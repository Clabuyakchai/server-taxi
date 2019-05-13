package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.StaffDTO;
import com.clabuyakchai.api.model.Booking;
import com.clabuyakchai.api.model.Bus;
import com.clabuyakchai.api.model.Local;
import com.clabuyakchai.api.model.Staff;
import com.clabuyakchai.api.repository.BookingRepository;
import com.clabuyakchai.api.repository.BusRepository;
import com.clabuyakchai.api.repository.LocalRepository;
import com.clabuyakchai.api.repository.StaffRepository;
import com.clabuyakchai.api.security.JwtTokenProvider;
import com.clabuyakchai.api.service.StaffService;
import com.clabuyakchai.api.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final BusRepository busRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final LocalRepository localRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository,
                            BusRepository busRepository,
                            JwtTokenProvider jwtTokenProvider,
                            AuthenticationManager authenticationManager,
                            LocalRepository localRepository,
                            BookingRepository bookingRepository) {
        this.staffRepository = staffRepository;
        this.busRepository = busRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.localRepository = localRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public String signIn(String phone) {
        if (staffRepository.existsStaffByPhone(phone) || localRepository.existsLocalByPhone(phone)){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, "123"));
            return jwtTokenProvider.createToken(phone);
        } else {
            return "null";
        }
    }

    @Override
    public String signUp(StaffDTO staffDTO) {
        staffRepository.save(Mapper.mapStaffDtoToStaff(staffDTO, false));
        return jwtTokenProvider.createToken(staffDTO.getPhone());
    }

    @Override
    public StaffDTO getStaffByPhone(HttpServletRequest req) {
        Staff staff = staffRepository.findStaffByPhone(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        if(staff != null){
            return Mapper.mapStaffToStaffDto(staff);
        }
        return null;
    }

    @Override
    public StaffDTO updateStaff(StaffDTO staffDTO) {
        Staff st = Mapper.mapStaffDtoToStaff(staffDTO, true);
        st.setBus(staffRepository.findStaffByStaffID(staffDTO.getStaffID()).getBus());
        staffRepository.save(st);
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

    @Override
    public void becomeDriver(String phone) {
        Local local = localRepository.findLocalByPhone(phone);
        List<Booking> bookings = bookingRepository.findBookingsByLocal(local);

        for (Booking booking: bookings) {
            bookingRepository.delete(booking);
        }

        localRepository.delete(local);
        signUp(Mapper.mapLocalToStaffDTO(local));
    }
}
