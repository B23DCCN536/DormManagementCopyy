package com.dorm.controller;

import com.dorm.model.Admin;
import com.dorm.model.ThongBao;
import com.dorm.service.AdminService;
import com.dorm.service.ThongBaoService;
import com.dorm.service.impl.AdminServiceImpl;
import com.dorm.service.impl.ThongBaoServiceImpl;
import com.dorm.view.NotificationListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TrangChuAdminController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private ListView<ThongBao> notificationList;

    private final AdminService adminService = new AdminServiceImpl();
    private final ThongBaoService thongBaoService = new ThongBaoServiceImpl();

    @FXML
    public void initialize() {
        loadAdminInfo();
        loadNotifications();
    }

    @FXML
    private void handleChangePassword() {
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

    private void loadAdminInfo() {
        // Assuming current logged in user is stored somewhere or passed.
        // For now, let's hardcode or fetch a default admin if no session management yet.
        // In a real app, you'd get the username from a SessionManager.
        // Let's assume "admin" for now.
        String currentUsername = "admin"; 
        Admin admin = adminService.getAdminByUsername(currentUsername);
        if (admin != null) {
            nameLabel.setText(admin.getHoTen());
            emailLabel.setText(admin.getEmail());
            phoneLabel.setText(admin.getSdt());
        }
    }

    private void loadNotifications() {
        ObservableList<ThongBao> items = FXCollections.observableArrayList(thongBaoService.getAllThongBao());
        notificationList.setItems(items);
        notificationList.setCellFactory(param -> new NotificationListCell());
    }
}
