package com.tang.tpwsmartparkinglot.mapper;

import com.tang.tpwsmartparkinglot.vo.ParkingEntityMoneyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EchartsDataMapper {
    @Select("select parking_name,sum(money) money from parkingrecord  group by parking_name")
    List<ParkingEntityMoneyVo> parkingLotMoneyEcharts();
}
