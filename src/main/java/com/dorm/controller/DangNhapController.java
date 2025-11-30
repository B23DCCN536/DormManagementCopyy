package com.dorm.controller;

import com.dorm.model.TaiKhoan;
import com.dorm.model.enums.QuyenTruyCap;
import com.dorm.service.TaiKhoanService;
import com.dorm.service.impl.TaiKhoanServiceImpl;
import com.dorm.util.SessionManager;
import com.dorm.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DangNhapController {

    @FXML
    private Label errorLabel;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private final TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl();

    @FXML
    public void initialize() {
        // Allow pressing Enter to login
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                handleLogin(new ActionEvent());
            }
        });
        
        usernameField.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                handleLogin(new ActionEvent());
            }
        });
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        TaiKhoan taiKhoan = taiKhoanService.login(username, password);

        if (taiKhoan != null) {
            SessionManager.getInstance().setCurrentUser(taiKhoan);
            ViewFactory viewFactory = new ViewFactory((Stage) loginBtn.getScene().getWindow());
            if (taiKhoan.getRole() == QuyenTruyCap.ADMIN) {
                viewFactory.showAdminDashboard();
            } else if (taiKhoan.getRole() == QuyenTruyCap.SINH_VIEN) {
                viewFactory.showStudentDashboard();
            } else {
                errorLabel.setText("Quyền truy cập không hợp lệ!");
            }
        } else {
            errorLabel.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
        }
    }

}
