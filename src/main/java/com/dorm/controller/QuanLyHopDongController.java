package com.dorm.controller;

import com.dorm.model.HopDong;
import com.dorm.model.SinhVien;
import com.dorm.model.enums.TrangThaiHopDong;
import com.dorm.service.HopDongService;
import com.dorm.service.SinhVienService;
import com.dorm.service.impl.HopDongServiceImpl;
import com.dorm.service.impl.SinhVienServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class QuanLyHopDongController {

    @FXML
    private TableView<HopDong> contractsTable;
    @FXML
    private TableColumn<HopDong, String> idCol;
    @FXML
    private TableColumn<HopDong, String> studentIdCol;
    @FXML
    private TableColumn<HopDong, String> studentNameCol;
    @FXML
    private TableColumn<HopDong, String> roomIdCol;
    @FXML
    private TableColumn<HopDong, Double> priceCol;
    @FXML
    private TableColumn<HopDong, LocalDate> createdDateCol;
    @FXML
    private TableColumn<HopDong, LocalDate> startDateCol;
    @FXML
    private TableColumn<HopDong, LocalDate> endDateCol;
    @FXML
    private TableColumn<HopDong, TrangThaiHopDong> statusCol;
    @FXML
    private TextField searchField;

    private final HopDongService hopDongService = new HopDongServiceImpl();
    private final SinhVienService sinhVienService = new SinhVienServiceImpl();
    private ObservableList<HopDong> hopDongList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadData();
        setupSearch();
        setupRowClick();
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setupRowClick() {
        contractsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !contractsTable.getSelectionModel().isEmpty()) {
                HopDong selected = contractsTable.getSelectionModel().getSelectedItem();
                showDetailPopup(selected);
            }
        });
    }

    private void showDetailPopup(HopDong hd) {
        javafx.scene.layout.VBox root = new javafx.scene.layout.VBox(15);
        root.setPadding(new javafx.geometry.Insets(20));

        javafx.scene.control.Label title = new javafx.scene.control.Label("Chi tiết Hợp đồng: " + hd.getMaHopDong());
        title.getStyleClass().add("popup-title");

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setStyle("-fx-font-size: 14px;");

        // Contract Info
        addDetailRow(grid, "Mã Hợp đồng:", hd.getMaHopDong(), 0);
        addDetailRow(grid, "Ngày lập:", String.valueOf(hd.getNgayLap()), 1);
        addDetailRow(grid, "Ngày bắt đầu:", String.valueOf(hd.getNgayBatDau()), 2);
        addDetailRow(grid, "Ngày kết thúc:", String.valueOf(hd.getNgayKetThuc()), 3);
        addDetailRow(grid, "Giá tiền:", String.format("%,.0f VND", hd.getGiaPhong()), 4);
        addDetailRow(grid, "Trạng thái:", String.valueOf(hd.getTrangThai()), 5);

        // Student Info
        SinhVien sv = sinhVienService.getSinhVienByMaSV(hd.getMaSinhVien());
        String svName = (sv != null) ? sv.getHoTen() : "Không tìm thấy";
        String svClass = (sv != null) ? sv.getLop() : "";
        
        addDetailRow(grid, "Mã Sinh viên:", hd.getMaSinhVien(), 6);
        addDetailRow(grid, "Tên Sinh viên:", svName, 7);
        if (!svClass.isEmpty()) {
            addDetailRow(grid, "Lớp:", svClass, 8);
        }

        // Room Info
        addDetailRow(grid, "Mã Phòng:", hd.getMaPhong(), 9);

        root.getChildren().addAll(title, grid);

        com.dorm.util.PopupUtil.showNodePopup("Chi tiết hợp đồng", root, 500, 550);
    }

    private void addDetailRow(javafx.scene.layout.GridPane grid, String label, String value, int row) {
        javafx.scene.control.Label lblTitle = new javafx.scene.control.Label(label);
        lblTitle.setStyle("-fx-font-weight: bold;");
        javafx.scene.control.Label lblValue = new javafx.scene.control.Label(value);
        grid.add(lblTitle, 0, row);
        grid.add(lblValue, 1, row);
    }

    private void setupTableColumns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("maHopDong"));
        studentIdCol.setCellValueFactory(new PropertyValueFactory<>("maSinhVien"));
        
        studentNameCol.setCellValueFactory(cellData -> {
            String maSV = cellData.getValue().getMaSinhVien();
            SinhVien sv = sinhVienService.getSinhVienByMaSV(maSV);
            return new SimpleStringProperty(sv != null ? sv.getHoTen() : "");
        });

        roomIdCol.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("giaPhong"));
        createdDateCol.setCellValueFactory(new PropertyValueFactory<>("ngayLap"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("ngayBatDau"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("ngayKetThuc"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    }

    private void loadData() {
        hopDongList.clear();
        hopDongList.addAll(hopDongService.getAllHopDong());
        contractsTable.setItems(hopDongList);
    }

    private void setupSearch() {
        FilteredList<HopDong> filteredData = new FilteredList<>(hopDongList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(hopDong -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (hopDong.getMaHopDong().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (hopDong.getMaSinhVien().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (hopDong.getMaPhong().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<HopDong> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(contractsTable.comparatorProperty());
        contractsTable.setItems(sortedData);
    }
}
