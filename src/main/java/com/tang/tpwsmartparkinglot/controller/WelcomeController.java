package com.tang.tpwsmartparkinglot.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

    //返回给前端的实时数据
    @GetMapping("/nowData")
    @ResponseBody
    public String nowData(){
        return "123";
    }
}
