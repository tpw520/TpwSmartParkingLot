package com.tang.tpwsmartparkinglot.service.ipml;

import com.tang.tpwsmartparkinglot.entity.ParkingSpace;
import com.tang.tpwsmartparkinglot.mapper.EchartsDataMapper;
import com.tang.tpwsmartparkinglot.mapper.ParkingRecordMapper;
import com.tang.tpwsmartparkinglot.mapper.ParkingSpaceMapper;
import com.tang.tpwsmartparkinglot.service.EchartsDataService;
import com.tang.tpwsmartparkinglot.service.ParkingSpaceService;
import com.tang.tpwsmartparkinglot.vo.ParkingEntityMoneyVo;
import com.tang.tpwsmartparkinglot.vo.ParkingMoneyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class EchartsDataServiceImpl implements EchartsDataService {
    @Autowired
    private EchartsDataMapper echartsDataMapper;
    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;

    @Autowired
    private ParkingRecordMapper parkingRecordMapper;
    @Override
    public List parkingLotMoneyEcharts() {
        List<ParkingEntityMoneyVo> parkingEntityMoneyVos = echartsDataMapper.parkingLotMoneyEcharts();
        ArrayList<Object> list = new ArrayList<>();
        for (ParkingEntityMoneyVo parkingEntityMoneyVo : parkingEntityMoneyVos) {
            String parkingName = parkingEntityMoneyVo.getParkingName();
            Integer money = parkingEntityMoneyVo.getMoney();
            HashMap<String, Object> map = new HashMap<>();
            map.put("value", money);
            map.put("name", parkingName);
            list.add(map);
            log.info("&&&&&&&&&&&&&&&&&&&&&&{}", list);
        }
        return list;
    }

    @Override
    public List parkingLotSpaceEcharts() {
        List<ParkingSpace> parkingSpaces = parkingSpaceMapper.parkingLotSpaceEcharts();
        ArrayList<Object> list = new ArrayList<>();
        for (ParkingSpace parkingSpace : parkingSpaces) {
            String parkingName = parkingSpace.getParkingName();
            Integer status1 = parkingRecordMapper.selectParkingStatusCount(parkingName);
            Integer parkingSpaceCount = parkingSpace.getParkingSpace();
            Integer parkingCarCount = parkingSpaceCount - status1;
            HashMap<String, Object> map = new HashMap<>();
            map.put("value", parkingCarCount);
            map.put("name", parkingName);
            list.add(map);
        }
        return list;
    }
}
