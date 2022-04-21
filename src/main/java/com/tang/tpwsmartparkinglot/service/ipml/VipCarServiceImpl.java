package com.tang.tpwsmartparkinglot.service.ipml;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.tpwsmartparkinglot.entity.ParkingLot;
import com.tang.tpwsmartparkinglot.entity.ParkingRecord;
import com.tang.tpwsmartparkinglot.entity.VipCar;
import com.tang.tpwsmartparkinglot.mapper.VipCarMapper;
import com.tang.tpwsmartparkinglot.service.VipCarService;
import com.tang.tpwsmartparkinglot.vo.ParkingLotVo;
import com.tang.tpwsmartparkinglot.vo.VipCarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VipCarServiceImpl implements VipCarService {
    @Autowired
    private VipCarMapper vipCarMapper;
    @Override
    public void addVipCar(Map<String, String> map) {
        String numberPlate = map.get("numberPlate");
        String carRoot = map.get("carRoot");
        String moneystr = map.get("money");
        int money = Integer.parseInt(moneystr);
        String carPhone = map.get("carPhone");
        String payMode = map.get("payMode");
        String carInfo = map.get("carInfo");
        VipCar vipCar = vipCarMapper.checkVipCar(numberPlate);
        if (vipCar == null){
            vipCarMapper.addVipCar(numberPlate, carRoot, money, carPhone, payMode, carInfo);
        }else {
            vipCarMapper.editVipCar(numberPlate, carRoot, money, carPhone, payMode, carInfo);
        }
    }

    @Override
    public VipCarVo<VipCar> getVipCarVo(Integer page, Integer limit) {
        IPage<VipCar> vipCarPage = new Page<>(page, limit);
        IPage<VipCar> result = vipCarMapper.selectPage(vipCarPage, null);
        VipCarVo<VipCar> vo = new VipCarVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }

    @Override
    public VipCarVo<VipCar> inquireVipMessage(String numberPlate, Integer page, Integer limit) {
        IPage<VipCar> vipCarPage = new Page<>(page, limit);
        QueryWrapper<VipCar> wrapper = new QueryWrapper<VipCar>();
        wrapper.like("number_plate", numberPlate);
        IPage<VipCar> result = vipCarMapper.selectPage(vipCarPage, wrapper);
        VipCarVo<VipCar> vo = new VipCarVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }
}
