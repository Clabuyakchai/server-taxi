package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.BusDTO;

import java.util.List;

public interface BusService {
    BusDTO addBus(BusDTO busDTO);
    void deleteBus(Long busID);
    BusDTO findBusByStaffId(Long staffID);
    List<BusDTO> findAll();
}
