package model.entity;

public class Phong {
    private String MaPhong;
    private Boolean TinhTrang;
    private int LoaiPhong;
    private double GiaPhong;
    public Phong(String MaPhong, Boolean TinhTrang, int LoaiPhong, double GiaPhong){
        this.MaPhong = MaPhong;
        this.TinhTrang = TinhTrang;
        this.LoaiPhong = LoaiPhong;
        this.GiaPhong = GiaPhong;
    }
    public String getMaPhong(){
        return MaPhong;
    }
    public Boolean getTinhTrang(){
        return TinhTrang;
    }
    public int getLoaiPhong(){
        return LoaiPhong;
    }
    public double getGiaPhong(){
        return GiaPhong;
    }
    public void setMaPhong(String MaPhong){
        if (!MaPhong.equals("")) this.MaPhong = MaPhong;
    }
    public void setTinhTrang(Boolean TinhTrang){
        this.TinhTrang = TinhTrang;
    }
    public void setLoaiPhong(int LoaiPhong){
        if (LoaiPhong >= 0) this.LoaiPhong = LoaiPhong;
    }
    public void setGiaPhong(double GiaPhong){
        if (GiaPhong > 0) this.GiaPhong = GiaPhong;
    }
    public void ThemPhong(){

    }
    public void XoaPhong(){

    }
    public void SuaThongTinPhong(){

    }
    public void XemThongTinPhong(){

    }
}