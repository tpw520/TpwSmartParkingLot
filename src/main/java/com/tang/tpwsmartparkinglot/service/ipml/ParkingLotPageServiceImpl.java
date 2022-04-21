package com.tang.tpwsmartparkinglot.service.ipml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.tpwsmartparkinglot.entity.ParkingLot;
import com.tang.tpwsmartparkinglot.entity.ParkingRecord;
import com.tang.tpwsmartparkinglot.mapper.ParkingLotPageMapper;
import com.tang.tpwsmartparkinglot.service.ParkingLotPageService;

import com.tang.tpwsmartparkinglot.vo.ParkingLotVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotPageServiceImpl implements ParkingLotPageService {
    @Autowired
    private ParkingLotPageMapper parkingLotPageMapper;
    //查询所有的停车场
    @Override
    public ParkingLotVo<ParkingLot> getParkingLotVo(Integer page, Integer limit) {
        IPage<ParkingLot> parkingLotPage = new Page<>(page, limit);
        IPage<ParkingLot> result = parkingLotPageMapper.selectPage(parkingLotPage, null);
        ParkingLotVo<ParkingLot> vo = new ParkingLotVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }

    @Override
    public ParkingLotVo<ParkingLot> inquireParkingLot(String parkingName,Integer page, Integer limit) {
        IPage<ParkingLot> parkingLotPage = new Page<>(page, limit);
        QueryWrapper<ParkingLot> wrapper = new QueryWrapper<ParkingLot>();
        wrapper.like("parking_name", parkingName);
        IPage<ParkingLot> result = parkingLotPageMapper.selectPage(parkingLotPage, wrapper);
        ParkingLotVo<ParkingLot> vo = new ParkingLotVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }
}
