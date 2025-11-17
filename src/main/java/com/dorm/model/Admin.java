package com.dorm.model;

public class Admin {
    private String username;
    private String hoTen;
    private String sdt;
    private String email;

    public Admin() {
    }

    public Admin(String username, String hoTen, String sdt, String email) {
        this.username = username;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
    }

    public void quanLySinhVien() {
        System.out.println("Admin " + this.hoTen + " đang truy cập chức năng quản lý sinh viên.");
    }

    public void quanLyPhong() {
        System.out.println("Admin " + this.hoTen + " đang truy cập chức năng quản lý phòng.");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}