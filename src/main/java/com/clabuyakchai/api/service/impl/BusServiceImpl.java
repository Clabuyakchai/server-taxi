package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.BusDTO;
import com.clabuyakchai.api.model.Bus;
import com.clabuyakchai.api.model.Staff;
import com.clabuyakchai.api.repository.BusRepository;
import com.clabuyakchai.api.repository.StaffRepository;
import com.clabuyakchai.api.service.BusService;
import com.clabuyakchai.api.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {
    private final BusRepository busRepository;
    private final StaffRepository staffRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository, StaffRepository staffRepository) {
        this.busRepository = busRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public BusDTO addBus(BusDTO busDTO) {
        busRepository.save(Mapper.mapBusDtoToBus(busDTO, false));
        return Mapper.mapBusToBusDto(busRepository.findBusByBusmodelAndCarNumber(busDTO.getBusmodel(), busDTO.getCarNumber()));
    }

    @Override
    public void deleteBus(Long busID) {
        busRepository.deleteById(busID);
    }

    @Override
    public BusDTO findBusByStaffId(Long staffID) {
        Staff staff = staffRepository.findStaffByStaffID(staffID);
        return Mapper.mapBusToBusDto(busRepository.findBusByStaff(staff));
    }

    @Override
    public List<BusDTO> findAll() {
        return Mapper.mapListBusToListBusDTO(busRepository.findAll());
    }
}
