package com.dorm.controller;

import com.dorm.model.HopDong;
import com.dorm.model.Phong;
import com.dorm.model.SinhVien;
import com.dorm.model.TaiKhoan;
import com.dorm.service.HopDongService;
import com.dorm.service.PhongService;
import com.dorm.service.SinhVienService;
import com.dorm.service.impl.HopDongServiceImpl;
import com.dorm.service.impl.PhongServiceImpl;
import com.dorm.service.impl.SinhVienServiceImpl;
import com.dorm.service.YeuCauGiaHanService;
import com.dorm.util.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HopDongCuaToiController {

    @FXML
    private Label contractIdLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label roomNameLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label statusLabel;

    private final SinhVienService sinhVienService = new SinhVienServiceImpl();
    private final HopDongService hopDongService = new HopDongServiceImpl();
    private final PhongService phongService = new PhongServiceImpl();

    private final YeuCauGiaHanService requestService = new com.dorm.service.impl.YeuCauGiaHanServiceImpl();

    @FXML
    public void initialize() {
        loadContractInfo();
    }

    @FXML
    private void handleExtend() {
        TaiKhoan currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser == null) return;
        SinhVien sv = sinhVienService.getSinhVienByUsername(currentUser.getUsername());
        if (sv == null) return;
        HopDong hopDong = hopDongService.getHopDongMoiNhat(sv.getMaSinhVien());
        
        if (hopDong == null) {
             showAlert("Bạn chưa có hợp đồng!", javafx.scene.control.Alert.AlertType.WARNING);
             return;
        }

        // Check for pending request
        if (requestService.getPendingRequest(sv.getMaSinhVien()) != null) {
            showAlert("Bạn đang có yêu cầu gia hạn chưa được xử lý!", javafx.scene.control.Alert.AlertType.WARNING);
            return;
        }

        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/fxml/extension_request_dialog.fxml"));
            javafx.scene.Parent root = loader.load();
            
            HopThoaiYeuCauGiaHanController controller = loader.getController();
            controller.setContract(hopDong);
            
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.setTitle("Yêu cầu gia hạn");
            stage.setScene(new javafx.scene.Scene(root));
            stage.showAndWait();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message, javafx.scene.control.Alert.AlertType type) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadContractInfo() {
        TaiKhoan currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser == null) {
            clearInfo();
            return;
        }

        SinhVien sv = sinhVienService.getSinhVienByUsername(currentUser.getUsername());
        if (sv == null) {
            clearInfo();
            return;
        }

        HopDong hopDong = hopDongService.getHopDongMoiNhat(sv.getMaSinhVien());
        if (hopDong != null) {
            contractIdLabel.setText(hopDong.getMaHopDong());
            startDateLabel.setText(hopDong.getNgayBatDau().toString());
            endDateLabel.setText(hopDong.getNgayKetThuc().toString());
            statusLabel.setText(hopDong.getTrangThai().toString());

            Phong phong = phongService.getPhongById(hopDong.getMaPhong());
            if (phong != null) {
                roomNameLabel.setText(phong.getTenPhong());
            } else {
                roomNameLabel.setText(hopDong.getMaPhong());
            }
        } else {
            clearInfo();
            statusLabel.setText("Chưa có hợp đồng");
        }
    }

    private void clearInfo() {
        contractIdLabel.setText("-");
        roomNameLabel.setText("-");
        startDateLabel.setText("-");
        endDateLabel.setText("-");
        statusLabel.setText("-");
    }
}
