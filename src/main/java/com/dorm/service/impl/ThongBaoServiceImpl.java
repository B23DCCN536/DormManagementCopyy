package com.dorm.service.impl;

import com.dorm.database.DatabaseManager;
import com.dorm.model.ThongBao;
import com.dorm.service.ThongBaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongBaoServiceImpl implements ThongBaoService {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public List<ThongBao> getAllThongBao() {
        List<ThongBao> list = new ArrayList<>();
        String sql = "SELECT * FROM ThongBao ORDER BY ngayDang DESC";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ThongBao(
                    rs.getString("maThongBao"),
                    rs.getString("tieuDe"),
                    rs.getString("noiDung"),
                    rs.getDate("ngayDang").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addThongBao(ThongBao thongBao) {
        String sql = "INSERT INTO ThongBao (maThongBao, tieuDe, noiDung, ngayDang) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, thongBao.getMaThongBao());
            stmt.setString(2, thongBao.getTieuDe());
            stmt.setString(3, thongBao.getNoiDung());
            stmt.setDate(4, java.sql.Date.valueOf(thongBao.getNgayDang()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteThongBao(String maThongBao) {
        String sql = "DELETE FROM ThongBao WHERE maThongBao = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maThongBao);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
