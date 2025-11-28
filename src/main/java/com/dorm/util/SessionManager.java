package com.dorm.util;

import com.dorm.model.TaiKhoan;

public class SessionManager {
    private static SessionManager instance;
    private TaiKhoan currentUser;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setCurrentUser(TaiKhoan user) {
        this.currentUser = user;
    }

    public TaiKhoan getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }
}
