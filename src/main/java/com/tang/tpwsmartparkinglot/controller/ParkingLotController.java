package com.tang.tpwsmartparkinglot.controller;

import com.tang.tpwsmartparkinglot.entity.ParkingLot;
import com.tang.tpwsmartparkinglot.service.ParkingLotPageService;
import com.tang.tpwsmartparkinglot.service.ParkingLotService;
import com.tang.tpwsmartparkinglot.service.ParkingSpaceService;
import com.tang.tpwsmartparkinglot.vo.ParkingLotVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private ParkingLotPageService parkingLotPageService;

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    //查询所有的停车场
    @GetMapping(value = "/ParkingList")
    @ResponseBody
    public ParkingLotVo<ParkingLot> parkingLotList(Integer page, Integer limit) {
        return parkingLotPageService.getParkingLotVo(page, limit);
    }

    //添加停车场
    @GetMapping(value = "/addParkingLot")
    @ResponseBody
    public void addParkingLot(@RequestParam Map<String, String> map) {
        parkingSpaceService.addParkingSpace(map);
        parkingLotService.addParkingLot(map);
    }
    //删除停车场(批量)
    @PostMapping(value = "/deleteParkingLotList")
    @ResponseBody
    public Integer deleteParkingLotList(@RequestParam("parkingId") String parkingId) {
        parkingSpaceService.deleteParkingSpace(parkingId);
        return parkingLotService.deleteParkingLotList(parkingId);
    }

    //编辑停车场
    @GetMapping(value = "/editParkingLot")
    @ResponseBody
    public void editParkingLot(@RequestParam Map<String, String> map) {
        parkingSpaceService.editParkingSpace(map);
        parkingLotService.editParkingLot(map);
    }

    //删除停车场
    @GetMapping(value = "/deleteParkingLot")
    @ResponseBody
    public void deleteParkingLot(@RequestParam("parkingId") String parkingId) {
        parkingSpaceService.deleteParkingSpace(parkingId);
        parkingLotService.deleteParkingLot(parkingId);
    }

    //搜索停车场
    @GetMapping("/inquireParkingLot")
    @ResponseBody
    public ParkingLotVo<ParkingLot> inquireParkingLot(@RequestParam(value = "parkingName" ,required = false) String parkingName, Integer page, Integer limit){
        return parkingLotPageService.inquireParkingLot(parkingName,page,limit);
    }
}
