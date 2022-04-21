package com.tang.tpwsmartparkinglot.service;

import com.tang.tpwsmartparkinglot.entity.ParkingRecord;
import com.tang.tpwsmartparkinglot.vo.ParkingRecordVo;

import java.time.LocalDateTime;

public interface ParkingRecordService {
    ParkingRecord checkCarStatus(String numberPlate);

    void addparkingRecord(String parkingName, String numberPlate, LocalDateTime inDateTime, LocalDateTime outDateTime, int money, String status);

    void updataParkingRecord(String parkingName, String numberPlate, LocalDateTime inDateTime, LocalDateTime outDateTime, int money, String status);

    ParkingRecordVo<ParkingRecord> checkParkingRecordVo(Integer page, Integer limit);

    ParkingRecordVo<ParkingRecord> inquireParkingRecord(String numberPlate, String loginTime, String loginOutTime, Integer page, Integer limit);
}
