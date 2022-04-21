package com.tang.tpwsmartparkinglot.service.ipml;

import com.tang.tpwsmartparkinglot.entity.ParkingLot;
import com.tang.tpwsmartparkinglot.entity.VipCar;
import com.tang.tpwsmartparkinglot.mapper.ParkingLotMapper;
import com.tang.tpwsmartparkinglot.mapper.ParkingRecordMapper;
import com.tang.tpwsmartparkinglot.mapper.VipCarMapper;
import com.tang.tpwsmartparkinglot.service.ParkingRecordService;
import com.tang.tpwsmartparkinglot.service.SystemService;
import com.tang.tpwsmartparkinglot.service.VipCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Autowired
    private ParkingRecordMapper parkingRecordMapper;

    @Autowired
    private VipCarMapper vipCarMapper;

    public Integer parkingLotNumber() {
        return parkingLotMapper.selectList(null).size();
    }

    public Integer parkingLotSpaceNumber() {
        Integer parkingLotSpaceNumber = 0;
        List<ParkingLot> parkingLots = parkingLotMapper.selectParkingLotList();
        if (parkingLots != null){
            for (ParkingLot parkingLot : parkingLots) {
                if (parkingLot != null){
                    parkingLotSpaceNumber = parkingLotSpaceNumber + parkingLot.getParkingSpaceCount();
                }
            }
            return parkingLotSpaceNumber;
        }
        return 0;
    }

    public Integer parkingNumber() {
        Integer parkingNumber = parkingRecordMapper.parkingNumber();
        return parkingNumber;
    }

    public Integer vipCarNumber() {
        return vipCarMapper.selectList(null).size();
    }

    @Override
    public Map<String, Integer> systemPageDate() {
        HashMap<String, Integer> map = new HashMap<>();
        Integer parkingLotNumber = parkingLotNumber();
        Integer parkingLotSpaceNumber = parkingLotSpaceNumber();
        Integer parkingNumber = parkingNumber();
        Integer vipCarNumber = vipCarNumber();
        map.put("parkingLotNumber", parkingLotNumber);
        map.put("parkingLotSpaceNumber", parkingLotSpaceNumber);
        map.put("parkingNumber", parkingNumber);
        map.put("vipCarNumber", vipCarNumber);
        return map;
    }
}

