package model.entity;

import java.util.TreeSet;

public class DichVu {
    private String TenDichVu;
    private String MoTa;
    private double DonGia;
    private Boolean TrangThai;
    
    public DichVu(String TenDichVu, String MoTa, double DonGia, Boolean TrangThai){
        this.TenDichVu = TenDichVu;
        this.MoTa = MoTa;
        this.DonGia = DonGia;
        this.TrangThai = TrangThai;
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
    
    public void setTenDichVu(String TenDichVu){
        if (!TenDichVu.equals("")) this.TenDichVu = TenDichVu;
    }
    public void setMoTa(String MoTa){
        if (!MoTa.equals("")) this.MoTa = MoTa;
    }
    public void setDonGia(double DonGia){
        if (DonGia != 0) this.DonGia = DonGia;
    }
    public void setTrangThai(Boolean TrangThai){
        this.TrangThai = TrangThai;
    }

    public void ThemDichVu(TreeSet<DichVu> dsDichVu, DichVu dvMoi){
        dsDichVu.add(dvMoi);
    }
    public void XoaDichVu(TreeSet<DichVu> dsDichVu, DichVu dvXoa){
        dsDichVu.remove(dvXoa);
    }
    public void SuaDichVu(String tenDichVu, String moTa, double donGia, Boolean trangThai){
        setTenDichVu(tenDichVu);
        setMoTa(moTa);
        setDonGia(donGia);
        setTrangThai(trangThai);
    }
    public void XemDichVu(){
        System.out.printf("Dich vu: %s, Mo ta: %s, Don gia: %.2f, Trang thai: %b\n",
                this.TenDichVu, this.MoTa, this.DonGia, this.TrangThai);
    }
}