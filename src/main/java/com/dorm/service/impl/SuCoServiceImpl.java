package com.dorm.service.impl;

import com.dorm.database.DatabaseManager;
import com.dorm.model.SuCo;
import com.dorm.model.enums.TrangThaiSuCo;
import com.dorm.service.SuCoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuCoServiceImpl implements SuCoService {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public List<SuCo> getAllSuCo() {
        List<SuCo> list = new ArrayList<>();
        String sql = "SELECT * FROM SuCo ORDER BY ngayBaoCao DESC";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToSuCo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<SuCo> getSuCoBySinhVien(String maSinhVien) {
        List<SuCo> list = new ArrayList<>();
        String sql = "SELECT * FROM SuCo WHERE maSinhVien = ? ORDER BY ngayBaoCao DESC";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSinhVien);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToSuCo(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addSuCo(SuCo suCo) {
        String sql = "INSERT INTO SuCo (maSuCo, maPhong, maSinhVien, tieuDe, noiDung, ngayBaoCao, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, suCo.getMaSuCo());
            stmt.setString(2, suCo.getMaPhong());
            stmt.setString(3, suCo.getMaSinhVien());
            stmt.setString(4, suCo.getTieuDe());
            stmt.setString(5, suCo.getNoiDung());
            stmt.setDate(6, java.sql.Date.valueOf(suCo.getNgayBao()));
            stmt.setString(7, suCo.getTrangThai().toString());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStatus(String maSuCo, TrangThaiSuCo trangThai) {
        String sql = "UPDATE SuCo SET trangThai = ? WHERE maSuCo = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trangThai.toString());
            stmt.setString(2, maSuCo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private SuCo mapResultSetToSuCo(ResultSet rs) throws SQLException {
        return new SuCo(
            rs.getString("maSuCo"),
            rs.getString("maPhong"),
            rs.getString("maSinhVien"),
            rs.getString("tieuDe"),
            rs.getString("noiDung"),
            rs.getDate("ngayBaoCao").toLocalDate(),
            TrangThaiSuCo.valueOf(rs.getString("trangThai"))
        );
    }
}
