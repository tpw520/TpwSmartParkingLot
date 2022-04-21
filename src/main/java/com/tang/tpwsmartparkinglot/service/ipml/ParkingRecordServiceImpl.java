package com.tang.tpwsmartparkinglot.service.ipml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.tpwsmartparkinglot.entity.ParkingRecord;
import com.tang.tpwsmartparkinglot.mapper.ParkingRecordMapper;
import com.tang.tpwsmartparkinglot.service.ParkingRecordService;
import com.tang.tpwsmartparkinglot.vo.ParkingRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ParkingRecordServiceImpl implements ParkingRecordService {
    @Autowired
    private ParkingRecordMapper parkingRecordMapper;

    @Override
    //查找当前识别出的车辆的停车记录
    public ParkingRecord checkCarStatus(String numberPlate) {
        return parkingRecordMapper.checkCarStatus(numberPlate);
    }

    @Override
    //向停车场中插入一条停车记录
    public void addparkingRecord(String parkingName, String numberPlate, LocalDateTime inDateTime, LocalDateTime outDateTime, int money, String status) {
        parkingRecordMapper.addparkingRecord(parkingName, numberPlate, inDateTime, outDateTime, money, status);
    }

    @Override
    //向停车场中更新一条停车记录
    public void updataParkingRecord(String parkingName, String numberPlate, LocalDateTime inDateTime, LocalDateTime outDateTime, int money, String status) {
        parkingRecordMapper.updataParkingRecord(parkingName, numberPlate, inDateTime, outDateTime, money, status);
    }

    @Override
    //遍历停车场中的停车记录,并封装成Vo对象
    public ParkingRecordVo<ParkingRecord> checkParkingRecordVo(Integer page, Integer limit) {
        IPage<ParkingRecord> vipCarPage = new Page<>(page, limit);
        IPage<ParkingRecord> result = parkingRecordMapper.selectPage(vipCarPage, null);
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        List<ParkingRecord> parkingRecordAll = result.getRecords();
        //对数据再进行一次渲染，返回符合要求的数据
        for (ParkingRecord parkingRecord : parkingRecordAll) {
            if ("1".equals(parkingRecord.getStatus())) {
                parkingRecord.setStatus("正在泊车");
            } else {
                parkingRecord.setStatus("车辆已离开");
            }
        }
        vo.setData(parkingRecordAll);
        return vo;
    }

    @Override
    //查询停车场中的停车记录,并封装成Vo对象
    public ParkingRecordVo<ParkingRecord> inquireParkingRecord(String numberPlate, String loginTime, String loginOutTime, Integer page, Integer limit) {
        if (numberPlate.equals("") && loginTime.equals("") && loginOutTime.equals("")) {
            return inquireAll(page, limit);
        } else if (numberPlate != "" && loginTime != "" && loginOutTime != "") {
            return inquireAllParams(numberPlate, loginTime, loginOutTime, page, limit);
        } else if (numberPlate != "" && loginTime != null) {
            return inquireParkingNumberLogin(numberPlate, loginTime, page, limit);
        } else if (numberPlate != "" && loginOutTime != null) {
            return inquireParkingNumberLoginOut(numberPlate, loginOutTime, page, limit);
        } else if (!(numberPlate.equals(""))) {
            return inquireParkingName(numberPlate, page, limit);
        } else if (loginTime != "" && loginOutTime != "") {
            return inquireDate(loginTime, loginOutTime, page, limit);
        } else if (!loginTime.equals("")) {
            return inquireloginTime(loginTime, page, limit);
        } else if (!loginOutTime.equals("")) {
            return inquireloginOutTime(loginOutTime, page, limit);
        } else {
            return null;
        }
    }

    public ParkingRecordVo<ParkingRecord> inquireParkingName(String numberPlate, Integer page, Integer limit) {
        IPage<ParkingRecord> parkingRecordPage = new Page<>(page, limit);
        QueryWrapper<ParkingRecord> wrapper = new QueryWrapper<ParkingRecord>();
        wrapper.like("number_plate", numberPlate);
        IPage<ParkingRecord> parkingRecordIPage = parkingRecordMapper.selectPage(parkingRecordPage, wrapper);
        List<ParkingRecord> records = parkingRecordIPage.getRecords();
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) parkingRecordIPage.getTotal());
        for (ParkingRecord parkingRecord : records) {
            if ("1".equals(parkingRecord.getStatus())) {
                parkingRecord.setStatus("正在泊车");
            } else {
                parkingRecord.setStatus("车辆已离开");
            }
        }
        vo.setData(records);
        return vo;
    }

    public ParkingRecordVo<ParkingRecord> inquireDate(String loginTime, String loginOutTime, Integer page, Integer limit) {
        IPage<ParkingRecord> parkingRecordPage = new Page<>(page, limit);
        QueryWrapper<ParkingRecord> wrapper = new QueryWrapper<ParkingRecord>();
        wrapper.between("in_date_time", loginTime, loginOutTime);
        IPage<ParkingRecord> result = parkingRecordMapper.selectPage(parkingRecordPage, wrapper);
        List<ParkingRecord> records = result.getRecords();
        for (ParkingRecord parkingRecord : records) {
            if ("1".equals(parkingRecord.getStatus())) {
                parkingRecord.setStatus("正在泊车");
            } else {
                parkingRecord.setStatus("车辆已离开");
            }
        }
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }

    public ParkingRecordVo<ParkingRecord> inquireloginTime(String loginTime, Integer page, Integer limit) {
        IPage<ParkingRecord> parkingRecordPage = new Page<>(page, limit);
        QueryWrapper<ParkingRecord> wrapper = new QueryWrapper<ParkingRecord>();
        wrapper.ge("in_date_time", loginTime);
        IPage<ParkingRecord> result = parkingRecordMapper.selectPage(parkingRecordPage, wrapper);
        List<ParkingRecord> records = result.getRecords();
        for (ParkingRecord parkingRecord : records) {
            if ("1".equals(parkingRecord.getStatus())) {
                parkingRecord.setStatus("正在泊车");
            } else {
                parkingRecord.setStatus("车辆已离开");
            }
        }
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }

    public ParkingRecordVo<ParkingRecord> inquireloginOutTime(String loginOutTime, Integer page, Integer limit) {
        IPage<ParkingRecord> parkingRecordPage = new Page<>(page, limit);
        QueryWrapper<ParkingRecord> wrapper = new QueryWrapper<ParkingRecord>();
        wrapper.le("out_date_time", loginOutTime);
        IPage<ParkingRecord> result = parkingRecordMapper.selectPage(parkingRecordPage, wrapper);
        List<ParkingRecord> records = result.getRecords();
        for (ParkingRecord parkingRecord : records) {
            if ("1".equals(parkingRecord.getStatus())) {
                parkingRecord.setStatus("正在泊车");
            } else {
                parkingRecord.setStatus("车辆已离开");
            }
        }
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }

    public ParkingRecordVo<ParkingRecord> inquireAll(Integer page, Integer limit) {
        IPage<ParkingRecord> parkingRecordPage = new Page<>(page, limit);
        QueryWrapper<ParkingRecord> wrapper = new QueryWrapper<ParkingRecord>();
        IPage<ParkingRecord> result = parkingRecordMapper.selectPage(parkingRecordPage, null);
        List<ParkingRecord> records = result.getRecords();
        for (ParkingRecord parkingRecord : records) {
            if ("1".equals(parkingRecord.getStatus())) {
                parkingRecord.setStatus("正在泊车");
            } else {
                parkingRecord.setStatus("车辆已离开");
            }
        }
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }

    public ParkingRecordVo<ParkingRecord> inquireAllParams(String numberPlate, String loginTime, String loginOutTime, Integer page, Integer limit) {
        IPage<ParkingRecord> parkingRecordPage = new Page<>(page, limit);
        QueryWrapper<ParkingRecord> wrapper = new QueryWrapper<ParkingRecord>();
        wrapper.like("number_plate", numberPlate);
        wrapper.between("in_date_time", loginTime, loginOutTime);
        IPage<ParkingRecord> result = parkingRecordMapper.selectPage(parkingRecordPage, wrapper);
        List<ParkingRecord> records = result.getRecords();
        for (ParkingRecord parkingRecord : records) {
            if ("1".equals(parkingRecord.getStatus())) {
                parkingRecord.setStatus("正在泊车");
            } else {
                parkingRecord.setStatus("车辆已离开");
            }
        }
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }

    public ParkingRecordVo<ParkingRecord> inquireParkingNumberLogin(String numberPlate, String loginTime,Integer page, Integer limit){
        IPage<ParkingRecord> parkingRecordPage = new Page<>(page, limit);
        QueryWrapper<ParkingRecord> wrapper = new QueryWrapper<ParkingRecord>();
        wrapper.like("number_plate", numberPlate);
        wrapper.ge("in_date_time", loginTime);
        IPage<ParkingRecord> result = parkingRecordMapper.selectPage(parkingRecordPage, wrapper);
        List<ParkingRecord> records = result.getRecords();
        for (ParkingRecord parkingRecord : records) {
            if ("1".equals(parkingRecord.getStatus())) {
                parkingRecord.setStatus("正在泊车");
            } else {
                parkingRecord.setStatus("车辆已离开");
            }
        }
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }

    public ParkingRecordVo<ParkingRecord> inquireParkingNumberLoginOut(String numberPlate, String loginOutTime,Integer page, Integer limit){
        IPage<ParkingRecord> parkingRecordPage = new Page<>(page, limit);
        QueryWrapper<ParkingRecord> wrapper = new QueryWrapper<ParkingRecord>();
        wrapper.like("number_plate", numberPlate);
        wrapper.le("in_date_time", loginOutTime);
        IPage<ParkingRecord> result = parkingRecordMapper.selectPage(parkingRecordPage, wrapper);
        List<ParkingRecord> records = result.getRecords();
        for (ParkingRecord parkingRecord : records) {
            if ("1".equals(parkingRecord.getStatus())) {
                parkingRecord.setStatus("正在泊车");
            } else {
                parkingRecord.setStatus("车辆已离开");
            }
        }
        ParkingRecordVo<ParkingRecord> vo = new ParkingRecordVo<>();
        vo.setCode(0);
        vo.setMsg("");
        vo.setCount((int) result.getTotal());
        vo.setData(result.getRecords());
        return vo;
    }

}
