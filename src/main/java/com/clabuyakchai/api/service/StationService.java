package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.StationDTO;

import java.util.List;

public interface StationService {
    void addStation(StationDTO stationDTO);
    StationDTO updateStation(StationDTO stationDTO);
    List<StationDTO> getStationsByCity(String city);
}
