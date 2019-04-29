package com.clabuyakchai.api.controller;

import com.clabuyakchai.api.dto.StationDTO;
import com.clabuyakchai.api.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/station")
@RestController
public class StationController {
    private StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping(value = "/add")
    public void addStation(@RequestBody StationDTO stationDTO) {
        stationService.addStation(stationDTO);
    }

    @PostMapping(value = "/update")
    public StationDTO updateStation(@RequestBody StationDTO stationDTO){
        return stationService.updateStation(stationDTO);
    }

    @GetMapping(value = "/{city}")
    public List<StationDTO> getAllStationByCity(@PathVariable String city){
        return stationService.getStationsByCity(city);
    }
}
