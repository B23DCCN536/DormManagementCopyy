package com.dorm.model;

import com.dorm.model.enums.QuyenTruyCap;

public class TaiKhoan {
    private String username;
    private String password;
    private QuyenTruyCap role;

    public TaiKhoan() {}

    public TaiKhoan(String username, String password, QuyenTruyCap role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean dangNhap(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void doiMatKhau(String mkCu, String mkMoi) {
        if (dangNhap(mkCu)) {
            this.password = mkMoi;
        }
    }

    public boolean kiemTraQuyen(QuyenTruyCap quyen) {
        return this.role == quyen;
    }

    public boolean isAdmin() {
        return this.role == QuyenTruyCap.ADMIN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public QuyenTruyCap getRole() {
        return role;
    }

    public void setRole(QuyenTruyCap role) {
        this.role = role;
    }
}
