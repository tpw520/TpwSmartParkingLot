package com.tang.tpwsmartparkinglot.controller;

import com.tang.tpwsmartparkinglot.service.LicensePlateService;
import com.tang.tpwsmartparkinglot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class LicensePlateController {
    @Autowired
    private ParkingLotService parkingLotService;
    @Autowired
    private LicensePlateService licensePlateService;

    //查询所有的停车场名
    @GetMapping("/carNameSelect")
    @ResponseBody
    public List<String> carNameSelect() {
        return parkingLotService.selectNameList();
    }

    //车牌识别
    @PostMapping("/imageRecognition")
    @ResponseBody
    public String imageRecognition(@RequestPart("fileName") MultipartFile fileName, @RequestParam("parkingName") String parkingName, Map map, @RequestParam(name = "Photograph" ,required = false)String photograph){
        return licensePlateService.imageRecognition(fileName, parkingName, photograph);
    }
    //车牌识别
    @PostMapping("/imageRecognition1")
    public String imageRecognition1(@RequestPart("fileName") MultipartFile fileName, @RequestParam("parkingName") String parkingName, Map map, @RequestParam(name = "Photograph" ,required = false)String photograph){
        String message = licensePlateService.imageRecognition(fileName, parkingName, photograph);
        map.put("PlateMsg",message);
        return "licensePlate";
    }

}
