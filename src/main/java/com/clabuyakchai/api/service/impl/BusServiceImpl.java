package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.BusDTO;
import com.clabuyakchai.api.repository.BusRepository;
import com.clabuyakchai.api.repository.SeatRepository;
import com.clabuyakchai.api.repository.TypeBusRepository;
import com.clabuyakchai.api.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusServiceImpl implements BusService {
    private final BusRepository busRepository;
    private final TypeBusRepository typeBusRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository, TypeBusRepository typeBusRepository, SeatRepository seatRepository) {
        this.busRepository = busRepository;
        this.typeBusRepository = typeBusRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public void addBus(BusDTO busDTO) {

    }
}
