package com.dorm.model;

import java.time.LocalDate;

public class HoaDon {
    private String maHoaDon;
    private String maPhong;
    private String maChiSo;
    private int thang;
    private int nam;
    private LocalDate ngayLap;
    private double tienDien;
    private double tienNuoc;
    private double tongTien;
    private String trangThai;
    private LocalDate ngayDong;
    private String nguoiNopTien;

    public HoaDon() {}

    public HoaDon(String maHoaDon, String maPhong, String maChiSo, int thang, int nam, LocalDate ngayLap, double tienDien, double tienNuoc, double tongTien, String trangThai, LocalDate ngayDong, String nguoiNopTien) {
        this.maHoaDon = maHoaDon;
        this.maPhong = maPhong;
        this.maChiSo = maChiSo;
        this.thang = thang;
        this.nam = nam;
        this.ngayLap = ngayLap;
        this.tienDien = tienDien;
        this.tienNuoc = tienNuoc;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.ngayDong = ngayDong;
        this.nguoiNopTien = nguoiNopTien;
    }

    public double tinhTong() {
        return this.tienDien + this.tienNuoc;
    }

    public java.io.File xuatPDF() {
        return null;
    }

    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public String getMaChiSo() { return maChiSo; }
    public void setMaChiSo(String maChiSo) { this.maChiSo = maChiSo; }

    public int getThang() { return thang; }
    public void setThang(int thang) { this.thang = thang; }

    public int getNam() { return nam; }
    public void setNam(int nam) { this.nam = nam; }

    public LocalDate getNgayLap() { return ngayLap; }
    public void setNgayLap(LocalDate ngayLap) { this.ngayLap = ngayLap; }

    public double getTienDien() { return tienDien; }
    public void setTienDien(double tienDien) { this.tienDien = tienDien; }

    public double getTienNuoc() { return tienNuoc; }
    public void setTienNuoc(double tienNuoc) { this.tienNuoc = tienNuoc; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public LocalDate getNgayDong() { return ngayDong; }
    public void setNgayDong(LocalDate ngayDong) { this.ngayDong = ngayDong; }

    public String getNguoiNopTien() { return nguoiNopTien; }
    public void setNguoiNopTien(String nguoiNopTien) { this.nguoiNopTien = nguoiNopTien; }
}
