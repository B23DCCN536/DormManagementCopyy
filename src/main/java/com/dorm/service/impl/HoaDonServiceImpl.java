package com.dorm.service.impl;

import com.dorm.database.DatabaseManager;
import com.dorm.model.HoaDon;
import com.dorm.service.HoaDonService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonServiceImpl implements HoaDonService {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon ORDER BY ngayLap DESC";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToHoaDon(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<HoaDon> getHoaDonBySinhVien(String maSinhVien) {
        // Note: HoaDon is linked to Phong, not directly to SinhVien in the new ERD.
        // But we might need to join tables to find invoices for a student's room.
        // For now, let's assume we can find it via room or maybe the old logic was different.
        // The ERD says HoaDon has maPhong. SinhVien has maPhong (via HopDong or directly?).
        // Let's assume we query by maPhong if we had it, but here we have maSinhVien.
        // We need to find the room of the student first.
        // For simplicity, I will return an empty list or implement a join if needed.
        // Let's try to join with HopDong to get MaPhong.
        
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT hd.* FROM HoaDon hd " +
                     "JOIN HopDong h ON hd.maPhong = h.maPhong " +
                     "WHERE h.maSinhVien = ? AND h.trangThai = 'HIEU_LUC' " +
                     "ORDER BY hd.ngayLap DESC";
        
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSinhVien);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToHoaDon(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private HoaDon mapResultSetToHoaDon(ResultSet rs) throws SQLException {
        return new HoaDon(
            rs.getString("maHoaDon"),
            rs.getString("maPhong"),
            rs.getString("maChiSo"),
            rs.getInt("thang"),
            rs.getInt("nam"),
            rs.getDate("ngayLap").toLocalDate(),
            rs.getDouble("tienDien"),
            rs.getDouble("tienNuoc"),
            rs.getDouble("tongTien"),
            rs.getString("trangThai"),
            rs.getDate("ngayDong") != null ? rs.getDate("ngayDong").toLocalDate() : null,
            rs.getString("nguoiNopTien")
        );
    }
}
