package com.tang.tpwsmartparkinglot.service;


import com.tang.tpwsmartparkinglot.entity.Admin;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AdminService {
    Admin getAdmin(String username);
    void updatePassword(String newPassword,String username);
    Integer updatePaxssword(Map<String, String> map, HttpSession session);
}
