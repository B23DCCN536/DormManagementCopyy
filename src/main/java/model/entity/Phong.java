package model.entity;
import java.util.ArrayList;
import java.util.TreeMap;

public class Phong {
    private String MaPhong;
    private Boolean TinhTrang;
    private int LoaiPhong;
    private double GiaPhong;
    private ArrayList<SinhVien> danhSachSinhVien;
    public Phong(String MaPhong, int LoaiPhong, double GiaPhong){
        this.MaPhong = MaPhong;
        this.TinhTrang = true;
        this.LoaiPhong = LoaiPhong;
        this.GiaPhong = GiaPhong;
        this.danhSachSinhVien = new ArrayList<>();
    }

    // Getters
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

    // Setters
    public void setMaPhong(String MaPhong){
        if (!MaPhong.equals("")) this.MaPhong = MaPhong;
    }
    public void setTinhTrang(Boolean TinhTrang){
        this.TinhTrang = TinhTrang;
    }
    public void setLoaiPhong(int LoaiPhong){
        if (LoaiPhong >= 0 && LoaiPhong <= 2) this.LoaiPhong = LoaiPhong;
    }
    public void setGiaPhong(double GiaPhong){
        if (GiaPhong > 0) this.GiaPhong = GiaPhong;
    }
    
    // Methods
    public static void ThemPhong(TreeMap<String, Phong> danhSachPhong, Phong p){
        if (!danhSachPhong.containsKey(p.getMaPhong())){
            danhSachPhong.put(p.getMaPhong(), p);
        }
    }
    public static void XoaPhong(TreeMap<String, Phong> danhSachPhong, String maPhong){
        if (danhSachPhong.containsKey(maPhong)){
            danhSachPhong.remove(maPhong);
        }
    }
    public static void ThemSinhVienVaoPhong(Phong p, SinhVien sv, Toa t){
        if (p.getTinhTrang() == true){
            p.danhSachSinhVien.add(sv);
        }
        else System.out.println("Phong da day, khong the them sinh vien");
        if (p.danhSachSinhVien.size() == t.getSoNguoiMoiPhong())
            p.setTinhTrang(false);
    }
    public static void XoaSinhVienKhoiPhong(Phong p, SinhVien sv){
        if (p.danhSachSinhVien.contains(sv))
            p.danhSachSinhVien.remove(sv);
        else System.out.println("Khong tim thay sinh vien trong phong");
    }
    public void XemDanhSachSinhVien(){
        System.out.println("Danh sach sinh vien trong phong " + MaPhong + ":");
        for (SinhVien sv : danhSachSinhVien){
            sv.XemThongTinSinhVien();
        }
    }
    public void XemThongTinPhong(){
        System.out.println("Thong tin phong:");
        System.out.println("Ma phong: " + MaPhong);
        System.out.println("Tinh trang: " + (TinhTrang ? "Con trong" : "Da day"));
        System.out.println("Loai phong: " + LoaiPhong);
        System.out.println("Gia phong: " + GiaPhong);
        System.out.println("So luong sinh vien hien tai: " + danhSachSinhVien.size());
    }
}