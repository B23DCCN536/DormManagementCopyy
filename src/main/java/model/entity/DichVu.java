package model.entity;

public class DichVu {
    private String tenDichVu;
    private String moTa;
    private double donGia;
    private Boolean trangThai;
    private Phong phong;
    
    // Constructor
    public DichVu(String tenDichVu, String moTa, double donGia, Boolean trangThai, Phong phong) { this.tenDichVu = tenDichVu; this.moTa = moTa; this.donGia = donGia; this.trangThai = trangThai; this.phong = phong; }
    
    // Getters
    public String getTenDichVu() { return tenDichVu; }
    public String getMoTa() { return moTa; }
    public double getDonGia() { return donGia; }
    public Boolean getTrangThai() { return trangThai; }
    public Phong getPhong() { return phong; }
    
    // Setters
    public void setTenDichVu(String tenDichVu) { this.tenDichVu = tenDichVu; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    public void setTrangThai(Boolean trangThai) { this.trangThai = trangThai; }
    public void setPhong(Phong phong) { this.phong = phong; }
}