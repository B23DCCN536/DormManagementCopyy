package model.entity;

public class HoaDon {
    private String MaHoaDon;
    private Double TongTien;
    private Boolean TinhTrang;
    private HopDong hd;
    private SinhVien sv;
    public HoaDon(String MaHoaDon, Double TongTien, Boolean TinhTrang, HopDong hd, SinhVien sv){
        this.MaHoaDon = MaHoaDon;
        this.TongTien = TongTien;
        this.TinhTrang = TinhTrang;
        this.hd = hd;
        this.sv = sv;
    } 
    public String getMaHoaDon(){
        return MaHoaDon;
    }
    public Double getTongTien(){
        return TongTien;
    }
    public Boolean getTinhTrang(){
        return TinhTrang;
    }
    public HopDong getHopDong(){
        return hd;
    }
    public SinhVien getSinhVien(){
        return sv;
    }
    public void setMaHoaDon(String MaHoaDon){
        this.MaHoaDon = MaHoaDon;
    }
    public void setTongTien(Double TongTien){
        this.TongTien = TongTien;
    }
    public void setTinhTrang(Boolean TinhTrang){
        this.TinhTrang = TinhTrang;
    }
    public void XemHoaDon(){

    }
    public void XuatHoaDon(){

    }
}