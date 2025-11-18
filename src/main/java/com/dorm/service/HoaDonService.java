package com.dorm.service;

import com.dorm.model.HoaDon;
import java.util.List;

public interface HoaDonService {
    List<HoaDon> getAllHoaDon();
    List<HoaDon> getHoaDonBySinhVien(String maSinhVien);
}
