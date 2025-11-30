package com.dorm.controller;

import com.dorm.model.YeuCauGiaHan;
import com.dorm.service.HopDongService;
import com.dorm.service.YeuCauGiaHanService;
import com.dorm.service.impl.HopDongServiceImpl;
import com.dorm.service.impl.YeuCauGiaHanServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Optional;

public class QuanLyYeuCauGiaHanController {

    @FXML
    private TableView<YeuCauGiaHan> requestsTable;
    @FXML
    private TableColumn<YeuCauGiaHan, Integer> idCol;
    @FXML
    private TableColumn<YeuCauGiaHan, String> studentIdCol;
    @FXML
    private TableColumn<YeuCauGiaHan, String> contractIdCol;
    @FXML
    private TableColumn<YeuCauGiaHan, Integer> monthsCol;
    @FXML
    private TableColumn<YeuCauGiaHan, String> amountCol;
    @FXML
    private TableColumn<YeuCauGiaHan, LocalDate> dateCol;
    @FXML
    private TableColumn<YeuCauGiaHan, String> statusCol;
    @FXML
    private TextField searchField;

    private final YeuCauGiaHanService requestService = new YeuCauGiaHanServiceImpl();
    private final HopDongService hopDongService = new HopDongServiceImpl();
    private ObservableList<YeuCauGiaHan> requestList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadData();
        setupSearch();
    }

    private void setupTableColumns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("maYeuCau"));
        studentIdCol.setCellValueFactory(new PropertyValueFactory<>("maSinhVien"));
        contractIdCol.setCellValueFactory(new PropertyValueFactory<>("maHopDong"));
        monthsCol.setCellValueFactory(new PropertyValueFactory<>("soThang"));
        
        amountCol.setCellValueFactory(cellData -> 
            new SimpleStringProperty(String.format("%,.0f VND", cellData.getValue().getTongTien())));
            
        dateCol.setCellValueFactory(new PropertyValueFactory<>("ngayGui"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    }

    private void loadData() {
        requestList.setAll(requestService.getAllRequests());
        requestsTable.setItems(requestList);
    }

    private void setupSearch() {
        FilteredList<YeuCauGiaHan> filteredData = new FilteredList<>(requestList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(request -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (request.getMaSinhVien().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<YeuCauGiaHan> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(requestsTable.comparatorProperty());
        requestsTable.setItems(sortedData);
    }

    @FXML
    private void handleApprove() {
        YeuCauGiaHan selected = requestsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Vui lòng chọn yêu cầu!", Alert.AlertType.WARNING);
            return;
        }

        if (!"CHO_DUYET".equals(selected.getTrangThai())) {
            showAlert("Yêu cầu này đã được xử lý!", Alert.AlertType.WARNING);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận duyệt");
        alert.setHeaderText("Duyệt yêu cầu gia hạn cho SV: " + selected.getMaSinhVien() + "?");
        alert.setContentText("Hợp đồng sẽ được gia hạn thêm " + selected.getSoThang() + " tháng.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // 1. Update Request Status
            if (requestService.updateStatus(selected.getMaYeuCau(), "DA_DUYET")) {
                // 2. Extend Contract
                // We need to manually update the contract end date because extendHopDong in service might be too simple (just +1 month or similar)
                // Let's check HopDongService.extendHopDong. It was not fully implemented in the snippet I saw earlier.
                // I'll implement the logic here or update HopDongService.
                
                // Let's do it here for now using direct DB update or better, add a method to HopDongService.
                // But since I can't easily change the interface without affecting others, I'll use a custom update here if needed, 
                // OR I can assume extendHopDong adds 1 month? No, the request has specific months.
                
                // I should probably add a method `extendHopDong(String maHopDong, int months)` to HopDongService.
                // But I'll stick to what I have. I'll fetch the contract, update it, and save it.
                // Wait, `addHopDong` is for insert. I don't have `updateHopDong`.
                // I'll add `extendHopDong(String maHopDong, int months)` to HopDongService and Impl.
                
                extendContract(selected.getMaHopDong(), selected.getSoThang());
                
                showAlert("Duyệt yêu cầu thành công!", Alert.AlertType.INFORMATION);
                loadData();
            } else {
                showAlert("Có lỗi xảy ra!", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void handleReject() {
        YeuCauGiaHan selected = requestsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Vui lòng chọn yêu cầu!", Alert.AlertType.WARNING);
            return;
        }

        if (!"CHO_DUYET".equals(selected.getTrangThai())) {
            showAlert("Yêu cầu này đã được xử lý!", Alert.AlertType.WARNING);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận từ chối");
        alert.setHeaderText("Từ chối yêu cầu gia hạn của SV: " + selected.getMaSinhVien() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (requestService.updateStatus(selected.getMaYeuCau(), "TU_CHOI")) {
                showAlert("Đã từ chối yêu cầu!", Alert.AlertType.INFORMATION);
                loadData();
            } else {
                showAlert("Có lỗi xảy ra!", Alert.AlertType.ERROR);
            }
        }
    }

    private void extendContract(String maHopDong, int months) {
        hopDongService.extendHopDongByMonths(maHopDong, months);
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
