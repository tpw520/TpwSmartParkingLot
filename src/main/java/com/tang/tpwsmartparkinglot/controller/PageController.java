package com.tang.tpwsmartparkinglot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(value = {"/login", "/"})
    public String JudgeLoginUser() {
        return "loginPage";
    }

    @GetMapping("/page/welcome.html")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/page/user-password.html")
    public String password() {
        return "user-password";
    }

    @GetMapping("/page/user-setting.html")
    public String setting() {
        return "user-setting";
    }

    @GetMapping("/page/parkingTable.html")
    public String parkingTable() {
        return "parkingTable";
    }

    @GetMapping("/add.html")
    public String add() {
        return "add";
    }

    @GetMapping("/edit.html")
    public String edit() {
        return "edit";
    }

    @GetMapping("/page/licensePlate.html")
    public String licensePlate() {
        return "licensePlate";
    }

    @GetMapping("/page/carMessage.html")
    public String carManager() {
        return "carMessage";
    }

    @GetMapping("/page/form-step.html")
    public String formStep() {
        return "form-step";
    }

    @GetMapping("/page/ParkingRecord.html")
    public String parkingRecord() {
        return "ParkingRecord";
    }

    @GetMapping("/page/dataTable.html")
    public String dataTable() {
        return "dataTable";
    }
}
