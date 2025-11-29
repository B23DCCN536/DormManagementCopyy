package com.dorm.controller;

import com.dorm.model.TaiKhoan;
import com.dorm.service.TaiKhoanService;
import com.dorm.service.impl.TaiKhoanServiceImpl;
import com.dorm.util.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class DoiMatKhauController {

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label errorLabel;

    private final TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl();

    @FXML
    public void handleChangePassword() {
        String oldPass = oldPasswordField.getText();
        String newPass = newPasswordField.getText();
        String confirmPass = confirmPasswordField.getText();

        if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            errorLabel.setText("Vui lòng điền đầy đủ thông tin!");
            return;
        }

        if (!newPass.equals(confirmPass)) {
            errorLabel.setText("Mật khẩu xác nhận không khớp!");
            return;
        }

        TaiKhoan currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser == null) {
            errorLabel.setText("Phiên đăng nhập hết hạn!");
            return;
        }

        if (taiKhoanService.changePassword(currentUser.getUsername(), oldPass, newPass)) {
            errorLabel.setStyle("-fx-text-fill: green;");
            errorLabel.setText("Đổi mật khẩu thành công!");
            oldPasswordField.clear();
            newPasswordField.clear();
            confirmPasswordField.clear();
        } else {
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setText("Mật khẩu cũ không đúng hoặc lỗi hệ thống!");
        }
    }
}