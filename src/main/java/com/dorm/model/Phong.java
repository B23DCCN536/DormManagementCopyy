package com.dorm.model;

import com.dorm.model.enums.TrangThaiPhong;

public class Phong {
    private String maPhong;
    private String maToa;
    private String maLoaiPhong;
    private String tenPhong;
    private int soNguoiHienTai;
    private String gioitinh;
    private TrangThaiPhong trangThai;

    public Phong() {}

    public Phong(String maPhong, String maToa, String maLoaiPhong, String tenPhong, int soNguoiHienTai, String gioitinh, TrangThaiPhong trangThai) {
        this.maPhong = maPhong;
        this.maToa = maToa;
        this.maLoaiPhong = maLoaiPhong;
        this.tenPhong = tenPhong;
        this.soNguoiHienTai = soNguoiHienTai;
        this.gioitinh = gioitinh;
        this.trangThai = trangThai;
    }

    public boolean isTrong() {
        return this.trangThai == TrangThaiPhong.TRONG;
    }

    public boolean isDay() {
        return this.trangThai == TrangThaiPhong.DA_DU;
    }

    public void capNhatTrangThai(TrangThaiPhong tt) {
        this.trangThai = tt;
    }

    public boolean themSinhVien(SinhVien sv) {
        // Logic to add student
        this.soNguoiHienTai++;
        return true;
    }

    public void xoaSinhVien(String maSV) {
        // Logic to remove student
        if (this.soNguoiHienTai > 0) {
            this.soNguoiHienTai--;
        }
    }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public String getMaToa() { return maToa; }
    public void setMaToa(String maToa) { this.maToa = maToa; }

    public String getMaLoaiPhong() { return maLoaiPhong; }
    public void setMaLoaiPhong(String maLoaiPhong) { this.maLoaiPhong = maLoaiPhong; }

    public String getTenPhong() { return tenPhong; }
    public void setTenPhong(String tenPhong) { this.tenPhong = tenPhong; }

    public int getSoNguoiHienTai() { return soNguoiHienTai; }
    public void setSoNguoiHienTai(int soNguoiHienTai) { this.soNguoiHienTai = soNguoiHienTai; }

    public String getGioitinh() { return gioitinh; }
    public void setGioitinh(String gioitinh) { this.gioitinh = gioitinh; }

    public TrangThaiPhong getTrangThai() { return trangThai; }
    public void setTrangThai(TrangThaiPhong trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return this.tenPhong;
    }
}
