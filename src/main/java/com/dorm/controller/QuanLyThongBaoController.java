package com.dorm.controller;

import com.dorm.model.ThongBao;
import com.dorm.service.ThongBaoService;
import com.dorm.service.impl.ThongBaoServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.UUID;

public class QuanLyThongBaoController {

    @FXML
    private TextField titleField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea contentArea;
    @FXML
    private TableView<ThongBao> notificationsTable;
    @FXML
    private TableColumn<ThongBao, String> idCol;
    @FXML
    private TableColumn<ThongBao, String> titleCol;
    @FXML
    private TableColumn<ThongBao, String> contentCol;
    @FXML
    private TableColumn<ThongBao, LocalDate> dateCol;

    private final ThongBaoService thongBaoService = new ThongBaoServiceImpl();
    private ObservableList<ThongBao> notificationList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        System.out.println("QuanLyThongBaoController initialized");
        setupTable();
        loadData();
        setupRowClick();
    }

    private void setupRowClick() {
        notificationsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !notificationsTable.getSelectionModel().isEmpty()) {
                ThongBao selected = notificationsTable.getSelectionModel().getSelectedItem();
                showDetailPopup(selected);
            }
        });
    }

    private void showDetailPopup(ThongBao thongBao) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/fxml/notification_detail_dialog.fxml"));
            javafx.scene.Parent root = loader.load();
            
            ChiTietThongBaoController controller = loader.getController();
            controller.setThongBao(thongBao);
            
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Chi tiết thông báo");
            stage.setScene(new javafx.scene.Scene(root));
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("maThongBao"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("tieuDe"));
        contentCol.setCellValueFactory(new PropertyValueFactory<>("noiDung"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("ngayDang"));
    }

    private void loadData() {
        notificationList.clear();
        notificationList.addAll(thongBaoService.getAllThongBao());
        notificationsTable.setItems(notificationList);
    }

    @FXML
    private void handleAdd() {
        String title = titleField.getText();
        String content = contentArea.getText();
        LocalDate date = datePicker.getValue();

        if (title == null || title.isEmpty() || content == null || content.isEmpty() || date == null) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        // Generate a random ID for now, or use a sequence if DB supports it.
        String id = "TB" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        ThongBao thongBao = new ThongBao(id, title, content, date);
        if (thongBaoService.addThongBao(thongBao)) {
            loadData();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Thêm thông báo thành công!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thêm thông báo thất bại!");
        }
    }

    @FXML
    private void handleDelete() {
        ThongBao selected = notificationsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn thông báo để xóa!");
            return;
        }

        if (thongBaoService.deleteThongBao(selected.getMaThongBao())) {
            loadData();
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Xóa thông báo thành công!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Xóa thông báo thất bại!");
        }
    }

    @FXML
    private void handleUpdate() {
        ThongBao selected = notificationsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn thông báo để sửa!");
            return;
        }
        
        // For simplicity, we might just update the fields and save. 
        // But here we need to implement the update logic in Service first if not exists.
        // Assuming we just want to update the selected item with current field values.
        // However, the current UI design implies we select -> fill fields -> update.
        // Let's implement a simple selection listener to fill fields first.
        
        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Chức năng sửa đang được phát triển.");
    }

    @FXML
    private void handleClear() {
        clearFields();
    }

    private void clearFields() {
        titleField.clear();
        contentArea.clear();
        datePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
