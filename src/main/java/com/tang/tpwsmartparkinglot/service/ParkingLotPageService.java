package com.tang.tpwsmartparkinglot.service;

import com.tang.tpwsmartparkinglot.entity.ParkingLot;
import com.tang.tpwsmartparkinglot.vo.ParkingLotVo;

public interface ParkingLotPageService {
    //查询所有的停车场
    ParkingLotVo<ParkingLot> getParkingLotVo(Integer page, Integer limit);

    ParkingLotVo<ParkingLot> inquireParkingLot(String parkingName,Integer page, Integer limit);
}
