package com.clabuyakchai.api.controller;

import com.clabuyakchai.api.dto.BusDTO;
import com.clabuyakchai.api.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bus")
@RestController
public class BusController {
    private BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    /*

    {
  "busmodel" : "Volkswagen Sprinter",
  "carNumber" : "AB1233-5",
  "countSeat" : "17"
}

     */

    @PostMapping(value = "/add")
    public void addBus(@RequestBody BusDTO busDTO) {
        busService.addBus(busDTO);
    }

    @GetMapping(value = "/delete")
    public void deleteBus(@RequestParam Long busID) {
        busService.deleteBus(busID);
    }
}
