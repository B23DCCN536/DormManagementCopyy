package com.dorm.controller;

import com.dorm.model.Phong;
import com.dorm.model.enums.TrangThaiPhong;
import com.dorm.service.PhongService;
import com.dorm.service.impl.PhongServiceImpl;
import com.dorm.model.HopDong;
import com.dorm.model.SinhVien;
import com.dorm.model.enums.TrangThaiHopDong;
import com.dorm.service.HopDongService;
import com.dorm.service.SinhVienService;
import com.dorm.service.impl.HopDongServiceImpl;
import com.dorm.service.impl.SinhVienServiceImpl;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class QuanLyPhongController {

    @FXML
    private TableView<Phong> roomsTable;
    @FXML
    private TableColumn<Phong, String> idCol;
    @FXML
    private TableColumn<Phong, String> nameCol;
    @FXML
    private TableColumn<Phong, String> buildingCol;
    @FXML
    private TableColumn<Phong, String> typeCol;
    @FXML
    private TableColumn<Phong, Integer> occupancyCol;
    @FXML
    private TableColumn<Phong, String> genderCol;
    @FXML
    private TableColumn<Phong, TrangThaiPhong> statusCol;
    @FXML
    private TextField searchField;

    private final PhongService phongService = new PhongServiceImpl();
    private final HopDongService hopDongService = new HopDongServiceImpl();
    private final SinhVienService sinhVienService = new SinhVienServiceImpl();
    private ObservableList<Phong> phongList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadData();
        setupSearch();
        setupRowClick();
    }

    private void setupRowClick() {
        roomsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !roomsTable.getSelectionModel().isEmpty()) {
                Phong selected = roomsTable.getSelectionModel().getSelectedItem();
                showDetailPopup(selected);
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void showDetailPopup(Phong phong) {
        javafx.scene.layout.VBox root = new javafx.scene.layout.VBox(20);
        root.setPadding(new javafx.geometry.Insets(20));
        
        // Room Info Section
        javafx.scene.control.Label title = new javafx.scene.control.Label("Thông tin phòng: " + phong.getTenPhong());
        title.getStyleClass().add("popup-title");
        
        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(20);
        grid.setVgap(10);
        
        addDetailRow(grid, "Mã phòng:", phong.getMaPhong(), 0);
        addDetailRow(grid, "Tòa:", phong.getMaToa(), 1);
        addDetailRow(grid, "Loại phòng:", phong.getMaLoaiPhong(), 2);
        addDetailRow(grid, "Số người:", String.valueOf(phong.getSoNguoiHienTai()), 3);
        addDetailRow(grid, "Giới tính:", phong.getGioitinh(), 4);
        addDetailRow(grid, "Trạng thái:", String.valueOf(phong.getTrangThai()), 5);
        
        // Student List Section
        javafx.scene.control.Label listTitle = new javafx.scene.control.Label("Danh sách sinh viên trong phòng");
        listTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        TableView<SinhVien> studentTable = new TableView<>();
        studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<SinhVien, String> idCol = new TableColumn<>("Mã SV");
        idCol.setCellValueFactory(new PropertyValueFactory<>("maSinhVien"));
        
        TableColumn<SinhVien, String> nameCol = new TableColumn<>("Họ tên");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        
        TableColumn<SinhVien, String> classCol = new TableColumn<>("Lớp");
        classCol.setCellValueFactory(new PropertyValueFactory<>("lop"));
        
        TableColumn<SinhVien, String> phoneCol = new TableColumn<>("SĐT");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        
        studentTable.getColumns().addAll(idCol, nameCol, classCol, phoneCol);
        
        // Load data
        ObservableList<SinhVien> students = FXCollections.observableArrayList();
        List<HopDong> contracts = hopDongService.getAllHopDong();
        for (HopDong hd : contracts) {
            // Hiển thị cả HIEU_LUC và HET_HAN để admin có thể xem sinh viên cần gia hạn
            if (hd.getMaPhong().equals(phong.getMaPhong()) && 
               (hd.getTrangThai() == TrangThaiHopDong.HIEU_LUC || hd.getTrangThai() == TrangThaiHopDong.HET_HAN)) {
                SinhVien sv = sinhVienService.getSinhVienByMaSV(hd.getMaSinhVien());
                if (sv != null) {
                    students.add(sv);
                }
            }
        }
        studentTable.setItems(students);
        studentTable.setPrefHeight(200);
        
        root.getChildren().addAll(title, grid, listTitle, studentTable);
        
        com.dorm.util.PopupUtil.showNodePopup("Chi tiết phòng " + phong.getTenPhong(), root, 700, 600);
    }

    private void addDetailRow(javafx.scene.layout.GridPane grid, String label, String value, int row) {
        javafx.scene.control.Label lblTitle = new javafx.scene.control.Label(label);
        lblTitle.setStyle("-fx-font-weight: bold;");
        javafx.scene.control.Label lblValue = new javafx.scene.control.Label(value);
        grid.add(lblTitle, 0, row);
        grid.add(lblValue, 1, row);
    }

    private void setupTableColumns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("maPhong"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("tenPhong"));
        buildingCol.setCellValueFactory(new PropertyValueFactory<>("maToa"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("maLoaiPhong"));
        occupancyCol.setCellValueFactory(new PropertyValueFactory<>("soNguoiHienTai"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gioitinh"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    }

    private void loadData() {
        phongList.clear();
        phongList.addAll(phongService.getAllPhong());
        roomsTable.setItems(phongList);
    }

    private void setupSearch() {
        FilteredList<Phong> filteredData = new FilteredList<>(phongList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(phong -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (phong.getMaPhong().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (phong.getTenPhong().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (phong.getMaToa().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Phong> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(roomsTable.comparatorProperty());
        roomsTable.setItems(sortedData);
    }
}
