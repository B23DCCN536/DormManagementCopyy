package com.dorm.controller;

import com.dorm.model.HopDong;
import com.dorm.model.YeuCauGiaHan;
import com.dorm.service.YeuCauGiaHanService;
import com.dorm.service.impl.YeuCauGiaHanServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class HopThoaiYeuCauGiaHanController {

    @FXML
    private Spinner<Integer> monthsSpinner;
    @FXML
    private Label totalAmountLabel;

    private HopDong currentContract;
    private final YeuCauGiaHanService requestService = new YeuCauGiaHanServiceImpl();

    public void setContract(HopDong contract) {
        this.currentContract = contract;
        updateTotalAmount();
    }

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1);
        monthsSpinner.setValueFactory(valueFactory);
        
        monthsSpinner.valueProperty().addListener((obs, oldValue, newValue) -> updateTotalAmount());
    }

    private void updateTotalAmount() {
        if (currentContract != null) {
            int months = monthsSpinner.getValue();
            double total = months * currentContract.getGiaPhong();
            totalAmountLabel.setText(String.format("%,.0f VND", total));
        }
    }

    @FXML
    private void handleConfirm() {
        if (currentContract == null) return;

        int months = monthsSpinner.getValue();
        double total = months * currentContract.getGiaPhong();

        YeuCauGiaHan request = new YeuCauGiaHan();
        request.setMaHopDong(currentContract.getMaHopDong());
        request.setMaSinhVien(currentContract.getMaSinhVien());
        request.setSoThang(months);
        request.setTongTien(total);
        request.setNgayGui(LocalDate.now());
        request.setTrangThai("CHO_DUYET");

        if (requestService.createRequest(request)) {
            showAlert("Gửi yêu cầu gia hạn thành công! Vui lòng chờ Admin duyệt.", Alert.AlertType.INFORMATION);
            closeDialog();
        } else {
            showAlert("Gửi yêu cầu thất bại!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleCancel() {
        closeDialog();
    }

    private void closeDialog() {
        Stage stage = (Stage) monthsSpinner.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
