package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.LocalDTO;
import com.clabuyakchai.api.model.Local;
import com.clabuyakchai.api.repository.LocalRepository;
import com.clabuyakchai.api.security.JwtTokenProvider;
import com.clabuyakchai.api.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalServiceImpl implements LocalService {

    private final LocalRepository localRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LocalServiceImpl(LocalRepository localRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.localRepository = localRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public List<Local> getAll() {
        return localRepository.findAll();
    }

    @Override
    public String signIn(String phone) {
        if (localRepository.existsLocalByPhone(phone)){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, "123"));
            return jwtTokenProvider.createToken(phone);
        } else {
            return "null";
        }
    }

    @Override
    public String signUp(LocalDTO localDTO) {
        localRepository.save(mapLocalDtoToLocal(localDTO, false));
        return jwtTokenProvider.createToken(localDTO.getPhone());
    }

    @Override
    public LocalDTO getLocalByPhone(String phone) {
        return mapLocalToLocalDto(localRepository.findLocalByPhone(phone));
    }

    @Override
    public LocalDTO updateLocal(LocalDTO localDTO) {
        localRepository.save(mapLocalDtoToLocal(localDTO, true));
        return localDTO;
    }

    @Override
    public void deleteLocalByPhone(String phone) {
        Local local = localRepository.findLocalByPhone(phone);
        localRepository.delete(local);
    }

    private LocalDTO mapLocalToLocalDto(Local local) {
        return new LocalDTO(local.getLocalID(),
                local.getPhone(),
                local.getEmail(),
                local.getGender(),
                local.getName());
    }

    private Local mapLocalDtoToLocal(LocalDTO localDTO, Boolean flag) {
        Local local = new Local(localDTO.getName(),
                localDTO.getEmail(),
                localDTO.getPhone(),
                localDTO.getGender());

        if (flag){
            local.setLocalID(localDTO.getLocalID());
        }

        return local;
    }
}
