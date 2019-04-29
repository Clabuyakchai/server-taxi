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

    //TODO
    @Override
    public String signInUp(LocalDTO localDTO) {
        Local local = mapLocalDtoToLocal(localDTO);

        if (!localRepository.existsLocalByPhone(local.getPhone())){
            localRepository.save(local);
        } else {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(local.getPhone(), "123"));
        }

        return jwtTokenProvider.createToken(local.getPhone());
    }

    @Override
    public LocalDTO getLocalByPhone(String phone) {
        return mapLocalToLocalDto(localRepository.findLocalByPhone(phone));
    }

    @Override
    public LocalDTO updateLocal(LocalDTO localDTO) {
        Local local = localRepository.findLocalByPhone(localDTO.getPhone());
        local = mapLocalDtoToLocal(local, localDTO);
        localRepository.save(local);
        return mapLocalToLocalDto(local);
    }

    @Override
    public void deleteLocalByPhone(String phone) {
        Local local = localRepository.findLocalByPhone(phone);
        localRepository.delete(local);
    }

    private LocalDTO mapLocalToLocalDto(Local local){
        LocalDTO localDTO = new LocalDTO();
        localDTO.setEmail(local.getEmail());
        localDTO.setGender(local.getGender());
        localDTO.setName(local.getName());
        localDTO.setPhone(local.getPhone());
        return localDTO;
    }

    private Local mapLocalDtoToLocal(LocalDTO localDTO){
        Local local = new Local();
        local.setEmail(localDTO.getEmail());
        local.setGender(localDTO.getGender());
        local.setName(localDTO.getName());
        local.setPhone(localDTO.getPhone());
        return local;
    }

    private Local mapLocalDtoToLocal(Local local, LocalDTO localDTO){
        local.setEmail(localDTO.getEmail());
        local.setGender(localDTO.getGender());
        local.setName(localDTO.getName());
        local.setPhone(localDTO.getPhone());
        return local;
    }
}
