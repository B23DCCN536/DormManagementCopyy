package model.entity;

public class SinhVien {
    private String MaSinhVien;
    private String HoTen;
    private String Lop;
    private String GioiTinh;
    private String SDT;
    public SinhVien(String MaSinhVien, String HoTen, String Lop, String GioiTinh, String SDT){
        this.MaSinhVien = MaSinhVien;
        this.HoTen = HoTen;
        this.Lop = Lop;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
    }
    public String getMaSinhVien(){
        return MaSinhVien;
    }
    public String getHoVaTen(){
        return HoTen;
    }
    public String getLop(){
        return Lop;
    }
    public String getGioiTinh(){
        return GioiTinh;
    }
    public String getSDT(){
        return SDT;
    }
    public void setMaSinhVien(String MaSinhVien){
        if (!MaSinhVien.equals("")) this.MaSinhVien = MaSinhVien;
    }
    public void setHoVaTen(String HoTen){
        if (!HoTen.equals("")) this.HoTen = HoTen;
    }
    public void setLop(String Lop){
        if (!Lop.equals(""))this.Lop = Lop;
    }
    public void setGioiTinh(String GioiTinh){
        if (!GioiTinh.equals(""))this.GioiTinh = GioiTinh;
    }
    public void setSDT(String SDT){
        if (!SDT.equals("")) this.SDT = SDT;
    }
    public void ThemSinhVien(){

    }
    public void XoaSinhVien(){

    }
    public void SuaThongTinSinhVien(){

    }
    public void XemThongTinSinhVien(){

    }
}