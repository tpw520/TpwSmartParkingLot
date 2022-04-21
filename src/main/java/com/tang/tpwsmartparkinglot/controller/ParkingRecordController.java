package com.tang.tpwsmartparkinglot.controller;


import com.tang.tpwsmartparkinglot.entity.ParkingRecord;
import com.tang.tpwsmartparkinglot.service.ParkingRecordService;
import com.tang.tpwsmartparkinglot.vo.ParkingRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class ParkingRecordController {
    @Autowired
    private ParkingRecordService parkingRecordService;
    @GetMapping("/getParkingRecordVo")
    @ResponseBody
    public ParkingRecordVo<ParkingRecord> getParkingRecordVo(Integer page, Integer limit){
        return parkingRecordService.checkParkingRecordVo(page,limit);
    }
    @GetMapping("/inquireParkingRecord")
    @ResponseBody
    public ParkingRecordVo<ParkingRecord> inquireParkingRecord(@RequestParam(value = "numberPlate",required = false) String numberPlate,
                                       @RequestParam(value = "loginTime",required = false)String loginTime,
                                       @RequestParam(value = "loginOutTime",required = false)String loginOutTime,
                                       Integer page,Integer limit
                                       ){
        log.info("**********{}", numberPlate);
        log.info("**********{}", loginTime);
        log.info("**********{}", loginOutTime);
        return parkingRecordService.inquireParkingRecord(numberPlate, loginTime, loginOutTime, page, limit);
    }
}
