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
    public String signInUp(LocalDTO localDTO) {
        Local local = new Local();
        local.setEmail(localDTO.getEmail());
        local.setGender(localDTO.getGender());
        local.setName(localDTO.getName());
        local.setPhone(localDTO.getPhone());

        if (!localRepository.existsLocalByPhone(local.getPhone())){
            localRepository.save(local);
        } else {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(local.getPhone(), "123"));
        }

        return jwtTokenProvider.createToken(local.getPhone());
    }
}
