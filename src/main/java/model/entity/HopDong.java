package model.entity;

import java.util.TreeMap;

import TempCodeRunner.HoaDon;

public class HopDong {
    private String MaHopDong;
    private String NgayBatDau;
    private String NgayKetThuc;
    private Boolean TrangThai;
    private HoaDon hoadon;

    //constructor
    public HopDong(String MaHopDong, String NgayBatDau, String NgayKetThuc, Boolean TrangThai, HoaDon hd){
        this.MaHopDong = MaHopDong;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.TrangThai = TrangThai;
        this.hoadon = hd;
    }

    //getters
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
    public HoaDon getHoaDon(){
        return hoadon;
    }

    //setters
    public void setMaHopDong(String MaHopDong){
        if (!MaHopDong.equals("")) this.MaHopDong = MaHopDong;
    }
    public void setNgayBatDau(String NgayBatDau){
        if (!NgayBatDau.equals("")) this.NgayBatDau = NgayBatDau;
    }
    public void setNgayKetThuc(String NgayKetThuc){
        if (!NgayKetThuc.equals("")) this.NgayKetThuc = NgayKetThuc;
    }
    public void setTrangThai(Boolean TrangThai){
        this.TrangThai = TrangThai;
    }

    //methods
    public void ThemHopDong(TreeMap<String, HopDong> dsHopDong, HopDong hd){
        if (dsHopDong == null || hd == null) 
            System.out.println("Không được để trống");
        else if (hd.getMaHopDong() == null || hd.getMaHopDong().trim().isEmpty()) 
            System.out.println("Mã hợp đồng không hợp lệ");
        else if (dsHopDong.containsKey(hd.getMaHopDong()))
            System.out.println("Hợp đồng đã tồn tại.");
        else dsHopDong.put(hd.getMaHopDong(), hd);
    }
    public void XoaHopDong(TreeMap<String, HopDong> dsHopDong, String maHD){
        if (dsHopDong == null) 
            System.out.println("Danh sách hợp đồng trống");
        else if (!dsHopDong.containsKey(maHD))
            System.out.println("Hợp đồng không tồn tại.");
        else dsHopDong.remove(maHD);
    }
    public void SuaHopDong(String MaHopDong, String NgayBatDau, String NgayKetThuc, Boolean TrangThai){
        if (MaHopDong != null && !MaHopDong.trim().isEmpty() ) this.MaHopDong = MaHopDong;
        if (NgayBatDau != null && !NgayBatDau.trim().isEmpty() ) this.NgayBatDau = NgayBatDau;
        if (NgayKetThuc != null && !NgayKetThuc.trim().isEmpty() ) this.NgayKetThuc = NgayKetThuc;
        if (TrangThai != null ) this.TrangThai = TrangThai;
    }
    public void XemThongTinHopDong(){
        System.out.println("Mã hợp đồng: " + MaHopDong);
        System.out.println("Ngày bắt đầu: " + NgayBatDau);
        System.out.println("Ngày kết thúc: " + NgayKetThuc);
        System.out.println("Trạng thái: " + (TrangThai ? "Còn hiệu lực" : "Đã kết thúc"));
    }
}
