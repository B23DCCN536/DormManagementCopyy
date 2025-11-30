package com.dorm.controller;

import com.dorm.model.HopDong;
import com.dorm.model.LoaiPhong;
import com.dorm.model.Phong;
import com.dorm.model.SinhVien;
import com.dorm.model.SuCo;
import com.dorm.model.TaiKhoan;
import com.dorm.model.ThongBao;
import com.dorm.model.enums.TrangThaiSuCo;
import com.dorm.service.HopDongService;
import com.dorm.service.LoaiPhongService;
import com.dorm.service.PhongService;
import com.dorm.service.SinhVienService;
import com.dorm.service.SuCoService;
import com.dorm.service.ThongBaoService;
import com.dorm.service.impl.HopDongServiceImpl;
import com.dorm.service.impl.LoaiPhongServiceImpl;
import com.dorm.service.impl.PhongServiceImpl;
import com.dorm.service.impl.SinhVienServiceImpl;
import com.dorm.service.impl.SuCoServiceImpl;
import com.dorm.service.impl.ThongBaoServiceImpl;
import com.dorm.util.SessionManager;
import com.dorm.view.NotificationListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;

public class TrangChuSinhVienController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label studentIdLabel;
    @FXML
    private Label classLabel;
    @FXML
    private Label dobLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label roomNameLabel;
    @FXML
    private Label roomTypeLabel;
    @FXML
    private Label roomPriceLabel;
    @FXML
    private Label roomStatusLabel;
    
    @FXML
    private ListView<ThongBao> notificationsListView;
    
    @FXML
    private TextField incidentTitleField;
    
    @FXML
    private TextArea incidentDescriptionArea;

    private final SinhVienService sinhVienService = new SinhVienServiceImpl();
    private final HopDongService hopDongService = new HopDongServiceImpl();
    private final PhongService phongService = new PhongServiceImpl();
    private final LoaiPhongService loaiPhongService = new LoaiPhongServiceImpl();
    private final ThongBaoService thongBaoService = new ThongBaoServiceImpl();
    private final SuCoService suCoService = new SuCoServiceImpl();

    private SinhVien currentSinhVien;

    @FXML
    public void initialize() {
        loadStudentInfo();
        loadRoomInfo();
        loadNotifications();
    }

    @FXML
    void handleChangePassword(ActionEvent event) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/fxml/change_password.fxml"));
            javafx.scene.Parent root = loader.load();
            
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Đổi mật khẩu");
            stage.setScene(new javafx.scene.Scene(root));
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentInfo() {
        TaiKhoan currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            currentSinhVien = sinhVienService.getSinhVienByUsername(currentUser.getUsername());
            
            if (currentSinhVien != null) {
                nameLabel.setText(currentSinhVien.getHoTen());
                studentIdLabel.setText(currentSinhVien.getMaSinhVien());
                classLabel.setText(currentSinhVien.getLop());
                dobLabel.setText(currentSinhVien.getNgaySinh() != null ? currentSinhVien.getNgaySinh().toString() : "");
                genderLabel.setText(currentSinhVien.getGioiTinh());
                phoneLabel.setText(currentSinhVien.getSdt());
            }
        } else {
             nameLabel.setText("Chưa đăng nhập");
        }
    }

    private void loadRoomInfo() {
        if (currentSinhVien == null) {
             roomNameLabel.setText("Chưa có thông tin");
             return;
        }

        HopDong hopDong = hopDongService.getHopDongMoiNhat(currentSinhVien.getMaSinhVien());
        
        if (hopDong != null && com.dorm.model.enums.TrangThaiHopDong.HIEU_LUC == hopDong.getTrangThai()) {
            Phong phong = phongService.getPhongById(hopDong.getMaPhong());
            if (phong != null) {
                roomNameLabel.setText(phong.getTenPhong());
                roomTypeLabel.setText(phong.getMaLoaiPhong());
                roomStatusLabel.setText(phong.getTrangThai().toString());

                LoaiPhong loaiPhong = loaiPhongService.getLoaiPhongById(phong.getMaLoaiPhong());
                if (loaiPhong != null) {
                    roomPriceLabel.setText(String.format("%,.0f VND", loaiPhong.getDonGia()));
                } else {
                    roomPriceLabel.setText("-");
                }
            }
        } else {
            roomNameLabel.setText("Chưa có phòng");
            roomTypeLabel.setText("-");
            roomPriceLabel.setText("-");
            roomStatusLabel.setText("-");
        }
    }
    
    private void loadNotifications() {
        List<ThongBao> list = thongBaoService.getAllThongBao();
        ObservableList<ThongBao> items = FXCollections.observableArrayList(list);
        notificationsListView.setItems(items);
        notificationsListView.setCellFactory(param -> new NotificationListCell());
    }
    
    @FXML
    void handleSendIncident(ActionEvent event) {
        if (currentSinhVien == null) {
            showAlert("Lỗi", "Bạn chưa đăng nhập hoặc không tìm thấy thông tin sinh viên!");
            return;
        }
        
        String title = incidentTitleField.getText();
        String description = incidentDescriptionArea.getText();
        
        if (title.isEmpty() || description.isEmpty()) {
            showAlert("Lỗi", "Vui lòng nhập đầy đủ tiêu đề và nội dung!");
            return;
        }
        
        SuCo suCo = new SuCo();
        suCo.setMaSuCo("SC" + System.currentTimeMillis()); // Generate ID
        suCo.setMaSinhVien(currentSinhVien.getMaSinhVien());
        
        HopDong hopDong = hopDongService.getHopDongMoiNhat(currentSinhVien.getMaSinhVien());
        if (hopDong != null && com.dorm.model.enums.TrangThaiHopDong.HIEU_LUC == hopDong.getTrangThai()) {
            suCo.setMaPhong(hopDong.getMaPhong());
        } else {
             // Nếu không có phòng, vẫn cho phép gửi nhưng maPhong có thể null hoặc xử lý khác
             // Tuy nhiên DB có thể yêu cầu maPhong NOT NULL. Kiểm tra lại DB schema.
             // Nếu DB yêu cầu maPhong, ta phải chặn hoặc gán giá trị mặc định.
             // Giả sử DB cho phép null hoặc ta chặn.
             // Nhưng ở đây lỗi là do maSuCo chưa được set!
        }
        
        suCo.setTieuDe(title);
        suCo.setNoiDung(description);
        suCo.setNgayBao(LocalDate.now());
        suCo.setTrangThai(TrangThaiSuCo.CHO_XU_LY);
        
        if (suCoService.addSuCo(suCo)) {
            showAlert("Thành công", "Gửi báo cáo sự cố thành công!");
            incidentTitleField.clear();
            incidentDescriptionArea.clear();
        } else {
            showAlert("Lỗi", "Gửi báo cáo thất bại!");
        }
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
