package com.dorm.service;

import com.dorm.model.ThongBao;
import java.util.List;

public interface ThongBaoService {
    List<ThongBao> getAllThongBao();
    boolean addThongBao(ThongBao thongBao);
    boolean deleteThongBao(String maThongBao);
}