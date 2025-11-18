package com.dorm.service;

import com.dorm.model.Admin;

public interface AdminService {
    Admin getAdminByUsername(String username);
}