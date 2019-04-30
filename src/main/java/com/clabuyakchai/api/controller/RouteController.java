package com.clabuyakchai.api.controller;

import com.clabuyakchai.api.dto.RouteDTO;
import com.clabuyakchai.api.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/route")
@RestController
public class RouteController {
    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping(value = "/create")
    public void createRoute(@RequestBody RouteDTO routeDTO){
        routeService.createRoute(routeDTO);
    }

    @GetMapping(value = "/routeby")
    public List<RouteDTO> findRoutesByFromAndTo(@RequestParam String from, @RequestParam String to){
        return routeService.findByFromAndTo(from, to);
    }
}
