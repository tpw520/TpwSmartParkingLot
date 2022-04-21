package com.tang.tpwsmartparkinglot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.tpwsmartparkinglot.entity.ParkingRecord;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ParkingRecordMapper extends BaseMapper<ParkingRecord> {

    //根据车牌号查询停车记录中是否存在该车，观察车辆的状态
    @Select("select *  from parkingrecord where number_plate  = #{numberPlate} and status = '1'")
    ParkingRecord checkCarStatus(String numberPlate);

    //向停车记录中插入一条停车记录
    @Insert("INSERT INTO parkingrecord(parking_name, number_plate,in_date_time,out_date_time,money,status) VALUES(#{parkingName},#{numberPlate},#{inDateTime},#{outDateTime},#{money},#{status})")
    void addparkingRecord(@Param("parkingName") String parkingName, @Param("numberPlate") String numberPlate, @Param("inDateTime") LocalDateTime inDateTime, @Param("outDateTime") LocalDateTime outDateTime, @Param("money") int money, @Param("status") String status);

    //向停车记录中更新一条停车记录
    @Update("update parkingrecord set parking_name=#{parkingName}, number_plate=#{numberPlate},in_date_time=#{inDateTime},out_date_time=#{outDateTime}, money=#{money} ,status=#{status} where number_plate=#{numberPlate} and status = '1'")
    void updataParkingRecord(String parkingName, String numberPlate, LocalDateTime inDateTime, LocalDateTime outDateTime, int money, String status);

    //当前停车数量
    @Select("select count(*) from parkingrecord where status = '1'")
    Integer parkingNumber();

    @Update("select count(*) from parkingrecord WHERE parking_name = #{parkingName} and status = '1'")
    Integer selectParkingStatusCount(String parkingName);
}
