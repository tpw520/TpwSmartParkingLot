package com.tang.tpwsmartparkinglot.service.ipml;

import cn.hutool.core.util.RandomUtil;
import com.tang.tpwsmartparkinglot.entity.ParkingLot;
import com.tang.tpwsmartparkinglot.entity.ParkingRecord;
import com.tang.tpwsmartparkinglot.service.LicensePlateService;
import com.tang.tpwsmartparkinglot.service.ParkingLotService;
import com.tang.tpwsmartparkinglot.service.ParkingRecordService;
import com.tang.tpwsmartparkinglot.utils.baiduiApi.WebImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LicensePlateServiceImpl implements LicensePlateService {
    @Autowired
    private ParkingRecordService parkingRecordService;
    @Autowired
    private ParkingLotService parkingLotService;
    @Override
    public String imageRecognition(MultipartFile fileName, String parkingName, String photograph) {
        String numberPlate = webImage(fileName, parkingName, photograph);
        if (numberPlate != null) {
            //查找当前识别出的车辆的停车记录
            ParkingRecord parkingRecord = parkingRecordService.checkCarStatus(numberPlate);
            if (parkingRecord == null){//true : 代表该车辆在停车场中不存在，属于进入停车场
                //向停车场中插入一条停车记录
                parkingRecordService.addparkingRecord(parkingName,numberPlate, LocalDateTime.now(),null,0,"1");

                return "车辆已成功进入"+parkingName+"停车场";
            }else if(parkingName.equals(parkingRecord.getParkingName())){ //1,停车场里存在该车辆 2,该车辆在同一停车场停车
                ParkingLot parkingLot = parkingLotService.getParkingLotByName(parkingName);//找到停车场
                Integer unitCost = null;
                Integer timingUnit = null;
                if (parkingLot != null) {
                    unitCost = parkingLot.getUnitCost();
                    timingUnit = parkingLot.getTimingUnit();
                }
                LocalDateTime inDateTime = parkingRecord.getInDateTime();
                long minutes = Duration.between(inDateTime, LocalDateTime.now()).toMinutes();
                int money = (int) (minutes / timingUnit * unitCost);
                parkingRecordService.updataParkingRecord(parkingName, numberPlate, inDateTime, LocalDateTime.now(), money, "0");
                return "车辆将离开停车场,请缴纳停车费"+money+"元";
            }else {
                return "车辆已在别处停车，请注意您的车辆是否被套牌";
            }

        }
        return "停车失败，请联系工作人员";
    }

    //将MultipartFile转换成文件
    public void copyFileWithBuffered(MultipartFile multipartFile, String name) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //1.造文件
//            String fileName = multipartFile.getOriginalFilename();
            File destFile = new File("D://resource/pictures/" + name);
            //2.造流
            //2.1 造节点流
//            FileInputStream fis = new FileInputStream((srcFile));
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2 造缓冲流
            InputStream inputStream = multipartFile.getInputStream();
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节：读取、写入
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    //解析来自前端车牌识别的类型并返回识别出的车牌号
    public String webImage(MultipartFile fileName, String parkingName, String photograph) {
        String originalFilename = null;//原始文件名
        String numberPlate = null;//车牌号
        if (!fileName.isEmpty()) {
            if (photograph != null) {
                String name = "Images" + RandomUtil.randomInt(1, 3000) + ".jpg";
                copyFileWithBuffered(fileName, name);
                numberPlate = WebImage.webImage("D://resource/pictures/" + name);
                return numberPlate;
            }
            originalFilename = fileName.getOriginalFilename();
            //识别车牌号
            numberPlate = WebImage.webImage("D://resource/pictures/" + originalFilename);
            return numberPlate;
        }
        return null;
    }

}
