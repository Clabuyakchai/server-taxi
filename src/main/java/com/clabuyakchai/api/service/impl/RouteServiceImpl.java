package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.BusDTO;
import com.clabuyakchai.api.dto.RouteDTO;
import com.clabuyakchai.api.dto.StationDTO;
import com.clabuyakchai.api.model.*;
import com.clabuyakchai.api.repository.*;
import com.clabuyakchai.api.service.RouteService;
import com.clabuyakchai.api.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final StationRouteRepository stationRouteRepository;
    private final BusRouteRepository busRouteRepository;
    private final TimetableRepository timetableRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, StationRouteRepository stationRouteRepository,
                            BusRouteRepository busRouteRepository, TimetableRepository timetableRepository) {
        this.routeRepository = routeRepository;
        this.stationRouteRepository = stationRouteRepository;
        this.busRouteRepository = busRouteRepository;
        this.timetableRepository = timetableRepository;
    }

    @Override
    public void createRoute(RouteDTO routeDTO) {
        Route route = Mapper.mapRouteDtoToRoute(routeDTO, false);
        routeRepository.save(route);

        route = routeRepository.findTopByOrderByRouteIDDesc();

        setStationRoute(route, routeDTO.getStations());
        setBusRoute(route, routeDTO.getBus());
        addTimetable(routeDTO.getDatetime(), route);
    }

    @Override
    public List<RouteDTO> findByDatetimeAndFromAndTo(String datetime, String from, String to) {
        List<Timetable> timetables = timetableRepository.findTimetableByDatetimeContainingAndRoute_FromAndRoute_To(datetime, from, to);
        List<RouteDTO> routeDTOList = new ArrayList<>();
        for (Timetable t : timetables) {
            RouteDTO routeDTO = new RouteDTO();
            routeDTO.setRouteID(t.getTimetableID());
            routeDTO.setDatetime(t.getDatetime());
            routeDTO.setFrom(t.getRoute().getFrom());
            routeDTO.setTo(t.getRoute().getTo());
            routeDTO.setPrice(t.getRoute().getPrice());

            List<BusRoute> busRoutes = t.getRoute().getBusRoutes();
            BusDTO busDTO = new BusDTO();
            for (BusRoute busRoute : busRoutes) {
                busDTO = Mapper.mapBusToBusDto(busRoute.getBus());
            }
            routeDTO.setBus(busDTO);

            List<StationRoute> stationRoutes = t.getRoute().getStationRoutes();
            List<StationDTO> stationDTOs = new ArrayList<>();
            for (StationRoute stationRoute : stationRoutes) {
                stationDTOs.add(Mapper.mapStationToStationDto(stationRoute.getStation()));
            }
            routeDTO.setStations(stationDTOs);
            routeDTOList.add(routeDTO);
        }
        return routeDTOList;
    }

    @Override
    public List<RouteDTO> findTimetableByDatetimeAndNameDriver(String datetime, String name) {
        //TODO как решить проблему bus null
        Set<Timetable> timetables = timetableRepository.findTimetableByStaff(datetime + "%", name);
        List<RouteDTO> routeDTOList = new ArrayList<>();
        for (Timetable t : timetables) {
            RouteDTO routeDTO = new RouteDTO();
            routeDTO.setRouteID(t.getTimetableID());
            routeDTO.setDatetime(t.getDatetime());
            routeDTO.setFrom(t.getRoute().getFrom());
            routeDTO.setTo(t.getRoute().getTo());
            routeDTO.setPrice(t.getRoute().getPrice());

            List<StationRoute> stationRoutes = t.getRoute().getStationRoutes();
            List<StationDTO> stationDTOs = new ArrayList<>();
            for (StationRoute stationRoute : stationRoutes) {
                stationDTOs.add(Mapper.mapStationToStationDto(stationRoute.getStation()));
            }
            routeDTO.setStations(stationDTOs);
            routeDTOList.add(routeDTO);
        }
        return routeDTOList;
    }

    private void addTimetable(String datetime, Route route) {
        timetableRepository.save(new Timetable(datetime, route));
    }

    private void setStationRoute(Route route, List<StationDTO> stationDTOs) {
        for (StationDTO stationDTO : stationDTOs) {
            Station station = Mapper.mapStationDtoToStation(stationDTO, true);
            stationRouteRepository.save(new StationRoute(route, station));
        }
    }

    private void setBusRoute(Route route, BusDTO busDTO) {
        Bus bus = Mapper.mapBusDtoToBus(busDTO, true);
        busRouteRepository.save(new BusRoute(route, bus));
    }


}