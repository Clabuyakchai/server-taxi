package com.clabuyakchai.api.controller;

import com.clabuyakchai.api.dto.StaffDTO;
import com.clabuyakchai.api.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/staff")
@RestController
public class StaffController {
    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    /*
    {
  "phone" : "+374291234567",
  "email" : "123@gmail.com",
  "gender" : "male",
  "name" : "Maxik",
  "address" : "Minsk"
}

     */
    @GetMapping(value = "/signin")
    public String signIn(@RequestParam String phone){
        return staffService.signIn(phone);
    }

    @PostMapping(value = "/signup")
    public String signUp(@RequestBody StaffDTO staffDTO){
        return staffService.signUp(staffDTO);
    }

    @GetMapping(value = "/me")
    public StaffDTO getStaff(HttpServletRequest req){
        return staffService.getStaffByPhone(req);
    }

    @PostMapping(value = "/update")
    public StaffDTO updateStaff(@RequestBody StaffDTO staffDTO){
        return staffService.updateStaff(staffDTO);
    }

    @GetMapping(value = "/delete")
    public void deleteStaff(@RequestParam Long staffID){
        staffService.deleteStaffByID(staffID);
    }

    @GetMapping(value = "/drivebus")
    public void driveBus(@RequestParam Long staffID,@RequestParam Long busID){
        staffService.driveBus(staffID, busID);
    }

    @GetMapping(value = "/becomedriver")
    public void becomeDriver(@RequestParam String phone){
        staffService.becomeDriver(phone);
    }
}
