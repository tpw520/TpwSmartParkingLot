package com.tang.tpwsmartparkinglot.controller;

import com.tang.tpwsmartparkinglot.entity.Admin;
import com.tang.tpwsmartparkinglot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

//用户登录
@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;
//    登陆判断
    @PostMapping("/login")
    public String Login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        Admin admin = adminService.getAdmin(username);
        if (admin != null && admin.getPassword().equals(password)) {
            //注入session 全局
            session.setAttribute("loginAdmin", admin);
            //重定向到主页面
            return "redirect:/index.html";
        }
        model.addAttribute("msg", "账号密码错误，请重新输入");
        return "loginPage";
    }
    @GetMapping("/index.html")
    public String indexPage(HttpSession session,
                            Model model) {
        Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
        model.addAttribute("name", loginAdmin.getUsername());
        return "index";
    }
}
