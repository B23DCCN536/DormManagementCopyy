package model.entity;

public class HoaDon {
	private String MaHoaDon;
    private Double TongTien;
    private Boolean TinhTrang;

    //constructor
    public HoaDon(String MaHoaDon, Double TongTien, Boolean TinhTrang, HopDong hd, SinhVien sv){
        this.MaHoaDon = MaHoaDon;
        this.TongTien = TongTien;
        this.TinhTrang = TinhTrang;
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

    }
    public void XuatHoaDon(){

    }
}