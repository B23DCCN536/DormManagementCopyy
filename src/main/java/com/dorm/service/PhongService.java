package com.dorm.service;

import com.dorm.model.Phong;
import java.util.List;

public interface PhongService {
    List<Phong> getAllPhong();
    List<Phong> getPhongTrong();
    Phong getPhongById(String maPhong);
    boolean updateSoNguoi(String maPhong, int delta);
}
