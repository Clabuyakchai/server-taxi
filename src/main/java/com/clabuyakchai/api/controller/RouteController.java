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


    /*

        {
          "datetime" : "02.05.2019 13:00",
          "from" : "Minsk",
          "to" : "Volozhin",
          "price" : "5.5",
          "stations" : [{ "stationID": 1, "name": "Ploshchad' svobody", "city": "Volozhin", "location": "123 123" },
                        { "stationID": 2, "name": "Novyy rayon", "city": "Volozhin", "location": "123 123" },
                        { "stationID": 3, "name": "Kamennaya gorka", "city": "Minsk", "location": "123 123" }]
        }

 */
    @PostMapping(value = "/create")
    public void createRoute(@RequestBody RouteDTO routeDTO) {
        routeService.createRoute(routeDTO);
    }

    // http://localhost:8090/route/routeby?datetime=02.05.2019&from=Minsk&to=Volozhin
    @GetMapping(value = "/routeby")
    public List<RouteDTO> findRoutesByFromAndTo(@RequestParam String datetime, @RequestParam String from, @RequestParam String to) {
        return routeService.findByDatetimeAndFromAndTo(datetime, from, to);
    }
}
