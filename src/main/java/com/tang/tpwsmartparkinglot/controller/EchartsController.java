package com.tang.tpwsmartparkinglot.controller;

import com.tang.tpwsmartparkinglot.service.EchartsDataService;
import com.tang.tpwsmartparkinglot.vo.ParkingMoneyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class EchartsController {
    @Autowired
    private EchartsDataService echartsDataService;

    @PostMapping("/parkingLotMoneyEcharts")
    @ResponseBody
    public List parkingLotMoneyEcharts() {
        return echartsDataService.parkingLotMoneyEcharts();
    }

    @PostMapping("/parkingLotSpaceEcharts")
    @ResponseBody
    public List parkingLotSpaceEcharts() {
        return echartsDataService.parkingLotSpaceEcharts();
    }
}
