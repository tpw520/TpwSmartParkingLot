package com.tang.tpwsmartparkinglot.service;

import com.tang.tpwsmartparkinglot.entity.ParkingSpace;
import com.tang.tpwsmartparkinglot.vo.ParkingMoneyVo;

import java.util.List;

public interface EchartsDataService {
    List parkingLotMoneyEcharts();

    List parkingLotSpaceEcharts();
}
