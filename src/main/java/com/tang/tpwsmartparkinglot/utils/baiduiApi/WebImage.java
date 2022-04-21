package com.tang.tpwsmartparkinglot.utils.baiduiApi;

import com.alibaba.fastjson.JSON;
import com.tang.tpwsmartparkinglot.utils.Base64Util;
import com.tang.tpwsmartparkinglot.utils.FileUtil;
import com.tang.tpwsmartparkinglot.utils.HttpUtil;


import java.net.URLEncoder;
import java.util.Map;

public class WebImage {
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     * @return
     */
    public static String webImage(String str) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
        try {
            // 本地文件路径
            String filePath = str;//[本地文件路径]
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();//[调用鉴权接口获取的token]
            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            Map maps = (Map) JSON.parse(result);
            String number1 = "";
            for (Object map : maps.entrySet()){
                if("words_result".equals(((Map.Entry)map).getKey())){
                    Object value =  ((Map.Entry<String, Object>) map).getValue();
                    Map innerMap = (Map)JSON.parse(String.valueOf(value));
                    for (Object o : innerMap.entrySet()) {
                        if ("number".equals(((Map.Entry)o).getKey())){
                            number1 = (String) ((Map.Entry) o).getValue();
                            break;
                        }
                    }
                }
            }
            return number1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}