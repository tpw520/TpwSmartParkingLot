package com.tang.tpwsmartparkinglot.entity;

import lombok.Data;

@Data
public class ParkingSpace {
    //车场名
    private String parkingName;
    //剩余车辆数
    private Integer parkingSpace;
}
