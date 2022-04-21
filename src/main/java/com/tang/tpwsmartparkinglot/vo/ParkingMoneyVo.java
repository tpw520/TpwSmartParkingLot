package com.tang.tpwsmartparkinglot.vo;

import lombok.Data;

import java.util.List;

@Data
public class ParkingMoneyVo<T> {
//    private List<String> ParkingNames;
//    private List<Integer> Moneys;
    private List<T> parkingEntityMoneyVo;
}
