package com.dorm.view;

import com.dorm.model.ThongBao;
import com.dorm.controller.ChiTietThongBaoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class NotificationListCell extends ListCell<ThongBao> {

    private final VBox content;
    private final Label title;
    private final Label date;
    private final Label preview;

    public NotificationListCell() {
        super();
        title = new Label();
        title.getStyleClass().add("notification-title");
        title.setWrapText(true);

        date = new Label();
        date.getStyleClass().add("notification-date");

        preview = new Label();
        preview.getStyleClass().add("notification-content");
        preview.setWrapText(true);
        // Limit preview to 2 lines roughly (CSS doesn't support line-clamp well in JavaFX 8/11 without tricks, 
        // so we just rely on wrapText and maybe max height if needed, or truncate string)
        
        content = new VBox(5);
        content.getChildren().addAll(title, date, preview);
        content.getStyleClass().add("notification-list-cell");
        
        // Handle click to show details
        content.setOnMouseClicked(event -> {
            ThongBao item = getItem();
            if (item != null) {
                showDetailPopup(item);
            }
        });
    }

    @Override
    protected void updateItem(ThongBao item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
            setText(null);
            setStyle("-fx-background-color: transparent;"); // Ensure empty cells are transparent
        } else {
            title.setText(item.getTieuDe());
            date.setText(item.getNgayDang().toString());
            
            // Truncate content for preview
            String fullContent = item.getNoiDung();
            if (fullContent.length() > 100) {
                preview.setText(fullContent.substring(0, 100) + "...");
            } else {
                preview.setText(fullContent);
            }

            setGraphic(content);
            setText(null);
        }
    }

    private void showDetailPopup(ThongBao item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/notification_detail_dialog.fxml"));
            Parent root = loader.load();
            
            ChiTietThongBaoController controller = loader.getController();
            controller.setThongBao(item);
            
            Stage stage = new Stage();
            stage.setTitle("Chi tiết thông báo");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
