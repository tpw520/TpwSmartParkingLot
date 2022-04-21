package com.tang.tpwsmartparkinglot.service;

import java.util.List;
import java.util.Map;

public interface ParkingSpaceService {

    void addParkingSpace(Map<String, String> map);

    void deleteParkingSpace(String parkingId);

    void editParkingSpace(Map<String, String> map);
}
