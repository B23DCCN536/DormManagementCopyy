package com.dorm.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewFactory {
    private Stage primaryStage;

    public ViewFactory(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLogin() {
        showStage("/fxml/login.fxml", "Login");
    }

    public void showAdminDashboard() {
        showStage("/fxml/admin_dashboard.fxml", "Admin Dashboard");
    }

    public void showExtensionRequests() {
        showStage("/fxml/admin_extension_requests.fxml", "Quản lý Yêu cầu Gia hạn");
    }

    public void showStudentDashboard() {
        showStage("/fxml/student_dashboard.fxml", "Student Dashboard");
    }

    private void showStage(String fxmlPath, String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle(title);
            
            // Set application icon
            try {
                primaryStage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("/image/Logo_PTIT_University.png")));
            } catch (Exception e) {
                System.err.println("Could not load application icon: " + e.getMessage());
            }

            primaryStage.setScene(scene);
            
            primaryStage.show();

            // Fix: Toggle maximized state to ensure window is correctly sized after scene switch
            primaryStage.setMaximized(false);
            primaryStage.setMaximized(true);
            
            // Prevent minimize
            primaryStage.iconifiedProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal) {
                    primaryStage.setIconified(false);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
