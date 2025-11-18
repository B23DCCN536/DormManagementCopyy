package com.dorm.service;

import com.dorm.model.YeuCauGiaHan;
import java.util.List;

public interface YeuCauGiaHanService {
    boolean createRequest(YeuCauGiaHan request);
    List<YeuCauGiaHan> getAllRequests();
    List<YeuCauGiaHan> getRequestsByStudent(String maSinhVien);
    boolean updateStatus(int maYeuCau, String newStatus);
    YeuCauGiaHan getPendingRequest(String maSinhVien);
}
