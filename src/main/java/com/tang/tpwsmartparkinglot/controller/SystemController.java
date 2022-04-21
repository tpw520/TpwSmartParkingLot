package com.tang.tpwsmartparkinglot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tang.tpwsmartparkinglot.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class SystemController {
    @Autowired
    private SystemService systemService;
    @GetMapping("/systemPageDate")
    @ResponseBody
    public String systemPageDate(){
        Map<String, Integer> stringIntegerMap = systemService.systemPageDate();
        String json = JSON.toJSONString(stringIntegerMap);
        return json;
    }
}
