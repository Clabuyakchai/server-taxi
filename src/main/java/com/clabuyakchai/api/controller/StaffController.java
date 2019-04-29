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

    @PostMapping(value = "/signIn")
    public String signInUp(@RequestBody StaffDTO staffDTO){
        return staffService.signInUp(staffDTO);
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
}
