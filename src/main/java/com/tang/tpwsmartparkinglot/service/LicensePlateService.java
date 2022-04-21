package com.tang.tpwsmartparkinglot.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface LicensePlateService {
     String imageRecognition(MultipartFile fileName, String parkingName, String photograph);
}
