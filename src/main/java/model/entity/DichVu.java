package model.entity;

public class DichVu {
    private String TenDichVu;
    private String MoTa;
    private double DonGia;
    private Boolean TrangThai;
    private Phong p;
    public DichVu(String TenDichVu, String MoTa, double DonGia, Boolean TrangThai, Phong p){
        this.TenDichVu = TenDichVu;
        this.MoTa = MoTa;
        this.DonGia = DonGia;
        this.TrangThai = TrangThai;
        this.p = p;
    }
    public String getTenDichVu(){
        return TenDichVu;
    }
    public String getMoTa(){
        return MoTa;
    }
    public double getDonGia(){
        return DonGia;
    }
    public Boolean getTrangThai(){
        return TrangThai;
    }
    public Phong getPhong(){
        return p;
    }
    public void setTenDichVu(String TenDichVu){
        if (!TenDichVu.equals("")) this.TenDichVu = TenDichVu;
    }
    public void setMoTa(String MoTa){
        this.MoTa = MoTa;
    }
    public void setDonGia(double DonGia){
        this.DonGia = DonGia;
    }
    public void setTrangThai(Boolean TrangThai){
        this.TrangThai = TrangThai;
    }
    public void ThemDichVu(){

    }
    public void XoaDichVu(){

    }
    public void SuaDichVu(){

    }
    public void XemDichVu(){

    }
}