package com.dorm.service;

import com.dorm.model.HopDong;
import java.util.List;

public interface HopDongService {
    List<HopDong> getAllHopDong();
    HopDong getHopDongMoiNhat(String maSinhVien);
    List<HopDong> getLichSuHopDong(String maSinhVien);
    boolean addHopDong(HopDong hopDong);
    boolean extendHopDong(String maHopDong);
    boolean extendHopDongByMonths(String maHopDong, int months);
    boolean deleteHopDongByMaSV(String maSV);
    String generateNewContractId();
}
