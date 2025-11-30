package com.dorm.util;

import com.dorm.controller.ThongTinPopupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PopupUtil {

    public static void showPopup(String title, String fxmlPath, Object controllerData) {
        try {
            FXMLLoader loader = new FXMLLoader(PopupUtil.class.getResource(fxmlPath));
            Parent root = loader.load();

            // If the controller needs data, we can pass it here if we define an interface
            // For now, we'll assume the controller has a method 'setData' or similar if we cast it
            // But to keep it generic, let's just show the view.
            // Better approach: The caller sets up the controller.
            
            // Let's try a simpler approach for "Pretty Popups":
            // Create a generic layout if fxmlPath is null, or load custom FXML.
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle(title);
            
            Scene scene = new Scene(root);
            // Add CSS
            scene.getStylesheets().add(PopupUtil.class.getResource("/css/styles.css").toExternalForm());
            
            stage.setScene(scene);
            stage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void showInfoPopup(String title, String header, String content) {
        try {
            FXMLLoader loader = new FXMLLoader(PopupUtil.class.getResource("/fxml/popup_info.fxml"));
            Parent root = loader.load();
            
            // Access controller to set text
            // We need a simple controller for this generic popup
            ThongTinPopupController controller = loader.getController();
            controller.setInfo(header, content);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(PopupUtil.class.getResource("/css/styles.css").toExternalForm());
            
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showNodePopup(String title, javafx.scene.Node content, double minWidth, double minHeight) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        javafx.scene.layout.StackPane root = new javafx.scene.layout.StackPane(content);
        root.setStyle("-fx-background-color: white; -fx-padding: 20;");

        Scene scene = new Scene(root);
        if (PopupUtil.class.getResource("/css/styles.css") != null) {
            scene.getStylesheets().add(PopupUtil.class.getResource("/css/styles.css").toExternalForm());
        }

        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
