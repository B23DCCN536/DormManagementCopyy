package com.dorm.controller;

import com.dorm.view.ViewFactory;
import com.dorm.util.SessionManager;
import com.dorm.model.TaiKhoan;
import com.dorm.model.enums.QuyenTruyCap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BangDieuKhienController {

    @FXML
    private StackPane contentArea;

    @FXML
    private Button contractsBtn;

    @FXML
    private Button extensionRequestsBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button incidentsBtn;

    @FXML
    private Button invoicesBtn;

    @FXML
    private Button notificationsBtn;

    @FXML
    private Button roomTypesBtn;

    @FXML
    private Button roomsBtn;

    @FXML
    private Button studentsBtn;

    @FXML
    private Label studentClassLabel;

    @FXML
    private Label studentNameLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        showHome(null);
    }

    private void setActiveButton(Button activeButton) {
        // Reset all buttons
        resetButtonStyle(homeBtn);
        resetButtonStyle(studentsBtn);
        resetButtonStyle(roomsBtn);
        resetButtonStyle(roomTypesBtn);
        resetButtonStyle(contractsBtn);
        resetButtonStyle(extensionRequestsBtn);
        resetButtonStyle(invoicesBtn);
        resetButtonStyle(notificationsBtn);
        resetButtonStyle(incidentsBtn);

        // Set active style
        if (activeButton != null) {
            activeButton.getStyleClass().add("active");
        }
    }

    private void resetButtonStyle(Button btn) {
        if (btn != null) {
            btn.getStyleClass().remove("active");
        }
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleLogout(ActionEvent event) {
        Stage stage = (Stage) homeBtn.getScene().getWindow();
        ViewFactory viewFactory = new ViewFactory(stage);
        viewFactory.showLogin();
    }

    @FXML
    void showContracts(ActionEvent event) {
        setActiveButton(contractsBtn);
        TaiKhoan user = SessionManager.getInstance().getCurrentUser();
        if (user != null && user.getRole() == QuyenTruyCap.SINH_VIEN) {
             welcomeLabel.setText("Hợp đồng của tôi");
             loadView("/fxml/my_contracts.fxml");
        } else {
             welcomeLabel.setText("Quản lý Hợp đồng");
             loadView("/fxml/admin_contracts.fxml");
        }
    }

    @FXML
    void showExtensionRequests(ActionEvent event) {
        setActiveButton(extensionRequestsBtn);
        welcomeLabel.setText("Quản lý Yêu cầu Gia hạn");
        loadView("/fxml/admin_extension_requests.fxml");
    }

    @FXML
    void showHome(ActionEvent event) {
        setActiveButton(homeBtn);
        welcomeLabel.setText("Trang chủ");
        TaiKhoan user = SessionManager.getInstance().getCurrentUser();
        if (user != null && user.getRole() == QuyenTruyCap.SINH_VIEN) {
             loadView("/fxml/student_home.fxml");
        } else {
             loadView("/fxml/admin_home.fxml");
        }
    }

    @FXML
    void showIncidents(ActionEvent event) {
        setActiveButton(incidentsBtn);
        welcomeLabel.setText("Quản lý Sự cố");
        loadView("/fxml/admin_incidents.fxml");
    }

    @FXML
    void showInvoices(ActionEvent event) {
        setActiveButton(invoicesBtn);
        TaiKhoan user = SessionManager.getInstance().getCurrentUser();
        if (user != null && user.getRole() == QuyenTruyCap.SINH_VIEN) {
             welcomeLabel.setText("Hóa đơn của tôi");
             loadView("/fxml/my_invoices.fxml");
        } else {
             welcomeLabel.setText("Quản lý Hóa đơn");
             loadView("/fxml/admin_invoices.fxml");
        }
    }

    @FXML
    void showNotifications(ActionEvent event) {
        setActiveButton(notificationsBtn);
        welcomeLabel.setText("Thông báo");
        loadView("/fxml/admin_notifications.fxml");
    }

    @FXML
    void showRoomTypes(ActionEvent event) {
        setActiveButton(roomTypesBtn);
        welcomeLabel.setText("Quản lý Loại phòng");
        loadView("/fxml/room_types.fxml");
    }

    @FXML
    void showRooms(ActionEvent event) {
        setActiveButton(roomsBtn);
        welcomeLabel.setText("Quản lý Phòng");
        loadView("/fxml/admin_rooms.fxml");
    }

    @FXML
    void showStudents(ActionEvent event) {
        setActiveButton(studentsBtn);
        welcomeLabel.setText("Quản lý Sinh viên");
        loadView("/fxml/admin_students.fxml");
    }

    @FXML
    void showChangePassword(ActionEvent event) {
        welcomeLabel.setText("Đổi mật khẩu");
        loadView("/fxml/change_password.fxml");
    }

}
