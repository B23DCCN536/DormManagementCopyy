package com.dorm.model;

import java.time.LocalDate;

public class SinhVien {
    private String maSinhVien;
    private String username;
    private String hoTen;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String lop;
    private String sdt;
    private String cccd;
    private String email;
    private String queQuan;

    public SinhVien() {}

    public SinhVien(String maSinhVien, String username, String hoTen, LocalDate ngaySinh, String gioiTinh, String lop, String sdt, String cccd, String email, String queQuan) {
        this.maSinhVien = maSinhVien;
        this.username = username;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.lop = lop;
        this.sdt = sdt;
        this.cccd = cccd;
        this.email = email;
        this.queQuan = queQuan;
    }

    public void xemThongTin() {
        System.out.println(this.toString());
    }

    public java.util.List<HoaDon> xemHoaDon() {
        return new java.util.ArrayList<>();
    }

    public java.util.List<HopDong> xemHopDong() {
        return new java.util.ArrayList<>();
    }

    public String getMaSinhVien() { return maSinhVien; }
    public void setMaSinhVien(String maSinhVien) { this.maSinhVien = maSinhVien; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public LocalDate getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getLop() { return lop; }
    public void setLop(String lop) { this.lop = lop; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getCccd() { return cccd; }
    public void setCccd(String cccd) { this.cccd = cccd; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getQueQuan() { return queQuan; }
    public void setQueQuan(String queQuan) { this.queQuan = queQuan; }

    @Override
    public String toString() {
        return this.hoTen + " - " + this.maSinhVien;
    }
}
