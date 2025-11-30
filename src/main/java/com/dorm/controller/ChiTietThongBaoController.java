package com.dorm.controller;

import com.dorm.model.ThongBao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChiTietThongBaoController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Text contentText;

    public void setThongBao(ThongBao thongBao) {
        if (thongBao != null) {
            titleLabel.setText(thongBao.getTieuDe());
            dateLabel.setText("Ngày đăng: " + thongBao.getNgayDang());
            contentText.setText(thongBao.getNoiDung());
        }
    }

    @FXML
    private void closeDialog() {
        Stage stage = (Stage) titleLabel.getScene().getWindow();
        stage.close();
    }
}
