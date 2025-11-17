package com.dorm.model;

import com.dorm.model.enums.TrangThaiSuCo;
import java.time.LocalDate;

public class SuCo {
    private String maSuCo;
    private String maPhong;
    private String maSinhVien;
    private String tieuDe;
    private String noiDung;
    private LocalDate ngayBao; 
    private TrangThaiSuCo trangThai;

    public SuCo() {}

    public SuCo(String maSuCo, String maPhong, String maSinhVien, String tieuDe, String noiDung, LocalDate ngayBao, TrangThaiSuCo trangThai) {
        this.maSuCo = maSuCo;
        this.maPhong = maPhong;
        this.maSinhVien = maSinhVien;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayBao = ngayBao;
        this.trangThai = trangThai;
    }

    public String getMaSuCo() {
        return maSuCo;
    }

    public void setMaSuCo(String maSuCo) {
        this.maSuCo = maSuCo;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LocalDate getNgayBao() {
        return ngayBao;
    }

    public void setNgayBao(LocalDate ngayBao) {
        this.ngayBao = ngayBao;
    }

    public TrangThaiSuCo getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiSuCo trangThai) {
        this.trangThai = trangThai;
    }
    
    public void capNhatTrangThai(TrangThaiSuCo newStatus) {
        this.trangThai = newStatus;
    }

    // For Controller compatibility
    public String getMoTa() {
        return tieuDe + " - " + noiDung;
    }
}
