package com.tang.tpwsmartparkinglot.service.ipml;


import com.tang.tpwsmartparkinglot.entity.Admin;
import com.tang.tpwsmartparkinglot.mapper.LoginAdminMapper;
import com.tang.tpwsmartparkinglot.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private LoginAdminMapper loginAdmin;
    @Override
    public Admin getAdmin(String username) {
        return loginAdmin.getAdmin(username);
    }

    @Override
    public void updatePassword(String newPassword,String username) {
        loginAdmin.updatePassword(newPassword,username);
    }

    @Override
    public Integer updatePaxssword(Map<String, String> map, HttpSession session) {
        String newPassword = null;
        String oldPassword = null;
        if(map != null){
            newPassword =  map.get("new_password");
            oldPassword =  map.get("old_password");
            Admin admin = (Admin) session.getAttribute("loginAdmin");
            if (admin.getPassword().equals(oldPassword)){
                return this.loginAdmin.updatePassword(newPassword,admin.getUsername());
            }
        }
        return 0;
    }
}
