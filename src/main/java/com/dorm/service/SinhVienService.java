package com.dorm.service;

import com.dorm.model.SinhVien;
import java.util.List;

public interface SinhVienService {
    List<SinhVien> getAllSinhVien();
    SinhVien getSinhVienByMaSV(String maSV);
    void addSinhVien(SinhVien sv);
    void updateSinhVien(SinhVien sv);
    void deleteSinhVien(String maSV);
    List<SinhVien> searchSinhVien(String keyword);
    SinhVien getSinhVienByUsername(String username);
}
