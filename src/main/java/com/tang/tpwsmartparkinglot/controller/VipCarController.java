package com.tang.tpwsmartparkinglot.controller;

import com.tang.tpwsmartparkinglot.entity.VipCar;
import com.tang.tpwsmartparkinglot.service.VipCarService;
import com.tang.tpwsmartparkinglot.vo.VipCarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class VipCarController {
    @Autowired
    private VipCarService vipCarService;
    @GetMapping("/addVipCar")
    @ResponseBody
    public String addVipCar(@RequestParam Map<String,String> map){
        vipCarService.addVipCar(map);
        return "0";
    }
    @GetMapping("/vipCarList")
    @ResponseBody
    public VipCarVo<VipCar> vipCarList(Integer page, Integer limit){
        return vipCarService.getVipCarVo(page,limit);
    }
    @GetMapping("/inquireVipMessage")
    @ResponseBody
    public VipCarVo<VipCar> inquireVipMessage(@RequestParam(value = "numberPlate" ,required = false) String numberPlate,Integer page, Integer limit){
        return vipCarService.inquireVipMessage(numberPlate,page,limit);
    }
}
