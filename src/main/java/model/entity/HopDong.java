package model.entity;

public class HopDong {
    private String MaHopDong;
    private String NgayBatDau;
    private String NgayKetThuc;
    private Boolean TrangThai;
    private SinhVien sv;
    private Phong p;
    public HopDong(String MaHopDong, String NgayBatDau, String NgayKetThuc, Boolean TrangThai, SinhVien sv, Phong p){
        this.MaHopDong = MaHopDong;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.TrangThai = TrangThai;
        this.sv = sv;
        this.p = p;
    }
    public String getMaHopDong(){
        return MaHopDong;
    }
    public String getNgayBatDau(){
        return NgayBatDau;
    } 
    public String getNgayKetThuc(){
        return NgayKetThuc;
    }
    public Boolean getTrangThai(){
        return TrangThai;
    }
    public SinhVien getSinhVien(){
        return sv;
    }
    public Phong getPhong(){
        return p;
    }
    public void setMaHopDong(String MaHopDong){
        this.MaHopDong = MaHopDong;
    }
    public void setNgayBatDau(String NgayBatDau){
        this.NgayBatDau = NgayBatDau;
    }
    public void setNgayKetThuc(String NgayKetThuc){
        this.NgayKetThuc = NgayKetThuc;
    }
    public void setTrangThai(Boolean TrangThai){
        this.TrangThai = TrangThai;
    }
    public void ThemHopDong(){

    }
    public void XoaHopDong(){

    }
    public void SuaHopDong(){

    }
    public void XemHopDong(){

    }
}