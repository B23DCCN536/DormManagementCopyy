package model.entity;

import java.util.TreeMap;

public class SinhVien {
    private String MaSinhVien;
    private String HoTen;
    private String Lop;
    private String GioiTinh;
    private String SDT;

    //constructor
    public SinhVien(String MaSinhVien, String HoTen, String Lop, String GioiTinh, String SDT){
        this.MaSinhVien = MaSinhVien;
        this.HoTen = HoTen;
        this.Lop = Lop;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
    }

    //getters
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

    //setters
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

    //methods
    public static void ThemSinhVien(TreeMap<String, SinhVien> danhSachSV, SinhVien sv) {
        try {
            if (danhSachSV == null || sv == null) 
                throw new IllegalArgumentException("Tham số không được null");
            else if (sv.getMaSinhVien() == null || sv.getMaSinhVien().trim().isEmpty()) 
                throw new IllegalArgumentException("Mã sinh viên không hợp lệ");
            else if (danhSachSV.containsKey(sv.getMaSinhVien())) 
                System.out.println("Sinh viên đã tồn tại.");
            else danhSachSV.put(sv.getMaSinhVien(), sv);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    public static void XoaSinhVien(TreeMap<String, SinhVien> danhSachSV, String maSV){
        try {
            if (danhSachSV == null) 
                throw new IllegalArgumentException("Danh sách sinh viên không được để trống");
            if (maSV == null || maSV.trim().isEmpty()) 
                throw new IllegalArgumentException("Mã sinh viên không hợp lệ");
            if (!danhSachSV.containsKey(maSV)) {
                System.out.println("Không tìm thấy sinh viên ");
            }
            danhSachSV.remove(maSV);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    public void SuaThongTinSinhVien(SinhVien sv, String HoTen, String Lop, String GioiTinh, String SDT){
        if (sv == null) {
            System.out.println("Không được để trống");
            return;
        }
        if (HoTen != null && !HoTen.trim().isEmpty() && Lop != null && !Lop.trim().isEmpty() && GioiTinh != null && !GioiTinh.trim().isEmpty() && SDT != null && !SDT.trim().isEmpty()) {
            sv.setHoVaTen(HoTen);
            sv.setLop(Lop);
            sv.setGioiTinh(GioiTinh);
            sv.setSDT(SDT);
        }
        else {
            System.out.println("Thông tin không hợp lệ");
        }
    }
    public void XemThongTinSinhVien(){
        System.out.println("Ma sinh vien: " + MaSinhVien);
        System.out.println("Ho va ten: " + HoTen);
        System.out.println("Lop: " + Lop);
        System.out.println("Gioi tinh: " + GioiTinh);
        System.out.println("SDT: " + SDT);
    }
}