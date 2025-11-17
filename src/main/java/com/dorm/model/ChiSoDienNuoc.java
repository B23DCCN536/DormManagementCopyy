package com.dorm.model;

public class ChiSoDienNuoc {
    private String maChiSo;
    private String maPhong;
    private int thang;
    private int nam;
    private int sodien;
    private int sonuoc;
    private double donGiaDien;
    private double donGiaNuoc;
    private double thanhTienDien;
    private double thanhTienNuoc;

    public ChiSoDienNuoc() {}

    public ChiSoDienNuoc(String maChiSo, String maPhong, int thang, int nam, int sodien, int sonuoc, double donGiaDien, double donGiaNuoc, double thanhTienDien, double thanhTienNuoc) {
        this.maChiSo = maChiSo;
        this.maPhong = maPhong;
        this.thang = thang;
        this.nam = nam;
        this.sodien = sodien;
        this.sonuoc = sonuoc;
        this.donGiaDien = donGiaDien;
        this.donGiaNuoc = donGiaNuoc;
        this.thanhTienDien = thanhTienDien;
        this.thanhTienNuoc = thanhTienNuoc;
    }

    public double tinhTienDien() {
        return this.sodien * this.donGiaDien;
    }

    public double tinhTienNuoc() {
        return this.sonuoc * this.donGiaNuoc;
    }

    public String getMaChiSo() { return maChiSo; }
    public void setMaChiSo(String maChiSo) { this.maChiSo = maChiSo; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public int getThang() { return thang; }
    public void setThang(int thang) { this.thang = thang; }

    public int getNam() { return nam; }
    public void setNam(int nam) { this.nam = nam; }

    public int getSodien() { return sodien; }
    public void setSodien(int sodien) { this.sodien = sodien; }

    public int getSonuoc() { return sonuoc; }
    public void setSonuoc(int sonuoc) { this.sonuoc = sonuoc; }

    public double getDonGiaDien() { return donGiaDien; }
    public void setDonGiaDien(double donGiaDien) { this.donGiaDien = donGiaDien; }

    public double getDonGiaNuoc() { return donGiaNuoc; }
    public void setDonGiaNuoc(double donGiaNuoc) { this.donGiaNuoc = donGiaNuoc; }

    public double getThanhTienDien() { return thanhTienDien; }
    public void setThanhTienDien(double thanhTienDien) { this.thanhTienDien = thanhTienDien; }

    public double getThanhTienNuoc() { return thanhTienNuoc; }
    public void setThanhTienNuoc(double thanhTienNuoc) { this.thanhTienNuoc = thanhTienNuoc; }
}
