package com.tang.tpwsmartparkinglot.utils.customize;

import com.tang.tpwsmartparkinglot.utils.baiduiApi.WebImage;
import org.springframework.web.multipart.MultipartFile;

public class FileJudgeUtil {
    public static String fileJudgeUtil(MultipartFile fileName){
        String originalFilename = null;
        String  imageNumber = null;
        //判断传入的文件是否为空，防止后面出现空指针异常
        if (!fileName.isEmpty()) {
            originalFilename = fileName.getOriginalFilename();
            //识别车牌号
            imageNumber = WebImage.webImage("D://resource/pictures/" + originalFilename);
        }
        return imageNumber;
    }
}
