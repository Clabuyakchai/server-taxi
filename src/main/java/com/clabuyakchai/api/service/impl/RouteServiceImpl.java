package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.BusDTO;
import com.clabuyakchai.api.dto.RouteDTO;
import com.clabuyakchai.api.dto.StationDTO;
import com.clabuyakchai.api.model.*;
import com.clabuyakchai.api.repository.*;
import com.clabuyakchai.api.service.RouteService;
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
    private final StationRepository stationRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, StationRouteRepository stationRouteRepository, BusRouteRepository busRouteRepository, TimetableRepository timetableRepository, StationRepository stationRepository) {
        this.routeRepository = routeRepository;
        this.stationRouteRepository = stationRouteRepository;
        this.busRouteRepository = busRouteRepository;
        this.timetableRepository = timetableRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public void createRoute(RouteDTO routeDTO) {
        Route route = mapRouteDtoToRoute(routeDTO);
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
            routeDTO.setDatetime(t.getDatetime());
            routeDTO.setFrom(t.getRoute().getFrom());
            routeDTO.setTo(t.getRoute().getTo());
            routeDTO.setPrice(t.getRoute().getPrice());

            List<BusRoute> busRoutes = t.getRoute().getBusRoutes();
            BusDTO busDTO = new BusDTO();
            for (BusRoute busRoute : busRoutes) {
                busDTO = mapBusToBusDto(busRoute.getBus());
            }
            routeDTO.setBus(busDTO);

            List<StationRoute> stationRoutes = t.getRoute().getStationRoutes();
            List<StationDTO> stationDTOs = new ArrayList<>();
            for (StationRoute stationRoute : stationRoutes) {
                stationDTOs.add(mapStationToStationDto(stationRoute.getStation()));
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
            routeDTO.setDatetime(t.getDatetime());
            routeDTO.setFrom(t.getRoute().getFrom());
            routeDTO.setTo(t.getRoute().getTo());
            routeDTO.setPrice(t.getRoute().getPrice());

            List<StationRoute> stationRoutes = t.getRoute().getStationRoutes();
            List<StationDTO> stationDTOs = new ArrayList<>();
            for (StationRoute stationRoute : stationRoutes) {
                stationDTOs.add(mapStationToStationDto(stationRoute.getStation()));
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
            Station station = mapStationDtoToStation(stationDTO, true);
            stationRouteRepository.save(new StationRoute(route, station));
        }
    }

    private void setBusRoute(Route route, BusDTO busDTO) {
        Bus bus = mapBusDtoToBus(busDTO, true);
        busRouteRepository.save(new BusRoute(route, bus));
    }

    private RouteDTO mapRouteToRouteDto(Route route) {
        return new RouteDTO(route.getFrom(),
                route.getTo(),
                route.getPrice());
    }

    private Route mapRouteDtoToRoute(RouteDTO routeDTO) {
        Route route = new Route();
        route.setFrom(routeDTO.getFrom());
        route.setTo(routeDTO.getTo());
        route.setPrice(routeDTO.getPrice());
        return route;
    }

    private StationDTO mapStationToStationDto(Station station){
        return new StationDTO(station.getStationID(),
                station.getName(),
                station.getCity(),
                station.getLocation());
    }

    private Station mapStationDtoToStation(StationDTO stationDTO, Boolean flag) {
        Station station = new Station(stationDTO.getName(),
                stationDTO.getCity(),
                stationDTO.getLocation());

        if (flag) {
            station.setStationID(stationDTO.getStationID());
        }

        return station;
    }

    private Bus mapBusDtoToBus(BusDTO busDTO, Boolean flag) {
        Bus bus = new Bus(busDTO.getBusmodel(),
                busDTO.getCarNumber(),
                busDTO.getCountSeat());

        if (flag) {
            bus.setBusID(busDTO.getBusID());
        }

        return bus;
    }

    private BusDTO mapBusToBusDto(Bus bus){
        return new BusDTO(bus.getBusID(),
                bus.getBusmodel(),
                bus.getCarNumber(),
                bus.getCountSeat());
    }
}