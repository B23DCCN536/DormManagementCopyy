package com.dorm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ThongTinPopupController {

    @FXML
    private Label headerLabel;

    @FXML
    private TextArea contentArea;
    
    @FXML
    private Button closeBtn;

    public void setInfo(String header, String content) {
        headerLabel.setText(header);
        contentArea.setText(content);
    }
    
    @FXML
    private void handleClose() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
