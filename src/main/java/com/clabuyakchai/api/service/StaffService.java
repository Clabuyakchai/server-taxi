package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.BusDTO;
import com.clabuyakchai.api.dto.StaffDTO;

import javax.servlet.http.HttpServletRequest;

public interface StaffService {
    String signIn(String phone);
    String signUp(StaffDTO staffDTO);
    StaffDTO getStaffByPhone(HttpServletRequest req);
    StaffDTO updateStaff(StaffDTO staffDTO);
    void deleteStaffByID(Long staffID);
    void driveBus(Long staffID, Long busID);
    void becomeDriver(String phone);
}
