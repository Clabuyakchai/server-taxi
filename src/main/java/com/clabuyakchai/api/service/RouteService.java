package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.RouteDTO;
import com.clabuyakchai.api.model.Timetable;

import java.util.List;
import java.util.Set;

public interface RouteService {
    void createRoute(RouteDTO routeDTO);

    List<RouteDTO> findByDatetimeAndFromAndTo(String datetime, String from, String to);

    List<RouteDTO> findTimetableByDatetimeAndNameDriver(String datetime, String name);
}
