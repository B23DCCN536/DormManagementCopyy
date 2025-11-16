package com.dorm.model;

public class Toa {
    private String maToa;
    private String tenToa;
    private String ghiChu;

    public Toa() {}

    public Toa(String maToa, String tenToa, String ghiChu) {
        this.maToa = maToa;
        this.tenToa = tenToa;
        this.ghiChu = ghiChu;
    }

    public java.util.List<Phong> layDanhSachPhong() {
        return new java.util.ArrayList<>();
    }

    public String getMaToa() { return maToa; }
    public void setMaToa(String maToa) { this.maToa = maToa; }

    public String getTenToa() { return tenToa; }
    public void setTenToa(String tenToa) { this.tenToa = tenToa; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    @Override
    public String toString() {
        return this.tenToa;
    }
}
