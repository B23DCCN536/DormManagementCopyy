package com.dorm.service;

import com.dorm.model.TaiKhoan;

public interface TaiKhoanService {
    TaiKhoan login(String username, String password);
    boolean changePassword(String username, String oldPassword, String newPassword);
    boolean createAccount(String username, String password, String role);
    boolean deleteAccount(String username);
}
