package com.tang.tpwsmartparkinglot.service.ipml;

import com.tang.tpwsmartparkinglot.entity.ParkingLot;
import com.tang.tpwsmartparkinglot.mapper.ParkingLotMapper;
import com.tang.tpwsmartparkinglot.mapper.ParkingLotPageMapper;
import com.tang.tpwsmartparkinglot.service.ParkingLotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    private ParkingLotMapper parkingLotMapper;
    //添加停车场
    @Override
    public void addParkingLot(Map<String, String> map) {
        int parkingId = Integer.parseInt(map.get("parkingId"));
        String parkingName = (String) map.get("parkingName");
        int parkingSpaceCount = Integer.parseInt(map.get("parkingSpaceCount"));
        int timingUnit = Integer.parseInt(map.get("timingUnit"));
        int unitCost = Integer.parseInt(map.get("unitCost"));
        parkingLotMapper.addParkingLot(parkingId,parkingName,parkingSpaceCount,timingUnit,unitCost);
    }

    //删除停车场
    @Override
    public Integer deleteParkingLotList(String parkingId) {
        String sub = parkingId.substring(1, parkingId.length() - 1);
        if (sub.contains(",")) {
            for (String s : sub.split(",")) {
                 parkingLotMapper.delParkingLot(Integer.parseInt(s));
            }
            return 1;
        } else {
             return parkingLotMapper.delParkingLot(Integer.parseInt(sub));
        }
    }

    @Override
    //编辑停车场
    public void editParkingLot(Map<String, String> map) {
        int parkingId = Integer.parseInt(map.get("parkingId"));
        String parkingName = (String) map.get("parkingName");
        int parkingSpaceCount = Integer.parseInt(map.get("parkingSpaceCount"));
        int timingUnit = Integer.parseInt(map.get("timingUnit"));
        int unitCost = Integer.parseInt(map.get("unitCost"));
        parkingLotMapper.editParkingLot(parkingId,parkingName,parkingSpaceCount,timingUnit,unitCost);
    }

    @Override
    public void deleteParkingLot(String parkingId) {
        parkingLotMapper.delParkingLot(Integer.parseInt(parkingId));
    }

    @Override
    //查询所有的停车场名
    public List<String> selectNameList() {
        List<ParkingLot> parkingLots = parkingLotMapper.selectList(null);
        ArrayList<String> list = new ArrayList<>();
        if (parkingLots!=null) {
            for (ParkingLot parkingLot : parkingLots) {
                list.add(parkingLot.getParkingName());
            }
        }
        return list;
    }

    @Override
    //通过停车场名字找到停车场对象
    public ParkingLot getParkingLotByName(String parkingName) {
        return parkingLotMapper.getParkingLotByName(parkingName);
    }
}
