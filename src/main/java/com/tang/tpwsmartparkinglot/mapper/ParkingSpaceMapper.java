package com.tang.tpwsmartparkinglot.mapper;

import com.tang.tpwsmartparkinglot.entity.ParkingSpace;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ParkingSpaceMapper {
    @Select("select * from parkingspace")
    List<ParkingSpace> parkingLotSpaceEcharts();

    @Insert("insert into parkingspace(parking_name,parking_space) values(#{parkingName},#{parkingSpaceCount})")
    void addParkingSpace(@Param("parkingName") String parkingName, @Param("parkingSpaceCount") int parkingSpaceCount);

    @Delete("DELETE FROM parkingspace WHERE parking_name = #{parkingName} ")
    void deleteParkingSpace(String parkingName);

    @Update("update parkingspace set parking_name = #{parkingName},parking_space = #{parkingSpaceCount} where parking_name = #{parkingName}")
    void editParkingSpace(String parkingName, String parkingSpaceCount);
}
