package com.tang.tpwsmartparkinglot.service;

import com.tang.tpwsmartparkinglot.entity.VipCar;
import com.tang.tpwsmartparkinglot.vo.VipCarVo;

import java.util.Map;

public interface VipCarService {
    void addVipCar(Map<String, String> map);

    VipCarVo<VipCar> getVipCarVo(Integer page, Integer limit);

    VipCarVo<VipCar> inquireVipMessage(String numberPlate, Integer page, Integer limit);
}
