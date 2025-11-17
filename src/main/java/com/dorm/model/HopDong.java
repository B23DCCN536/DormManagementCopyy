package com.dorm.model;

import com.dorm.model.enums.TrangThaiHopDong;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class HopDong {
    private String maHopDong;
    private String maSinhVien;
    private String maPhong;
    private double giaPhong;
    private LocalDate ngayLap;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private TrangThaiHopDong trangThai;

    public HopDong() {}

    public HopDong(String maHopDong, String maSinhVien, String maPhong, double giaPhong, LocalDate ngayLap, LocalDate ngayBatDau, LocalDate ngayKetThuc, TrangThaiHopDong trangThai) {
        this.maHopDong = maHopDong;
        this.maSinhVien = maSinhVien;
        this.maPhong = maPhong;
        this.giaPhong = giaPhong;
        this.ngayLap = ngayLap;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public boolean isConHieuLuc() {
        LocalDate today = LocalDate.now();
        return ((today.isAfter(this.ngayBatDau) || today.isEqual(this.ngayBatDau)) && 
                (today.isBefore(this.ngayKetThuc) || today.isEqual(this.ngayKetThuc)) && 
                this.trangThai == TrangThaiHopDong.HIEU_LUC);
    }

    public long getSoNgayConLai() {
        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(today, this.ngayKetThuc);
    }

    public void giaHan(int soThang) {
        this.ngayKetThuc = this.ngayKetThuc.plusMonths(soThang);
        this.trangThai = TrangThaiHopDong.HIEU_LUC;
    }

    public void thanhLy() {
        this.trangThai = TrangThaiHopDong.DA_THANH_LY;
        this.ngayKetThuc = LocalDate.now();
    }

    public String getMaHopDong() { return maHopDong; }
    public void setMaHopDong(String maHopDong) { this.maHopDong = maHopDong; }

    public String getMaSinhVien() { return maSinhVien; }
    public void setMaSinhVien(String maSinhVien) { this.maSinhVien = maSinhVien; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public double getGiaPhong() { return giaPhong; }
    public void setGiaPhong(double giaPhong) { this.giaPhong = giaPhong; }

    public LocalDate getNgayLap() { return ngayLap; }
    public void setNgayLap(LocalDate ngayLap) { this.ngayLap = ngayLap; }

    public LocalDate getNgayBatDau() { return ngayBatDau; }
    public void setNgayBatDau(LocalDate ngayBatDau) { this.ngayBatDau = ngayBatDau; }

    public LocalDate getNgayKetThuc() { return ngayKetThuc; }
    public void setNgayKetThuc(LocalDate ngayKetThuc) { this.ngayKetThuc = ngayKetThuc; }

    public TrangThaiHopDong getTrangThai() { return trangThai; }
    public void setTrangThai(TrangThaiHopDong trangThai) { this.trangThai = trangThai; }
}
