package model.entity;

import java.util.TreeMap;
public class SinhVien {
    private String MaSinhVien;
    private String HoTen;
    private String Lop;
    private String GioiTinh;
    private String SDT;

    // Constructor
    public SinhVien(String maSinhVien, String hoTen, String lop, String gioiTinh, String sdt) {
        this.MaSinhVien = maSinhVien;
        this.HoTen = hoTen;
        this.Lop = lop;
        this.GioiTinh = gioiTinh;
        this.SDT = sdt;
    }

    // Getters
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
    

    // Setters
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

    // Methods
    public static void ThemSinhVien(TreeMap<String, SinhVien> danhSachSV, SinhVien sv) {
        try {
            if (danhSachSV == null || sv == null) 
                throw new IllegalArgumentException("Tham số không được null");
            if (sv.getMaSinhVien() == null || sv.getMaSinhVien().trim().isEmpty()) 
                throw new IllegalArgumentException("Mã sinh viên không hợp lệ");
            if (danhSachSV.containsKey(sv.getMaSinhVien())) 
                System.out.println("Sinh viên đã tồn tại.");
            danhSachSV.put(sv.getMaSinhVien(), sv);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    public static void XoaSinhVien(TreeMap<String, SinhVien> danhSachSV, String maSV){
        try {
            if (danhSachSV == null) 
                throw new IllegalArgumentException("Danh sách sinh viên không được null");
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
    public void SuaThongTinSinhVien(){
        
    }
    @Override
    public String toString(){
        return "Ma sinh vien: " + MaSinhVien + ", Ho va ten: " + HoTen + ", Lop: " + Lop + ", Gioi tinh: " + GioiTinh + ", SDT: " + SDT;
    }
}
