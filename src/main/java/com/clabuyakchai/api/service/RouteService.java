package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.RouteDTO;

import java.util.List;

public interface RouteService {
    void createRoute(RouteDTO routeDTO);

    List<RouteDTO> findByDatetimeAndFromAndTo(String datetime, String from, String to);
}
