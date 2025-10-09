package model.entity;

public class HoaDon {
    private String maHoaDon;
    private Double tongTien;
    private Boolean tinhTrang;
    private HopDong hopDong;
    private SinhVien sinhVien;
    
    // Constructor
    public HoaDon(String maHoaDon, Double tongTien, Boolean tinhTrang, HopDong hopDong, SinhVien sinhVien) { this.maHoaDon = maHoaDon; this.tongTien = tongTien; this.tinhTrang = tinhTrang; this.hopDong = hopDong; this.sinhVien = sinhVien; }
    
    // Getters
    public String getMaHoaDon() { return maHoaDon; }
    public Double getTongTien() { return tongTien; }
    public Boolean getTinhTrang() { return tinhTrang; }
    public HopDong getHopDong() { return hopDong; }
    public SinhVien getSinhVien() { return sinhVien; }
    
    // Setters
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }
    public void setTongTien(Double tongTien) { this.tongTien = tongTien; }
    public void setTinhTrang(Boolean tinhTrang) { this.tinhTrang = tinhTrang; }
    public void setHopDong(HopDong hopDong) { this.hopDong = hopDong; }
    public void setSinhVien(SinhVien sinhVien) { this.sinhVien = sinhVien; }
}