package com.dorm.model;

import java.time.LocalDate;

public class YeuCauGiaHan {
    private int maYeuCau;
    private String maHopDong;
    private String maSinhVien;
    private int soThang;
    private double tongTien;
    private LocalDate ngayGui;
    private String trangThai; // CHO_DUYET, DA_DUYET, TU_CHOI

    public YeuCauGiaHan() {
    }

    public YeuCauGiaHan(int maYeuCau, String maHopDong, String maSinhVien, int soThang, double tongTien, LocalDate ngayGui, String trangThai) {
        this.maYeuCau = maYeuCau;
        this.maHopDong = maHopDong;
        this.maSinhVien = maSinhVien;
        this.soThang = soThang;
        this.tongTien = tongTien;
        this.ngayGui = ngayGui;
        this.trangThai = trangThai;
    }

    public int getMaYeuCau() {
        return maYeuCau;
    }

    public void setMaYeuCau(int maYeuCau) {
        this.maYeuCau = maYeuCau;
    }

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public int getSoThang() {
        return soThang;
    }

    public void setSoThang(int soThang) {
        this.soThang = soThang;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDate getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(LocalDate ngayGui) {
        this.ngayGui = ngayGui;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
