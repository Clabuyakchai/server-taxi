package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.StaffDTO;
import com.clabuyakchai.api.model.Staff;
import com.clabuyakchai.api.repository.StaffRepository;
import com.clabuyakchai.api.security.JwtTokenProvider;
import com.clabuyakchai.api.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.staffRepository = staffRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    //TODO
    @Override
    public String signInUp(StaffDTO staffDTO) {
        Staff staff = mapStaffDtoToStaff(staffDTO);

        if (!staffRepository.existsStaffByPhone(staff.getPhone())){
            staffRepository.save(staff);
        } else {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(staff.getPhone(), "123"));
        }

        return jwtTokenProvider.createToken(staff.getPhone());
    }

    @Override
    public StaffDTO getStaffByPhone(String phone) {
        return mapStaffToStaffDto(staffRepository.findStaffByPhone(phone));
    }

    @Override
    public StaffDTO updateStaff(StaffDTO staffDTO) {
        Staff staff = staffRepository.findStaffByPhone(staffDTO.getPhone());
        staff = mapStaffDtoToStaff(staff, staffDTO);
        staffRepository.save(staff);
        return mapStaffToStaffDto(staff);
    }

    @Override
    public void deleteStaffByPhone(String phone) {
        Staff staff = staffRepository.findStaffByPhone(phone);
        staffRepository.delete(staff);
    }

    private StaffDTO mapStaffToStaffDto(Staff staff){
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setGender(staff.getGender());
        staffDTO.setName(staff.getName());
        staffDTO.setPhone(staff.getPhone());
        staffDTO.setAddress(staff.getAddress());
        return staffDTO;
    }

    private Staff mapStaffDtoToStaff(StaffDTO staffDTO){
        Staff staff = new Staff();
        staff.setEmail(staffDTO.getEmail());
        staff.setGender(staffDTO.getGender());
        staff.setName(staffDTO.getName());
        staff.setPhone(staffDTO.getPhone());
        staff.setAddress(staffDTO.getAddress());
        return staff;
    }

    private Staff mapStaffDtoToStaff(Staff staff, StaffDTO staffDTO){
        staff.setEmail(staffDTO.getEmail());
        staff.setGender(staffDTO.getGender());
        staff.setName(staffDTO.getName());
        staff.setPhone(staffDTO.getPhone());
        staff.setAddress(staffDTO.getAddress());
        return staff;
    }
}
