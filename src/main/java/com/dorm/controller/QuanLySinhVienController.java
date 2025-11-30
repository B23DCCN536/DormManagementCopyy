package com.dorm.controller;

import com.dorm.model.HopDong;
import com.dorm.model.SinhVien;
import com.dorm.model.enums.TrangThaiHopDong;
import com.dorm.service.HopDongService;
import com.dorm.service.PhongService;
import com.dorm.service.SinhVienService;
import com.dorm.service.TaiKhoanService;
import com.dorm.service.impl.HopDongServiceImpl;
import com.dorm.service.impl.PhongServiceImpl;
import com.dorm.service.impl.SinhVienServiceImpl;
import com.dorm.service.impl.TaiKhoanServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class QuanLySinhVienController {

    @FXML
    private TableView<SinhVien> studentsTable;
    @FXML
    private TableColumn<SinhVien, String> idCol;
    @FXML
    private TableColumn<SinhVien, String> nameCol;
    @FXML
    private TableColumn<SinhVien, String> classCol;
    @FXML
    private TableColumn<SinhVien, String> roomCol;
    @FXML
    private TableColumn<SinhVien, String> contractCol;
    @FXML
    private TableColumn<SinhVien, String> dobCol;
    @FXML
    private TableColumn<SinhVien, String> genderCol;
    @FXML
    private TableColumn<SinhVien, String> phoneCol;
    @FXML
    private TableColumn<SinhVien, String> emailCol;
    @FXML
    private TableColumn<SinhVien, String> hometownCol;
    @FXML
    private TextField searchField;

    private SinhVienService sinhVienService;
    private HopDongService hopDongService;
    private PhongService phongService;
    private TaiKhoanService taiKhoanService;
    private ObservableList<SinhVien> studentList = FXCollections.observableArrayList();
    private java.util.Map<String, String> studentRoomMap = new java.util.HashMap<>();
    private java.util.Map<String, String> studentContractMap = new java.util.HashMap<>();

    public QuanLySinhVienController() {
        this.sinhVienService = new SinhVienServiceImpl();
        this.hopDongService = new HopDongServiceImpl();
        this.phongService = new PhongServiceImpl();
        this.taiKhoanService = new TaiKhoanServiceImpl();
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        loadData();
        setupSearchFunctionality();
        setupRowClick();
    }

    public void refreshTable() {
        loadData();
    }

    @FXML
    private void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_student_dialog.fxml"));
            Parent root = loader.load();
            
            ThemSinhVienController controller = loader.getController();
            controller.setParentController(this);
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Thêm Sinh Viên");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        SinhVien selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Vui lòng chọn sinh viên cần xóa!", Alert.AlertType.WARNING);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa sinh viên: " + selected.getHoTen() + "?");
        alert.setContentText("Hành động này sẽ xóa cả hợp đồng và tài khoản của sinh viên.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // 1. Check active contract to update room occupancy
            HopDong activeContract = hopDongService.getHopDongMoiNhat(selected.getMaSinhVien());
            
            if (activeContract != null && activeContract.getTrangThai() == TrangThaiHopDong.HIEU_LUC) {
                phongService.updateSoNguoi(activeContract.getMaPhong(), -1);
            }

            // 2. Delete Contracts
            hopDongService.deleteHopDongByMaSV(selected.getMaSinhVien());

            // 3. Delete Student
            sinhVienService.deleteSinhVien(selected.getMaSinhVien());

            // 4. Delete Account
            taiKhoanService.deleteAccount(selected.getUsername());

            refreshTable();
            showAlert("Xóa sinh viên thành công!", Alert.AlertType.INFORMATION);
        }
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setupRowClick() {
        studentsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !studentsTable.getSelectionModel().isEmpty()) {
                SinhVien selected = studentsTable.getSelectionModel().getSelectedItem();
                showDetailPopup(selected);
            }
        });
    }

    private void showDetailPopup(SinhVien sv) {
        javafx.scene.layout.VBox root = new javafx.scene.layout.VBox(15);
        root.setPadding(new javafx.geometry.Insets(20));

        javafx.scene.control.Label title = new javafx.scene.control.Label("Thông tin sinh viên: " + sv.getHoTen());
        title.getStyleClass().add("popup-title");

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setStyle("-fx-font-size: 14px;");

        addDetailRow(grid, "Mã SV:", sv.getMaSinhVien(), 0);
        addDetailRow(grid, "Họ tên:", sv.getHoTen(), 1);
        addDetailRow(grid, "Lớp:", sv.getLop(), 2);
        
        HopDong hd = hopDongService.getHopDongMoiNhat(sv.getMaSinhVien());
        addDetailRow(grid, "Mã HĐ:", (hd != null ? hd.getMaHopDong() : "Chưa có"), 3);
        addDetailRow(grid, "Phòng:", (hd != null ? hd.getMaPhong() : "Chưa có"), 4);

        addDetailRow(grid, "Ngày sinh:", String.valueOf(sv.getNgaySinh()), 5);
        addDetailRow(grid, "Giới tính:", sv.getGioiTinh(), 6);
        addDetailRow(grid, "SĐT:", sv.getSdt(), 7);
        addDetailRow(grid, "Email:", sv.getEmail(), 8);
        addDetailRow(grid, "CCCD:", sv.getCccd(), 9);
        addDetailRow(grid, "Quê quán:", sv.getQueQuan(), 10);

        root.getChildren().addAll(title, grid);

        com.dorm.util.PopupUtil.showNodePopup("Chi tiết sinh viên", root, 500, 500);
    }

    private void addDetailRow(javafx.scene.layout.GridPane grid, String label, String value, int row) {
        javafx.scene.control.Label lblTitle = new javafx.scene.control.Label(label);
        lblTitle.setStyle("-fx-font-weight: bold;");
        javafx.scene.control.Label lblValue = new javafx.scene.control.Label(value);
        
        grid.add(lblTitle, 0, row);
        grid.add(lblValue, 1, row);
    }

    private void setupTableColumns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("maSinhVien"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        classCol.setCellValueFactory(new PropertyValueFactory<>("lop"));
        
        roomCol.setCellValueFactory(cellData -> {
            String maSV = cellData.getValue().getMaSinhVien();
            String room = studentRoomMap.getOrDefault(maSV, "");
            return new javafx.beans.property.SimpleStringProperty(room);
        });

        contractCol.setCellValueFactory(cellData -> {
            String maSV = cellData.getValue().getMaSinhVien();
            String contract = studentContractMap.getOrDefault(maSV, "");
            return new javafx.beans.property.SimpleStringProperty(contract);
        });

        dobCol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        hometownCol.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
    }

    private void loadData() {
        if (sinhVienService != null) {
            studentList.setAll(sinhVienService.getAllSinhVien());
            
            // Pre-fetch room info
            List<HopDong> allContracts = hopDongService.getAllHopDong();
            studentRoomMap.clear();
            studentContractMap.clear();
            
            // Map to store the latest contract for each student
            java.util.Map<String, HopDong> latestContractMap = new java.util.HashMap<>();
            
            for (HopDong hd : allContracts) {
                String maSV = hd.getMaSinhVien();
                if (!latestContractMap.containsKey(maSV)) {
                    latestContractMap.put(maSV, hd);
                } else {
                    HopDong existing = latestContractMap.get(maSV);
                    // If current hd is newer than existing, replace it
                    if (hd.getNgayKetThuc().isAfter(existing.getNgayKetThuc())) {
                        latestContractMap.put(maSV, hd);
                    }
                }
            }
            
            for (java.util.Map.Entry<String, HopDong> entry : latestContractMap.entrySet()) {
                studentRoomMap.put(entry.getKey(), entry.getValue().getMaPhong());
                studentContractMap.put(entry.getKey(), entry.getValue().getMaHopDong());
            }

            studentsTable.setItems(studentList);
        }
    }

    private void setupSearchFunctionality() {
        FilteredList<SinhVien> filteredData = new FilteredList<>(studentList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(student -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (student.getHoTen().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (student.getMaSinhVien().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<SinhVien> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(studentsTable.comparatorProperty());
        studentsTable.setItems(sortedData);
    }
}
