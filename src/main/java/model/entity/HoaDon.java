package model.entity;

import BaiToanTuyenSinh.SinhVien;

public class HoaDon {
	private String MaHoaDon;
    private Double TongTien;
    private Boolean TinhTrang;
    private SinhVien sv;

    //constructor
    public HoaDon(String MaHoaDon, Double TongTien, Boolean TinhTrang, SinhVien sv){
        this.MaHoaDon = MaHoaDon;
        this.TongTien = TongTien;
        this.TinhTrang = TinhTrang;
        this.sv = sv;
    }

    //getters
    public String getMaHoaDon(){
        return MaHoaDon;
    }
    public Double getTongTien(){
        return TongTien;
    }
    public Boolean getTinhTrang(){
        return TinhTrang;
    }
    public SinhVien getSinhVien(){
        return sv;
    }

    //setters
    public void setMaHoaDon(String MaHoaDon){
        if (!MaHoaDon.equals("")) this.MaHoaDon = MaHoaDon;
    }
    public void setTongTien(Double TongTien){
        if (TongTien > 0) this.TongTien = TongTien;
    }
    public void setTinhTrang(Boolean TinhTrang){
        this.TinhTrang = TinhTrang;
    }

    //methods
    public void XemHoaDon(){
        System.out.printf("Ma hoa don: %s, Tong tien: %.2f, Tinh trang: %s\n",
                this.MaHoaDon, this.TongTien, this.TinhTrang ? "Da thanh toan" : "Chua thanh toan");
    }
    public void XuatHoaDon(){

    }
}