package com.dorm.service.impl;

import com.dorm.database.DatabaseManager;
import com.dorm.model.Phong;
import com.dorm.model.enums.TrangThaiPhong;
import com.dorm.service.PhongService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongServiceImpl implements PhongService {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public List<Phong> getAllPhong() {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM Phong";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToPhong(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Phong> getPhongTrong() {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM Phong WHERE trangThai = 'TRONG'";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToPhong(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Phong getPhongById(String maPhong) {
        String sql = "SELECT * FROM Phong WHERE maPhong = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maPhong);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPhong(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Phong mapResultSetToPhong(ResultSet rs) throws SQLException {
        return new Phong(
            rs.getString("maPhong"),
            rs.getString("maToa"),
            rs.getString("maLoaiPhong"),
            rs.getString("tenPhong"),
            rs.getInt("soNguoiHienTai"),
            rs.getString("gioitinh"),
            TrangThaiPhong.valueOf(rs.getString("trangThai"))
        );
    }

    @Override
    public boolean updateSoNguoi(String maPhong, int delta) {
        String sql = "UPDATE Phong SET soNguoiHienTai = soNguoiHienTai + ? WHERE maPhong = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, delta);
            stmt.setString(2, maPhong);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
