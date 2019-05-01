package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.RouteDTO;
import com.clabuyakchai.api.model.Route;
import com.clabuyakchai.api.model.Station;
import com.clabuyakchai.api.model.StationRoute;
import com.clabuyakchai.api.model.Timetable;
import com.clabuyakchai.api.repository.RouteRepository;
import com.clabuyakchai.api.repository.StationRepository;
import com.clabuyakchai.api.repository.StationRouteRepository;
import com.clabuyakchai.api.repository.TimetableRepository;
import com.clabuyakchai.api.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final StationRouteRepository stationRouteRepository;
    private final TimetableRepository timetableRepository;
    private final StationRepository stationRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, StationRouteRepository stationRouteRepository, TimetableRepository timetableRepository, StationRepository stationRepository) {
        this.routeRepository = routeRepository;
        this.stationRouteRepository = stationRouteRepository;
        this.timetableRepository = timetableRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public void createRoute(RouteDTO routeDTO) {
        Route route = new Route(); //TODO
        route.setFrom(routeDTO.getFrom());
        route.setTo(routeDTO.getTo());
        route.setPrice(routeDTO.getPrice());
        routeRepository.save(route);

        route = routeRepository.findTopByOrderByRouteIDDesc();

        setStationRoute(route, routeDTO.getStations());
        addTimetable(routeDTO.getDatetime(), route);

//        routeRepository.save(mapRouteDtoToRoute(routeDTO));
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

            List<StationRoute> stationRoutes = t.getRoute().getStationRoutes();
            List<Station> stations = new ArrayList<>();
            for (StationRoute stationRoute:
                 stationRoutes) {
                stations.add(stationRoute.getStation());
            }
            routeDTO.setStations(stations);
            routeDTOList.add(routeDTO);
        }
        return routeDTOList;
    }

    private void addTimetable(String datetime, Route route) {
        Timetable timetable = new Timetable();
        timetable.setDatetime(datetime);
        timetable.setRoute(route);
        timetableRepository.save(timetable);
    }

    private void setStationRoute(Route route, List<Station> stations) {
        for (Station station : stations) {
            StationRoute stationRoute = new StationRoute();
            stationRoute.setStation(station);
            stationRoute.setRouteStation(route);
            stationRouteRepository.save(stationRoute);
        }
    }

    private RouteDTO mapRouteToRouteDto(Route route) {
        RouteDTO routeDTO = new RouteDTO();
//        routeDTO.setId(route.getRouteID());
        routeDTO.setFrom(route.getFrom());
        routeDTO.setTo(route.getTo());
        routeDTO.setPrice(route.getPrice());
        return routeDTO;
    }

    private Route mapRouteDtoToRoute(RouteDTO routeDTO) {
        Route route = new Route();
//        route.setRouteID(routeDTO.getId());
        route.setFrom(routeDTO.getFrom());
        route.setTo(routeDTO.getTo());
        route.setPrice(routeDTO.getPrice());
        return route;
    }

    private List<RouteDTO> mapListRouteToListRouteDto(List<Route> routes) {
        List<RouteDTO> routeDTOList = new ArrayList<>();
        for (Route r : routes) {
            RouteDTO routeDTO = new RouteDTO();
//            routeDTO.setId(r.getRouteID());
            routeDTO.setFrom(r.getFrom());
            routeDTO.setTo(r.getTo());
            routeDTO.setPrice(r.getPrice());
            routeDTOList.add(routeDTO);
        }

        return routeDTOList;
    }
}