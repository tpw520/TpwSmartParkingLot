package com.tang.tpwsmartparkinglot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.tpwsmartparkinglot.entity.ParkingLot;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ParkingLotMapper extends BaseMapper<ParkingLot> {
    //添加停车场
    @Insert("INSERT INTO parkinglot(parking_id, parking_name,parking_space_count,timing_unit,unit_cost) VALUES(#{parkingId},#{parkingName},#{parkingSpaceCount},#{timingUnit},#{unitCost})")
    void addParkingLot(@Param("parkingId") Integer parkingId, @Param("parkingName") String parkingName, @Param("parkingSpaceCount") Integer parkingSpaceCount
            , @Param("timingUnit") Integer timingUnit, @Param("unitCost") Integer unitCost);

    //删除停车场
    @Delete("DELETE FROM `parkinglot` WHERE `parking_id` = #{parkingId}")
    Integer delParkingLot(int parseInt);

    //编辑停车场
    @Update("update parkinglot set parking_id=#{parkingId}, parking_name=#{parkingName},parking_space_count=#{parkingSpaceCount}, timing_unit=#{timingUnit} ,unit_cost=#{unitCost} where parking_id=#{parkingId} ")
    void editParkingLot(@Param("parkingId") Integer parkingId, @Param("parkingName") String parkingName, @Param("parkingSpaceCount") Integer parkingSpaceCount
            , @Param("timingUnit") Integer timingUnit, @Param("unitCost") Integer unitCost);

    //通过停车场名字找到停车场对象
    @Select("select * from parkinglot where parking_name = #{parkingName}")
    ParkingLot getParkingLotByName(String parkingName);

    @Select("select * from parkinglot")
    List<ParkingLot> selectParkingLotList();

    @Select("select * from parkinglot where parking_id = #{parkingId}")
    ParkingLot getParkingLotById(Integer parkingId);

}
