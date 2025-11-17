package com.dorm.model;

public class LoaiPhong {
    private String maLoaiPhong;
    private String tenLoaiPhong;
    private double donGia;
    private int soNguoiToiDa;

    public LoaiPhong() {}

    public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, double donGia, int soNguoiToiDa) {
        this.maLoaiPhong = maLoaiPhong;
        this.tenLoaiPhong = tenLoaiPhong;
        this.donGia = donGia;
        this.soNguoiToiDa = soNguoiToiDa;
    }

    public String getMaLoaiPhong() { return maLoaiPhong; }
    public void setMaLoaiPhong(String maLoaiPhong) { this.maLoaiPhong = maLoaiPhong; }

    public String getTenLoaiPhong() { return tenLoaiPhong; }
    public void setTenLoaiPhong(String tenLoaiPhong) { this.tenLoaiPhong = tenLoaiPhong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public int getSoNguoiToiDa() { return soNguoiToiDa; }
    public void setSoNguoiToiDa(int soNguoiToiDa) { this.soNguoiToiDa = soNguoiToiDa; }
}
