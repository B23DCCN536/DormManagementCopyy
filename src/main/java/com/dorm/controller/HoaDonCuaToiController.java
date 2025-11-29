package com.dorm.controller;

import com.dorm.model.HoaDon;
import com.dorm.model.SinhVien;
import com.dorm.model.TaiKhoan;
import com.dorm.service.HoaDonService;
import com.dorm.service.SinhVienService;
import com.dorm.service.impl.HoaDonServiceImpl;
import com.dorm.service.impl.SinhVienServiceImpl;
import com.dorm.util.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class HoaDonCuaToiController {

    @FXML
    private TableView<HoaDon> invoicesTable;
    @FXML
    private TableColumn<HoaDon, String> invoiceIdCol;
    @FXML
    private TableColumn<HoaDon, String> roomCol;
    @FXML
    private TableColumn<HoaDon, String> contentCol;
    @FXML
    private TableColumn<HoaDon, LocalDate> dateCol;
    @FXML
    private TableColumn<HoaDon, Double> electricityCol;
    @FXML
    private TableColumn<HoaDon, Double> waterCol;
    @FXML
    private TableColumn<HoaDon, Double> amountCol;
    @FXML
    private TableColumn<HoaDon, String> statusCol;
    @FXML
    private TableColumn<HoaDon, LocalDate> paymentDateCol;
    @FXML
    private TableColumn<HoaDon, String> payerCol;

    private final HoaDonService hoaDonService = new HoaDonServiceImpl();
    private final SinhVienService sinhVienService = new SinhVienServiceImpl();
    private ObservableList<HoaDon> invoiceList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        loadData();
    }

    private void setupTable() {
        invoiceIdCol.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
        contentCol.setCellValueFactory(cellData -> {
            HoaDon hd = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(hd.getThang() + "/" + hd.getNam());
        });
        dateCol.setCellValueFactory(new PropertyValueFactory<>("ngayLap"));
        electricityCol.setCellValueFactory(new PropertyValueFactory<>("tienDien"));
        waterCol.setCellValueFactory(new PropertyValueFactory<>("tienNuoc"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        paymentDateCol.setCellValueFactory(new PropertyValueFactory<>("ngayDong"));
        payerCol.setCellValueFactory(new PropertyValueFactory<>("nguoiNopTien"));
    }

    private void loadData() {
        TaiKhoan currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser == null) {
            return;
        }

        SinhVien sv = sinhVienService.getSinhVienByUsername(currentUser.getUsername());
        if (sv != null) {
            invoiceList.clear();
            invoiceList.addAll(hoaDonService.getHoaDonBySinhVien(sv.getMaSinhVien()));
            invoicesTable.setItems(invoiceList);
        }
    }

    @FXML
    private void handleExportInvoice(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Chức năng xuất hóa đơn đang được phát triển!");
        alert.showAndWait();
    }
}
