package com.clabuyakchai.api.controller;

import com.clabuyakchai.api.dto.StaffDTO;
import com.clabuyakchai.api.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public StaffDTO getStaff(@RequestParam String phone){
        return staffService.getStaffByPhone(phone);
    }

    @PostMapping(value = "/update")
    public StaffDTO updateStaff(@RequestBody StaffDTO staffDTO){
        return staffService.updateStaff(staffDTO);
    }

    @GetMapping(value = "/delete/{phone}")
    public void deleteStaff(@PathVariable String phone){
        staffService.deleteStaffByPhone(phone);
    }

    @GetMapping(value = "/drivebus")
    public void driveBus(@RequestParam Long staffID,@RequestParam Long busID){
        staffService.driveBus(staffID, busID);
    }
}
