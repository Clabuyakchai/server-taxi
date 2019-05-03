package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.BusDTO;
import com.clabuyakchai.api.model.Bus;
import com.clabuyakchai.api.repository.BusRepository;
import com.clabuyakchai.api.service.BusService;
import com.clabuyakchai.api.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusServiceImpl implements BusService {
    private final BusRepository busRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public void addBus(BusDTO busDTO) {
        busRepository.save(Mapper.mapBusDtoToBus(busDTO, false));
    }

    @Override
    public void deleteBus(Long busID) {
        busRepository.deleteById(busID);
    }
}
