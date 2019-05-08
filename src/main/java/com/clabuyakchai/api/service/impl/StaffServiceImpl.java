package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.StaffDTO;
import com.clabuyakchai.api.model.Bus;
import com.clabuyakchai.api.model.Staff;
import com.clabuyakchai.api.repository.BusRepository;
import com.clabuyakchai.api.repository.StaffRepository;
import com.clabuyakchai.api.security.JwtTokenProvider;
import com.clabuyakchai.api.service.StaffService;
import com.clabuyakchai.api.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final BusRepository busRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository, BusRepository busRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.staffRepository = staffRepository;
        this.busRepository = busRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String signIn(String phone) {
        if (staffRepository.existsStaffByPhone(phone)){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, "123"));
            return jwtTokenProvider.createToken(phone);
        } else {
            return null;
        }
    }

    @Override
    public String signUp(StaffDTO staffDTO) {
        staffRepository.save(Mapper.mapStaffDtoToStaff(staffDTO, false));
        return jwtTokenProvider.createToken(staffDTO.getPhone());
    }

    @Override
    public StaffDTO getStaffByPhone(HttpServletRequest req) {
        return Mapper.mapStaffToStaffDto(staffRepository.findStaffByPhone(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))));
    }

    @Override
    public StaffDTO updateStaff(StaffDTO staffDTO) {
        staffRepository.save(Mapper.mapStaffDtoToStaff(staffDTO, true));
        return staffDTO;
    }

    @Override
    public void deleteStaffByID(Long staffID) {
        staffRepository.deleteById(staffID);
    }

    @Override
    public void driveBus(Long staffID, Long busID) {
        Staff staff = staffRepository.findStaffByStaffID(staffID);
        Bus bus = busRepository.findBusByBusID(busID);
        staff.setBus(bus);
        staffRepository.save(staff);
    }
}
