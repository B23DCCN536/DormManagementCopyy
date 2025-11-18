package com.dorm.service.impl;

import com.dorm.database.DatabaseManager;
import com.dorm.model.TaiKhoan;
import com.dorm.model.enums.QuyenTruyCap;
import com.dorm.service.TaiKhoanService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanServiceImpl implements TaiKhoanService {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public TaiKhoan login(String username, String password) {
        String sql = "SELECT * FROM TaiKhoan WHERE username = ? AND password = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String roleStr = rs.getString("role");
                    QuyenTruyCap role = QuyenTruyCap.valueOf(roleStr);
                    return new TaiKhoan(
                        rs.getString("username"),
                        rs.getString("password"),
                        role
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid role in database: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        String sql = "UPDATE TaiKhoan SET password = ? WHERE username = ? AND password = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            stmt.setString(3, oldPassword);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createAccount(String username, String password, String role) {
        String sql = "INSERT INTO TaiKhoan (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAccount(String username) {
        String sql = "DELETE FROM TaiKhoan WHERE username = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
