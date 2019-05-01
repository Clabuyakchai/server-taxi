package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.StationDTO;
import com.clabuyakchai.api.model.Station;
import com.clabuyakchai.api.repository.StationRepository;
import com.clabuyakchai.api.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public void addStation(StationDTO stationDTO) {
        stationRepository.save(mapStationDtoToStation(stationDTO));
    }

    @Override
    public StationDTO updateStation(StationDTO stationDTO) {
        Station station = stationRepository.findStationByStationID(stationDTO.getStationID());
        station.setCity(stationDTO.getCity());
        station.setLocation(stationDTO.getLocation());
        station.setName(stationDTO.getName());
        stationRepository.save(station);
        return mapStationToStationDto(station);
    }

    @Override
    public List<StationDTO> getStationsByCity(String city) {
        List<Station> stations = stationRepository.findStationsByCity(city);
        return mapListStationToListStatioDto(stations);
    }

    private StationDTO mapStationToStationDto(Station station){
        StationDTO stationDTO = new StationDTO();
        stationDTO.setStationID(station.getStationID());
        stationDTO.setCity(station.getCity());
        stationDTO.setLocation(station.getLocation());
        stationDTO.setName(station.getName());
        return stationDTO;
    }

    private Station mapStationDtoToStation(StationDTO stationDTO){
        Station station = new Station();
        station.setStationID(stationDTO.getStationID());
        station.setCity(stationDTO.getCity());
        station.setLocation(stationDTO.getLocation());
        station.setName(stationDTO.getName());
        return station;
    }

    private List<StationDTO> mapListStationToListStatioDto(List<Station> stations){
        List<StationDTO> stationDTOList = new ArrayList<>();
        for (Station s: stations) {
            StationDTO stationDTO = new StationDTO();
            stationDTO.setStationID(s.getStationID());
            stationDTO.setName(s.getName());
            stationDTO.setLocation(s.getLocation());
            stationDTO.setCity(s.getCity());
            stationDTOList.add(stationDTO);
        }

        return stationDTOList;
    }
}
