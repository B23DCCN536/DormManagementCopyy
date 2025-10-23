package model.entity;

public class HopDong {
    private String maHopDong;
    private String ngayBatDau;
    private String ngayKetThuc;
    private Boolean trangThai;
    private SinhVien sinhVien;
    private Phong phong;
    
    // Constructor
    public HopDong(String maHopDong, String ngayBatDau, String ngayKetThuc, Boolean trangThai, SinhVien sinhVien, Phong phong) { this.maHopDong = maHopDong; this.ngayBatDau = ngayBatDau; this.ngayKetThuc = ngayKetThuc; this.trangThai = trangThai; this.sinhVien = sinhVien; this.phong = phong; }
    
    // Getters
    public String getMaHopDong() { return maHopDong; }
    public String getNgayBatDau() { return ngayBatDau; }
    public String getNgayKetThuc() { return ngayKetThuc; }
    public Boolean getTrangThai() { return trangThai; }
    public SinhVien getSinhVien() { return sinhVien; }
    public Phong getPhong() { return phong; }
    
    // Setters
    public void setMaHopDong(String maHopDong) { this.maHopDong = maHopDong; }
    public void setNgayBatDau(String ngayBatDau) { this.ngayBatDau = ngayBatDau; }
    public void setNgayKetThuc(String ngayKetThuc) { this.ngayKetThuc = ngayKetThuc; }
    public void setTrangThai(Boolean trangThai) { this.trangThai = trangThai; }
    public void setSinhVien(SinhVien sinhVien) { this.sinhVien = sinhVien; }
    public void setPhong(Phong phong) { this.phong = phong; }
}
