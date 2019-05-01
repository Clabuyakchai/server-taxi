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
        stationRepository.save(mapStationDtoToStation(stationDTO, false));
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
        return mapListStationToListStationDto(stations);
    }

    private StationDTO mapStationToStationDto(Station station){
        return new StationDTO(station.getStationID(),
                station.getName(),
                station.getCity(),
                station.getLocation());
    }

    private Station mapStationDtoToStation(StationDTO stationDTO, Boolean flag){
         Station station = new Station(stationDTO.getName(),
                stationDTO.getCity(),
                stationDTO.getLocation());

         if(flag){
             station.setStationID(stationDTO.getStationID());
         }

        return station;
    }

    private List<StationDTO> mapListStationToListStationDto(List<Station> stations){
        List<StationDTO> stationDTOList = new ArrayList<>();
        for (Station station: stations) {
            stationDTOList.add(new StationDTO(station.getStationID(),
                    station.getName(),
                    station.getCity(),
                    station.getLocation()));
        }
        return stationDTOList;
    }
}
