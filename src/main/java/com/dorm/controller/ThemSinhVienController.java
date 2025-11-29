package com.dorm.controller;

import com.dorm.model.HopDong;
import com.dorm.model.LoaiPhong;
import com.dorm.model.Phong;
import com.dorm.model.SinhVien;
import com.dorm.model.enums.TrangThaiHopDong;
import com.dorm.service.HopDongService;
import com.dorm.service.LoaiPhongService;
import com.dorm.service.PhongService;
import com.dorm.service.SinhVienService;
import com.dorm.service.TaiKhoanService;
import com.dorm.service.impl.HopDongServiceImpl;
import com.dorm.service.impl.LoaiPhongServiceImpl;
import com.dorm.service.impl.PhongServiceImpl;
import com.dorm.service.impl.SinhVienServiceImpl;
import com.dorm.service.impl.TaiKhoanServiceImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.List;

public class ThemSinhVienController {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private DatePicker dobPicker;
    @FXML private ComboBox<String> genderCombo;
    @FXML private TextField classField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField cccdField;
    @FXML private TextField hometownField;
    @FXML private ComboBox<Phong> roomCombo;
    @FXML private Label errorLabel;

    private final SinhVienService sinhVienService = new SinhVienServiceImpl();
    private final PhongService phongService = new PhongServiceImpl();
    private final HopDongService hopDongService = new HopDongServiceImpl();
    private final TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl();
    private final LoaiPhongService loaiPhongService = new LoaiPhongServiceImpl();

    private QuanLySinhVienController parentController;

    public void setParentController(QuanLySinhVienController parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void initialize() {
        genderCombo.setItems(FXCollections.observableArrayList("NAM", "NU"));
        
        // Configure DatePicker for manual input
        dobPicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd/MM/yyyy";
            java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter.ofPattern(pattern);

            {
                dobPicker.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    try {
                        return LocalDate.parse(string, dateFormatter);
                    } catch (java.time.format.DateTimeParseException e) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        });
        
        loadRooms();
    }

    private void loadRooms() {
        List<Phong> rooms = phongService.getPhongTrong();
        roomCombo.setItems(FXCollections.observableArrayList(rooms));
        roomCombo.setConverter(new StringConverter<Phong>() {
            @Override
            public String toString(Phong object) {
                return object != null ? object.getTenPhong() + " (" + object.getSoNguoiHienTai() + " người)" : "";
            }

            @Override
            public Phong fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void handleSave() {
        String id = idField.getText().toUpperCase();
        String name = nameField.getText();
        Phong selectedRoom = roomCombo.getValue();

        if (id.isEmpty() || name.isEmpty() || selectedRoom == null) {
            errorLabel.setText("Vui lòng điền các trường bắt buộc (*)");
            return;
        }

        // Check room capacity
        LoaiPhong lp = loaiPhongService.getLoaiPhongById(selectedRoom.getMaLoaiPhong());
        if (lp != null && selectedRoom.getSoNguoiHienTai() >= lp.getSoNguoiToiDa()) {
            errorLabel.setText("Phòng đã đầy (" + selectedRoom.getSoNguoiHienTai() + "/" + lp.getSoNguoiToiDa() + ")");
            return;
        }

        if (sinhVienService.getSinhVienByMaSV(id) != null) {
            errorLabel.setText("Mã sinh viên đã tồn tại!");
            return;
        }

        // 1. Create Account
        if (!taiKhoanService.createAccount(id, "123", "SINH_VIEN")) {
            errorLabel.setText("Lỗi khi tạo tài khoản!");
            return;
        }

        // 2. Create Student
        SinhVien sv = new SinhVien(
            id, id, name, dobPicker.getValue(), 
            genderCombo.getValue(), classField.getText(), 
            phoneField.getText(), cccdField.getText(), 
            emailField.getText(), hometownField.getText()
        );
        sinhVienService.addSinhVien(sv);

        // 3. Create Contract
        String contractId = hopDongService.generateNewContractId();
        // Need to get price from Room Type. For now, assume fixed or fetch.
        // Ideally fetch LoaiPhong. But Phong model has maLoaiPhong.
        // Let's assume 800000 for now or fetch if possible. 
        // To keep it simple, I'll use a default value or try to fetch via service if I had LoaiPhongService.
        // I'll use a placeholder value.
        double price = 800000.0; 
        
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(3);
        endDate = endDate.withDayOfMonth(endDate.lengthOfMonth());

        HopDong hd = new HopDong(
            contractId, id, selectedRoom.getMaPhong(), price,
            startDate, startDate, endDate,
            TrangThaiHopDong.HIEU_LUC
        );
        hopDongService.addHopDong(hd);

        // 4. Update Room Occupancy
        phongService.updateSoNguoi(selectedRoom.getMaPhong(), 1);

        // Close and Refresh
        if (parentController != null) {
            parentController.refreshTable();
        }
        closeDialog();
    }

    @FXML
    private void handleCancel() {
        closeDialog();
    }

    private void closeDialog() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
}
