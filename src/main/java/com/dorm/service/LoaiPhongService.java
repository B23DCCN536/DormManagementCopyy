package com.dorm.service;

import com.dorm.model.LoaiPhong;
import java.util.List;

public interface LoaiPhongService {
    List<LoaiPhong> getAllLoaiPhong();
    LoaiPhong getLoaiPhongById(String maLoaiPhong);
}
