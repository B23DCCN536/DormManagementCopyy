package model.entity;

public class Phong {
    private String maPhong;
    private Boolean tinhTrang;
    private int loaiPhong;
    private double giaPhong;
    
    // Constructor
    public Phong(String maPhong, Boolean tinhTrang, int loaiPhong, double giaPhong) { this.maPhong = maPhong; this.tinhTrang = tinhTrang; this.loaiPhong = loaiPhong; this.giaPhong = giaPhong; }
    
    // Getters
    public String getMaPhong() { return maPhong; }
    public Boolean getTinhTrang() { return tinhTrang; }
    public int getLoaiPhong() { return loaiPhong; }
    public double getGiaPhong() { return giaPhong; }
    
    // Setters
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }
    public void setTinhTrang(Boolean tinhTrang) { this.tinhTrang = tinhTrang; }
    public void setLoaiPhong(int loaiPhong) { this.loaiPhong = loaiPhong; }
    public void setGiaPhong(double giaPhong) { this.giaPhong = giaPhong; }
}