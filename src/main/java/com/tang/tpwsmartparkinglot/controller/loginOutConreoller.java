package com.tang.tpwsmartparkinglot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class loginOutConreoller {
    @GetMapping("/loginOut")
    @ResponseBody
    public String loginOut(HttpSession session){
        session.removeAttribute("loginAdmin");
        return null;
    }
}
