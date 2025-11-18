package com.dorm.service.impl;

import com.dorm.database.DatabaseManager;
import com.dorm.model.SinhVien;
import com.dorm.service.SinhVienService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinhVienServiceImpl implements SinhVienService {

    @Override
    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMaSinhVien(rs.getString("maSinhVien"));
                sv.setUsername(rs.getString("username"));
                sv.setHoTen(rs.getString("hoTen"));
                sv.setNgaySinh(rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null);
                sv.setGioiTinh(rs.getString("gioiTinh"));
                sv.setLop(rs.getString("lop"));
                sv.setSdt(rs.getString("sdt"));
                sv.setCccd(rs.getString("cccd"));
                sv.setEmail(rs.getString("email"));
                sv.setQueQuan(rs.getString("queQuan"));
                list.add(sv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SinhVien getSinhVienByMaSV(String maSV) {
        String sql = "SELECT * FROM SinhVien WHERE maSinhVien = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSV);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMaSinhVien(rs.getString("maSinhVien"));
                    sv.setUsername(rs.getString("username"));
                    sv.setHoTen(rs.getString("hoTen"));
                    sv.setNgaySinh(rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null);
                    sv.setGioiTinh(rs.getString("gioiTinh"));
                    sv.setLop(rs.getString("lop"));
                    sv.setSdt(rs.getString("sdt"));
                    sv.setCccd(rs.getString("cccd"));
                    sv.setEmail(rs.getString("email"));
                    sv.setQueQuan(rs.getString("queQuan"));
                    return sv;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addSinhVien(SinhVien sv) {
        String sql = "INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sv.getMaSinhVien());
            stmt.setString(2, sv.getUsername());
            stmt.setString(3, sv.getHoTen());
            stmt.setDate(4, sv.getNgaySinh() != null ? java.sql.Date.valueOf(sv.getNgaySinh()) : null);
            stmt.setString(5, sv.getGioiTinh());
            stmt.setString(6, sv.getLop());
            stmt.setString(7, sv.getSdt());
            stmt.setString(8, sv.getCccd());
            stmt.setString(9, sv.getEmail());
            stmt.setString(10, sv.getQueQuan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSinhVien(SinhVien sv) {
        String sql = "UPDATE SinhVien SET hoTen=?, ngaySinh=?, gioiTinh=?, lop=?, sdt=?, cccd=?, email=?, queQuan=? WHERE maSinhVien=?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sv.getHoTen());
            stmt.setDate(2, sv.getNgaySinh() != null ? java.sql.Date.valueOf(sv.getNgaySinh()) : null);
            stmt.setString(3, sv.getGioiTinh());
            stmt.setString(4, sv.getLop());
            stmt.setString(5, sv.getSdt());
            stmt.setString(6, sv.getCccd());
            stmt.setString(7, sv.getEmail());
            stmt.setString(8, sv.getQueQuan());
            stmt.setString(9, sv.getMaSinhVien());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSinhVien(String maSV) {
        String sql = "DELETE FROM SinhVien WHERE maSinhVien = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSV);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SinhVien> searchSinhVien(String keyword) {
        return new ArrayList<>();
    }

    @Override
    public SinhVien getSinhVienByUsername(String username) {
        String sql = "SELECT * FROM SinhVien WHERE username = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMaSinhVien(rs.getString("maSinhVien"));
                    sv.setUsername(rs.getString("username"));
                    sv.setHoTen(rs.getString("hoTen"));
                    sv.setNgaySinh(rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null);
                    sv.setGioiTinh(rs.getString("gioiTinh"));
                    sv.setLop(rs.getString("lop"));
                    sv.setSdt(rs.getString("sdt"));
                    sv.setCccd(rs.getString("cccd"));
                    sv.setEmail(rs.getString("email"));
                    sv.setQueQuan(rs.getString("queQuan"));
                    return sv;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
