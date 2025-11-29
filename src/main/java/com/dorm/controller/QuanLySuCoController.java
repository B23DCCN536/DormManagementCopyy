package com.dorm.controller;

import com.dorm.model.SuCo;
import com.dorm.model.enums.TrangThaiSuCo;
import com.dorm.service.SuCoService;
import com.dorm.service.impl.SuCoServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class QuanLySuCoController {

    @FXML
    private TableView<SuCo> incidentTable;
    @FXML
    private TableColumn<SuCo, String> idCol;
    @FXML
    private TableColumn<SuCo, String> studentIdCol;
    @FXML
    private TableColumn<SuCo, String> roomIdCol;
    @FXML
    private TableColumn<SuCo, String> descriptionCol;
    @FXML
    private TableColumn<SuCo, LocalDate> dateCol;
    @FXML
    private TableColumn<SuCo, TrangThaiSuCo> statusCol;
    @FXML
    private ComboBox<TrangThaiSuCo> statusComboBox;

    private final SuCoService suCoService = new SuCoServiceImpl();
    private ObservableList<SuCo> suCoList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        setupComboBox();
        loadData();
        setupRowClick();
    }

    private void setupRowClick() {
        incidentTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !incidentTable.getSelectionModel().isEmpty()) {
                SuCo selected = incidentTable.getSelectionModel().getSelectedItem();
                showDetailPopup(selected);
            }
        });
    }

    private void showDetailPopup(SuCo suCo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chi tiết sự cố");
        alert.setHeaderText("Sự cố: " + suCo.getTieuDe());
        
        String content = String.format(
            "Mã sự cố: %s\n" +
            "Mã SV: %s\n" +
            "Mã phòng: %s\n" +
            "Ngày báo: %s\n" +
            "Trạng thái: %s\n\n" +
            "Nội dung:\n%s",
            suCo.getMaSuCo(),
            suCo.getMaSinhVien(),
            suCo.getMaPhong(),
            suCo.getNgayBao(),
            suCo.getTrangThai(),
            suCo.getNoiDung()
        );
        
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void setupTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("maSuCo"));
        studentIdCol.setCellValueFactory(new PropertyValueFactory<>("maSinhVien"));
        roomIdCol.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("ngayBao"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));

        incidentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                statusComboBox.setValue(newSelection.getTrangThai());
            }
        });
    }

    private void setupComboBox() {
        statusComboBox.setItems(FXCollections.observableArrayList(TrangThaiSuCo.values()));
    }

    private void loadData() {
        suCoList.clear();
        suCoList.addAll(suCoService.getAllSuCo());
        incidentTable.setItems(suCoList);
    }

    @FXML
    private void handleUpdateStatus() {
        SuCo selectedSuCo = incidentTable.getSelectionModel().getSelectedItem();
        TrangThaiSuCo newStatus = statusComboBox.getValue();

        if (selectedSuCo != null && newStatus != null) {
            if (suCoService.updateStatus(selectedSuCo.getMaSuCo(), newStatus)) {
                selectedSuCo.capNhatTrangThai(newStatus);
                incidentTable.refresh();
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Cập nhật trạng thái thành công!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Cập nhật trạng thái thất bại!");
            }
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
