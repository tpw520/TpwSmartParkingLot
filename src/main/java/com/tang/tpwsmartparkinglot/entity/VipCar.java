package com.tang.tpwsmartparkinglot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("vipcar")
//车辆信息
public class VipCar {
    //车牌号
    private String numberPlate;
    //车辆权限
    private String carRoot;
    //车辆联系方式
    private String carPhone;
    //车辆信息
    private String carInfo;
    //VIP缴费费用
    private Integer money;
    //支付方式
    private String payMode;
}