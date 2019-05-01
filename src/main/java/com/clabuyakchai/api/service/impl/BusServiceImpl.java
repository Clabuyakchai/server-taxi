package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.BusDTO;
import com.clabuyakchai.api.model.Bus;
import com.clabuyakchai.api.repository.BusRepository;
import com.clabuyakchai.api.service.BusService;
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
        busRepository.save(mapBusDtoToBus(busDTO, false));
    }

    @Override
    public void deleteBus(Long busID) {
        busRepository.deleteById(busID);
    }

    private BusDTO mapBusToBusDto(Bus bus){
        return new BusDTO(bus.getBusID(),
                bus.getBusmodel(),
                bus.getCarNumber(),
                bus.getCountSeat());
    }

    private Bus mapBusDtoToBus(BusDTO busDTO, Boolean flag){
        Bus bus = new Bus(busDTO.getBusmodel(),
                busDTO.getCarNumber(),
                busDTO.getCountSeat());

        if (flag){
            bus.setBusID(busDTO.getBusID());
        }

        return bus;
    }
}
