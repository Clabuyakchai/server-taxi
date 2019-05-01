package com.clabuyakchai.api;

import com.clabuyakchai.api.dto.*;
import com.clabuyakchai.api.model.Local;
import com.clabuyakchai.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	private LocalService localService;
	private StaffService staffService;
	private BusService busService;
	private RouteService routeService;
	private StationService stationService;

	@Autowired
	public ApiApplication(LocalService localService, StaffService staffService, BusService busService, RouteService routeService, StationService stationService) {
		this.localService = localService;
		this.staffService = staffService;
		this.busService = busService;
		this.routeService = routeService;
		this.stationService = stationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create Local
		LocalDTO local = new LocalDTO(1L,
				"Roman",
				"chai@gmail.com",
				"375291234567",
				"male");
		localService.signUp(local);

		// Create Station
		StationDTO station = new StationDTO(1L,
				"Ploshchad' svobody",
				"Volozhin",
				"123 123");
		StationDTO station1 = new StationDTO(2L,
				"Novyy rayon",
				"Volozhin",
				"123 123");
		StationDTO station2 = new StationDTO(3L,
				"Kamennaya gorka",
				"Minsk",
				"123 123");
		stationService.addStation(station);
		stationService.addStation(station1);
		stationService.addStation(station2);

		// Create Bus
		BusDTO bus = new BusDTO(1L,
				"Volkswagen Sprinter",
				"AB1233-5",
				17);
		BusDTO bus2 = new BusDTO(2L,
				"Volkswagen Sprinter",
				"AB1243-5",
				17);
		busService.addBus(bus);
		busService.addBus(bus2);

		//Create Stuff
		StaffDTO staff = new StaffDTO(1L,
				"375442345678",
				"123@gmail.com",
				"female",
				"Masha",
				"Minsk");
		StaffDTO staff2 = new StaffDTO(2L,
				"375442345008",
				"1233@gmail.com",
				"male",
				"Sasha",
				"Minsk");
		staffService.signUp(staff);
		staffService.signUp(staff2);
		staffService.driveBus(1L, 1L);
		staffService.driveBus(2L, 2L);

		// Create Route
		List<StationDTO> stationDTOS = new ArrayList<>();
		stationDTOS.add(station);
		stationDTOS.add(station1);
		stationDTOS.add(station2);
		RouteDTO route = new RouteDTO("Minsk",
				"Volozhin",
				5.5f,
				"03.05.2019 13:00",
				stationDTOS,
				bus);
		RouteDTO route2 = new RouteDTO("Minsk",
				"Volozhin",
				5.5f,
				"03.05.2019 13:30",
				stationDTOS,
				bus2);
		routeService.createRoute(route);
		routeService.createRoute(route2);
	}
}
