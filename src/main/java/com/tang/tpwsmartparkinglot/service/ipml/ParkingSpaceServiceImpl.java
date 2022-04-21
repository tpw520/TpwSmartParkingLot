package com.tang.tpwsmartparkinglot.service.ipml;

import com.tang.tpwsmartparkinglot.entity.ParkingLot;
import com.tang.tpwsmartparkinglot.entity.ParkingSpace;
import com.tang.tpwsmartparkinglot.mapper.ParkingLotMapper;
import com.tang.tpwsmartparkinglot.mapper.ParkingSpaceMapper;
import com.tang.tpwsmartparkinglot.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Override
    public void addParkingSpace(Map<String, String> map) {
        String parkingName = (String) map.get("parkingName");
        int parkingSpaceCount = Integer.parseInt(map.get("parkingSpaceCount"));
        parkingSpaceMapper.addParkingSpace(parkingName, parkingSpaceCount);
    }

    @Override
    public void deleteParkingSpace(String parkingId) {
        String sub = parkingId.substring(1, parkingId.length() - 1);
        if (sub.contains(",")) {
            for (String s : sub.split(",")) {
              ParkingLot parkingLot = parkingLotMapper.getParkingLotById(Integer.parseInt(s));
              String parkingName = parkingLot.getParkingName();
              parkingSpaceMapper.deleteParkingSpace(parkingName);
            }
        } else {
            ParkingLot parkingLot = parkingLotMapper.getParkingLotById(Integer.parseInt(sub));
            String parkingName = parkingLot.getParkingName();
            parkingSpaceMapper.deleteParkingSpace(parkingName);
        }
    }

    @Override
    public void editParkingSpace(Map<String, String> map) {
        String parkingName = map.get("parkingName");
        String parkingSpaceCount = map.get("parkingSpaceCount");
        parkingSpaceMapper.editParkingSpace(parkingName,parkingSpaceCount);
    }
}
