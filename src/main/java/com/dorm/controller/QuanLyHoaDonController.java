package com.dorm.controller;

import com.dorm.model.HoaDon;
import com.dorm.service.HoaDonService;
import com.dorm.service.impl.HoaDonServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class QuanLyHoaDonController {

    @FXML
    private TableView<HoaDon> invoicesTable;
    @FXML
    private TableColumn<HoaDon, String> idCol;
    @FXML
    private TableColumn<HoaDon, String> roomIdCol;
    @FXML
    private TableColumn<HoaDon, Integer> monthCol;
    @FXML
    private TableColumn<HoaDon, Integer> yearCol;
    @FXML
    private TableColumn<HoaDon, Double> electricCol;
    @FXML
    private TableColumn<HoaDon, Double> waterCol;
    @FXML
    private TableColumn<HoaDon, Double> totalCol;
    @FXML
    private TableColumn<HoaDon, String> statusCol;
    @FXML
    private TableColumn<HoaDon, String> payerCol;
    @FXML
    private TextField searchField;

    private final HoaDonService hoaDonService = new HoaDonServiceImpl();
    private ObservableList<HoaDon> hoaDonList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        System.out.println("QuanLyHoaDonController initialized");
        setupTableColumns();
        loadData();
        setupSearch();
        setupRowClick();
    }

    private void setupTableColumns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
        roomIdCol.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("thang"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("nam"));
        electricCol.setCellValueFactory(new PropertyValueFactory<>("tienDien"));
        waterCol.setCellValueFactory(new PropertyValueFactory<>("tienNuoc"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        payerCol.setCellValueFactory(new PropertyValueFactory<>("nguoiNopTien"));
    }

    private void setupRowClick() {
        invoicesTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !invoicesTable.getSelectionModel().isEmpty()) {
                HoaDon selected = invoicesTable.getSelectionModel().getSelectedItem();
                showDetailPopup(selected);
            }
        });
    }

    private void showDetailPopup(HoaDon hoaDon) {
        String header = "Hóa đơn: " + hoaDon.getMaHoaDon();
        String content = String.format(
            "Mã phòng: %s\n" +
            "Tháng/Năm: %d/%d\n" +
            "Ngày lập: %s\n" +
            "Tiền điện: %,.0f VND\n" +
            "Tiền nước: %,.0f VND\n" +
            "Tổng tiền: %,.0f VND\n" +
            "Trạng thái: %s\n" +
            "Ngày đóng: %s\n" +
            "Người nộp: %s",
            hoaDon.getMaPhong(),
            hoaDon.getThang(), hoaDon.getNam(),
            hoaDon.getNgayLap(),
            hoaDon.getTienDien(),
            hoaDon.getTienNuoc(),
            hoaDon.getTongTien(),
            hoaDon.getTrangThai(),
            hoaDon.getNgayDong() != null ? hoaDon.getNgayDong().toString() : "Chưa đóng",
            hoaDon.getNguoiNopTien() != null ? hoaDon.getNguoiNopTien() : "Chưa có"
        );
        
        com.dorm.util.PopupUtil.showInfoPopup("Chi tiết hóa đơn", header, content);
    }

    private void loadData() {
        try {
            hoaDonList.clear();
            hoaDonList.addAll(hoaDonService.getAllHoaDon());
            invoicesTable.setItems(hoaDonList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupSearch() {
        FilteredList<HoaDon> filteredData = new FilteredList<>(hoaDonList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(hoaDon -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (hoaDon.getMaHoaDon().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (hoaDon.getMaPhong().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<HoaDon> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(invoicesTable.comparatorProperty());
        invoicesTable.setItems(sortedData);
    }
}
