package com.dorm.controller;

import com.dorm.model.LoaiPhong;
import com.dorm.service.LoaiPhongService;
import com.dorm.service.impl.LoaiPhongServiceImpl;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class QuanLyLoaiPhongController {

    @FXML
    private FlowPane roomTypesContainer;

    private final LoaiPhongService loaiPhongService = new LoaiPhongServiceImpl();

    @FXML
    public void initialize() {
        loadRoomTypes();
    }

    private void loadRoomTypes() {
        roomTypesContainer.getChildren().clear();
        for (LoaiPhong lp : loaiPhongService.getAllLoaiPhong()) {
            roomTypesContainer.getChildren().add(createRoomTypePanel(lp));
        }
    }

    private VBox createRoomTypePanel(LoaiPhong loaiPhong) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");
        card.setPrefWidth(250);
        card.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label(loaiPhong.getTenLoaiPhong());
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 18));

        Label idLabel = new Label("Mã loại phòng: " + loaiPhong.getMaLoaiPhong());
        idLabel.setFont(Font.font("System", 14));

        Label priceLabel = new Label(String.format("%,.0f VNĐ/tháng", loaiPhong.getDonGia()));
        priceLabel.setFont(Font.font("System", 14));
        priceLabel.setStyle("-fx-text-fill: #2196F3;");

        Label capacityLabel = new Label("Sức chứa: " + loaiPhong.getSoNguoiToiDa() + " người");
        capacityLabel.setFont(Font.font("System", 14));

        card.getChildren().addAll(nameLabel, idLabel, priceLabel, capacityLabel);
        return card;
    }
}
