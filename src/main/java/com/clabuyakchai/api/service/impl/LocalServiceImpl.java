package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.model.Local;
import com.clabuyakchai.api.repository.LocalRepository;
import com.clabuyakchai.api.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalServiceImpl implements LocalService {

    private final LocalRepository localRepository;

    @Autowired
    public LocalServiceImpl(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public List<Local> getAll() {
//        return localRepository.getAllLocal();
        return localRepository.findAll();
    }
}
