package com.dorm.service.impl;

import com.dorm.database.DatabaseManager;
import com.dorm.model.Admin;
import com.dorm.service.AdminService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public Admin getAdminByUsername(String username) {
        String sql = "SELECT * FROM Admin WHERE username = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                        rs.getString("username"),
                        rs.getString("hoTen"),
                        rs.getString("sdt"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
