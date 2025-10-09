package model.entity;

public class SinhVien {
    private String maSinhVien;
    private String hoTen;
    private String lop;
    private String gioiTinh;
    private String sdt;

    // Constructor
    public SinhVien(String maSinhVien, String hoTen, String lop, String gioiTinh, String sdt) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.lop = lop;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
    }

    // Getters
    public String getMaSinhVien() { return maSinhVien; }
    public String getHoTen() { return hoTen; }
    public String getLop() { return lop; }
    public String getGioiTinh() { return gioiTinh; }
    public String getSdt() { return sdt; }

    // Setters
    public void setMaSinhVien(String maSinhVien) { this.maSinhVien = maSinhVien; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public void setLop(String lop) { this.lop = lop; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    public void setSdt(String sdt) { this.sdt = sdt; }
}
