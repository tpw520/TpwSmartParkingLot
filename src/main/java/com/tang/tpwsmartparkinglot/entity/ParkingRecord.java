package com.tang.tpwsmartparkinglot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("parkingrecord")
public class ParkingRecord {
    private String parkingName;
    private String numberPlate;
    private LocalDateTime inDateTime;
    private LocalDateTime outDateTime;
    private Integer money;
    private String status;
}