package com.tang.tpwsmartparkinglot.controller;

import com.tang.tpwsmartparkinglot.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
//修改密码
@Slf4j
@Controller
public class ChangePasswordController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/updatePassword")
    @ResponseBody
    public Integer updatePassword(@RequestParam Map<String,String> map, HttpSession session){
        return adminService.updatePaxssword(map,session);
    }
}
