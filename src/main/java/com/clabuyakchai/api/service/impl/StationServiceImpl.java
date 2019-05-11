package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.StationDTO;
import com.clabuyakchai.api.model.Station;
import com.clabuyakchai.api.repository.StationRepository;
import com.clabuyakchai.api.service.StationService;
import com.clabuyakchai.api.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public StationDTO addStation(StationDTO stationDTO) {
        stationRepository.save(Mapper.mapStationDtoToStation(stationDTO, false));
        return Mapper.mapStationToStationDto(stationRepository.findStationByLatAndLng(stationDTO.getLat(), stationDTO.getLng()));
    }

    @Override
    public StationDTO updateStation(StationDTO stationDTO) {
        Station station = stationRepository.findStationByStationID(stationDTO.getStationID());
        station.setCity(stationDTO.getCity());
        station.setLat(stationDTO.getLat());
        station.setLng(stationDTO.getLng());
        station.setName(stationDTO.getName());
        stationRepository.save(station);
        return Mapper.mapStationToStationDto(station);
    }

    @Override
    public List<StationDTO> getStationsByCity(String city) {
        List<Station> stations = stationRepository.findStationsByCity(city);
        return Mapper.mapListStationToListStationDto(stations);
    }

    @Override
    public List<StationDTO> getStation() {
        List<Station> stations = stationRepository.findAll();
        return Mapper.mapListStationToListStationDto(stations);
    }
}
