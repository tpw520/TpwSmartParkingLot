package com.tang.tpwsmartparkinglot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.tpwsmartparkinglot.entity.VipCar;
import org.apache.ibatis.annotations.*;

@Mapper
public interface VipCarMapper extends BaseMapper<VipCar> {

    @Insert("insert into vipcar(number_plate,car_root,car_phone,car_info,money,pay_mode) values(#{numberPlate},#{carRoot},#{carPhone},#{carInfo},#{money},#{payMode})")
    void addVipCar(@Param("numberPlate") String numberPlate, @Param("carRoot") String carRoot, @Param("money") int money, @Param("carPhone") String carPhone, @Param("payMode") String payMode, @Param("carInfo") String carInfo);

    @Select("select * from vipcar where number_plate = #{numberPlate}")
    VipCar checkVipCar(String numberPlate);

    @Update("update vipcar set number_plate = #{numberPlate},car_root = #{carRoot},car_phone = #{carPhone},car_info = #{carInfo},money = #{money},pay_mode = #{payMode} where number_plate = #{numberPlate} ")
    void editVipCar(@Param("numberPlate") String numberPlate, @Param("carRoot") String carRoot, @Param("money") int money, @Param("carPhone") String carPhone, @Param("payMode") String payMode, @Param("carInfo") String carInfo);
}
