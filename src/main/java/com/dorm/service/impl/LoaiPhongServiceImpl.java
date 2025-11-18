package com.dorm.service.impl;

import com.dorm.database.DatabaseManager;
import com.dorm.model.LoaiPhong;
import com.dorm.service.LoaiPhongService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiPhongServiceImpl implements LoaiPhongService {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public List<LoaiPhong> getAllLoaiPhong() {
        List<LoaiPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiPhong";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new LoaiPhong(
                    rs.getString("maLoaiPhong"),
                    rs.getString("tenLoaiPhong"),
                    rs.getDouble("donGia"),
                    rs.getInt("soNguoiToiDa")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public LoaiPhong getLoaiPhongById(String maLoaiPhong) {
        String sql = "SELECT * FROM LoaiPhong WHERE maLoaiPhong = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maLoaiPhong);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LoaiPhong(
                        rs.getString("maLoaiPhong"),
                        rs.getString("tenLoaiPhong"),
                        rs.getDouble("donGia"),
                        rs.getInt("soNguoiToiDa")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
