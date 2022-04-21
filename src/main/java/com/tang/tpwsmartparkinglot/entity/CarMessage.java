package com.tang.tpwsmartparkinglot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("carmessage")
//车辆信息
public class CarMessage {
    //车牌号
    private String numberPlate;
    //车辆权限
    private String carRoot;
    //车辆联系方式
    private String carPlone;
    //车辆信息
    private String carInfo;
}
