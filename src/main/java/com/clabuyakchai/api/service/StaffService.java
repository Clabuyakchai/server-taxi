package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.BusDTO;
import com.clabuyakchai.api.dto.StaffDTO;

public interface StaffService {
    String signIn(String phone);
    String signUp(StaffDTO staffDTO);
    StaffDTO getStaffByPhone(String phone);
    StaffDTO updateStaff(StaffDTO staffDTO);
    void deleteStaffByPhone(String phone);
    void driveBus(Long staffID, Long busID);
}
