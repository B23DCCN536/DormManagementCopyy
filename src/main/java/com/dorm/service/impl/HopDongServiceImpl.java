package com.dorm.service.impl;

import com.dorm.database.DatabaseManager;
import com.dorm.model.HopDong;
import com.dorm.model.enums.TrangThaiHopDong;
import com.dorm.service.HopDongService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HopDongServiceImpl implements HopDongService {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public List<HopDong> getAllHopDong() {
        List<HopDong> list = new ArrayList<>();
        List<HopDong> toExpire = new ArrayList<>();
        String sql = "SELECT * FROM HopDong";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                HopDong hd = mapResultSetToHopDong(rs);
                if (shouldExpire(hd)) {
                    hd.setTrangThai(TrangThaiHopDong.HET_HAN);
                    toExpire.add(hd);
                }
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Update expired contracts after closing the select connection
        for (HopDong hd : toExpire) {
            updateStatus(hd.getMaHopDong(), TrangThaiHopDong.HET_HAN);
        }
        
        return list;
    }

    @Override
    public HopDong getHopDongMoiNhat(String maSinhVien) {
        String sql = "SELECT TOP 1 * FROM HopDong WHERE maSinhVien = ? ORDER BY ngayKetThuc DESC";
        HopDong hd = null;
        boolean needsUpdate = false;
        
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSinhVien);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    hd = mapResultSetToHopDong(rs);
                    if (shouldExpire(hd)) {
                        hd.setTrangThai(TrangThaiHopDong.HET_HAN);
                        needsUpdate = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (needsUpdate && hd != null) {
            updateStatus(hd.getMaHopDong(), TrangThaiHopDong.HET_HAN);
        }
        
        return hd;
    }

    @Override
    public boolean addHopDong(HopDong hopDong) {
        String sql = "INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hopDong.getMaHopDong());
            stmt.setString(2, hopDong.getMaSinhVien());
            stmt.setString(3, hopDong.getMaPhong());
            stmt.setDouble(4, hopDong.getGiaPhong());
            stmt.setDate(5, java.sql.Date.valueOf(hopDong.getNgayLap()));
            stmt.setDate(6, java.sql.Date.valueOf(hopDong.getNgayBatDau()));
            stmt.setDate(7, java.sql.Date.valueOf(hopDong.getNgayKetThuc()));
            stmt.setString(8, hopDong.getTrangThai().name());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean extendHopDong(String maHopDong) {
        return extendHopDongByMonths(maHopDong, 1);
    }

    @Override
    public boolean extendHopDongByMonths(String maHopDong, int months) {
        String sql = "UPDATE HopDong SET ngayKetThuc = DATEADD(month, ?, ngayKetThuc), trangThai = 'HIEU_LUC' WHERE maHopDong = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, months);
            stmt.setString(2, maHopDong);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteHopDongByMaSV(String maSV) {
        String sql = "DELETE FROM HopDong WHERE maSinhVien = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSV);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<HopDong> getLichSuHopDong(String maSinhVien) {
        List<HopDong> list = new ArrayList<>();
        List<HopDong> toExpire = new ArrayList<>();
        String sql = "SELECT * FROM HopDong WHERE maSinhVien = ? ORDER BY ngayBatDau DESC";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSinhVien);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    HopDong hd = mapResultSetToHopDong(rs);
                    if (shouldExpire(hd)) {
                        hd.setTrangThai(TrangThaiHopDong.HET_HAN);
                        toExpire.add(hd);
                    }
                    list.add(hd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        for (HopDong hd : toExpire) {
            updateStatus(hd.getMaHopDong(), TrangThaiHopDong.HET_HAN);
        }
        
        return list;
    }

    private boolean shouldExpire(HopDong hd) {
        return hd != null && 
               hd.getTrangThai() == TrangThaiHopDong.HIEU_LUC && 
               hd.getNgayKetThuc().isBefore(java.time.LocalDate.now());
    }

    private void updateStatus(String maHopDong, TrangThaiHopDong status) {
        String sql = "UPDATE HopDong SET trangThai = ? WHERE maHopDong = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status.name());
            stmt.setString(2, maHopDong);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generateNewContractId() {
        String sql = "SELECT TOP 1 maHopDong FROM HopDong WHERE maHopDong LIKE 'HD%' ORDER BY LEN(maHopDong) DESC, maHopDong DESC";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("maHopDong");
                try {
                    int number = Integer.parseInt(lastId.substring(2));
                    return String.format("HD%03d", number + 1);
                } catch (NumberFormatException e) {
                    // Fallback if parsing fails
                    return "HD" + System.currentTimeMillis();
                }
            } else {
                return "HD001";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "HD" + System.currentTimeMillis();
        }
    }

    private HopDong mapResultSetToHopDong(ResultSet rs) throws SQLException {
        return new HopDong(
            rs.getString("maHopDong"),
            rs.getString("maSinhVien"),
            rs.getString("maPhong"),
            rs.getDouble("giaPhong"),
            rs.getDate("ngayLap").toLocalDate(),
            rs.getDate("ngayBatDau").toLocalDate(),
            rs.getDate("ngayKetThuc").toLocalDate(),
            TrangThaiHopDong.valueOf(rs.getString("trangThai"))
        );
    }
}
