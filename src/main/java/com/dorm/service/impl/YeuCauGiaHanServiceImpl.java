package com.dorm.service.impl;

import com.dorm.database.DatabaseManager;
import com.dorm.model.YeuCauGiaHan;
import com.dorm.service.YeuCauGiaHanService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class YeuCauGiaHanServiceImpl implements YeuCauGiaHanService {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public boolean createRequest(YeuCauGiaHan request) {
        String sql = "INSERT INTO YeuCauGiaHan (maHopDong, maSinhVien, soThang, tongTien, ngayGui, trangThai) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, request.getMaHopDong());
            stmt.setString(2, request.getMaSinhVien());
            stmt.setInt(3, request.getSoThang());
            stmt.setDouble(4, request.getTongTien());
            stmt.setDate(5, Date.valueOf(request.getNgayGui()));
            stmt.setString(6, request.getTrangThai());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<YeuCauGiaHan> getAllRequests() {
        List<YeuCauGiaHan> list = new ArrayList<>();
        String sql = "SELECT * FROM YeuCauGiaHan ORDER BY ngayGui DESC";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToRequest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<YeuCauGiaHan> getRequestsByStudent(String maSinhVien) {
        List<YeuCauGiaHan> list = new ArrayList<>();
        String sql = "SELECT * FROM YeuCauGiaHan WHERE maSinhVien = ? ORDER BY ngayGui DESC";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSinhVien);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToRequest(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateStatus(int maYeuCau, String newStatus) {
        String sql = "UPDATE YeuCauGiaHan SET trangThai = ? WHERE maYeuCau = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, maYeuCau);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public YeuCauGiaHan getPendingRequest(String maSinhVien) {
        String sql = "SELECT TOP 1 * FROM YeuCauGiaHan WHERE maSinhVien = ? AND trangThai = 'CHO_DUYET'";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSinhVien);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToRequest(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private YeuCauGiaHan mapResultSetToRequest(ResultSet rs) throws SQLException {
        return new YeuCauGiaHan(
            rs.getInt("maYeuCau"),
            rs.getString("maHopDong"),
            rs.getString("maSinhVien"),
            rs.getInt("soThang"),
            rs.getDouble("tongTien"),
            rs.getDate("ngayGui").toLocalDate(),
            rs.getString("trangThai")
        );
    }
}
