package com.dorm.service;

import com.dorm.model.SuCo;
import com.dorm.model.enums.TrangThaiSuCo;
import java.util.List;

public interface SuCoService {
    List<SuCo> getAllSuCo();
    List<SuCo> getSuCoBySinhVien(String maSinhVien);
    boolean addSuCo(SuCo suCo);
    boolean updateStatus(String maSuCo, TrangThaiSuCo trangThai);
}
