package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.LocalDTO;
import com.clabuyakchai.api.model.Local;
import com.clabuyakchai.api.model.Staff;
import com.clabuyakchai.api.repository.LocalRepository;
import com.clabuyakchai.api.repository.StaffRepository;
import com.clabuyakchai.api.security.JwtTokenProvider;
import com.clabuyakchai.api.service.LocalService;
import com.clabuyakchai.api.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocalServiceImpl implements LocalService {

    private final LocalRepository localRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final StaffRepository staffRepository;

    @Autowired
    public LocalServiceImpl(LocalRepository localRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, StaffRepository staffRepository) {
        this.localRepository = localRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Local> getAll() {
        return localRepository.findAll();
    }

    @Override
    public String signIn(String phone) {
        if (localRepository.existsLocalByPhone(phone)) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, "123"));
            return jwtTokenProvider.createToken(phone);
        } else {
            return null; //TODO
        }
    }

    @Override
    public String signUp(LocalDTO localDTO) {
        if (!staffRepository.existsStaffByPhone(localDTO.getPhone()) || !localRepository.existsLocalByPhone(localDTO.getPhone())){
            localRepository.save(Mapper.mapLocalDtoToLocal(localDTO, false));
            return jwtTokenProvider.createToken(localDTO.getPhone());
        } else {
            return null;
        }
    }

    @Override
    public LocalDTO getLocalByPhone(HttpServletRequest req) {
        Local local = localRepository.findLocalByPhone(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        if (local != null){
            return Mapper.mapLocalToLocalDto(local);
        }
        return new LocalDTO();
    }

    @Override
    public LocalDTO updateLocal(LocalDTO localDTO) {
        localRepository.save(Mapper.mapLocalDtoToLocal(localDTO, true));
        return localDTO;
    }

    @Override
    public void deleteLocalByID(Long localID) {
        localRepository.deleteById(localID);
    }

    @Override
    public List<LocalDTO> findLocalByTimetableID(Long timetableID) {
        List<Local> locals = localRepository.findLocalByTimetable(timetableID);
        List<LocalDTO> list = new ArrayList<>();
        for (Local local : locals) {
            list.add(Mapper.mapLocalToLocalDto(local));
        }
        return list;
    }
}
