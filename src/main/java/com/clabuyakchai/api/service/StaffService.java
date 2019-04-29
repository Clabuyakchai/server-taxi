package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.StaffDTO;

public interface StaffService {
    String signInUp(StaffDTO staffDTO);
    StaffDTO getStaffByPhone(String phone);
    StaffDTO updateStaff(StaffDTO staffDTO);
    void deleteStaffByPhone(String phone);
}
