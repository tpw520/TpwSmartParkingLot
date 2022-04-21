package com.tang.tpwsmartparkinglot.service;

import com.tang.tpwsmartparkinglot.entity.ParkingLot;

import java.util.List;
import java.util.Map;

public interface ParkingLotService {
    //添加停车场
    void addParkingLot(Map<String, String> map);

    //删除停车场
    Integer deleteParkingLotList(String parkingId);

    //编辑停车场
    void editParkingLot(Map<String, String> map);

    //删除停车场(单个)
    void deleteParkingLot(String parkingId);

    //查询所有的停车场名
    List<String> selectNameList();

    //通过停车场名字找到停车场对象
    ParkingLot getParkingLotByName(String parkingName);
}
