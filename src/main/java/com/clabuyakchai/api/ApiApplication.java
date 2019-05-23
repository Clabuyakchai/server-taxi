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
	private BookingService bookingService;

	@Autowired
	public ApiApplication(LocalService localService,
						  StaffService staffService,
						  BusService busService,
						  RouteService routeService,
						  StationService stationService,
						  BookingService bookingService) {
		this.localService = localService;
		this.staffService = staffService;
		this.busService = busService;
		this.routeService = routeService;
		this.stationService = stationService;
		this.bookingService = bookingService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create Local
		LocalDTO local = new LocalDTO(1L,
				"375447654321",
				"chai@gmail.com",
				"male",
				"Роман");
		localService.signUp(local);

		// Create Station
		StationDTO vol1 = new StationDTO(1L, "Сельхозтехника", "Воложин", "54.087945", "26.549502");
		StationDTO vol2 = new StationDTO(2L, "Вокзал", "Воложин", "54.086283", "26.535976");
		StationDTO vol3 = new StationDTO(3L, "Площадь свободы", "Воложин", "54.090100", "26.528255");
		StationDTO vol4 = new StationDTO(4L, "Банк", "Воложин", "54.092529", "26.520553");
		StationDTO vol5 = new StationDTO(5L, "Рынок", "Воложин", "54.093385", "26.517448");
		StationDTO vol6 = new StationDTO(6L, "Новый микрорайон", "Воложин", "54.094379", "26.513768");

		StationDTO min1 = new StationDTO(7L, "Каменная горка", "Минск", "53.907470", "27.436315");
		StationDTO min2 = new StationDTO(8L, "Кунцевщина", "Минск", "53.905979", "27.454706");
		StationDTO min3 = new StationDTO(9L, "Спортивная", "Минск", "53.908241", "27.479865");
		StationDTO min4 = new StationDTO(10L, "Пушкинская", "Минск", "53.909505", "27.497967");
		StationDTO min5 = new StationDTO(11L, "Молодежная", "Минск", "53.906350", "27.523587");
		StationDTO min6 = new StationDTO(12L, "Фрунзенская", "Минск", "53.905238", "27.539371");
		StationDTO min7 = new StationDTO(13L, "Площадь Ленина", "Минск", "53.892304", "27.545411");
		stationService.addStation(vol1);
		stationService.addStation(vol2);
		stationService.addStation(vol3);
		stationService.addStation(vol4);
		stationService.addStation(vol5);
		stationService.addStation(vol6);
		stationService.addStation(min1);
		stationService.addStation(min2);
		stationService.addStation(min3);
		stationService.addStation(min4);
		stationService.addStation(min5);
		stationService.addStation(min6);
		stationService.addStation(min7);

		// Create Bus
		BusDTO bus = new BusDTO(1L,
				"Volkswagen Sprinter",
				"AB1233-5",
				17);
		BusDTO bus2 = new BusDTO(2L,
				"Volkswagen Sprinter",
				"AB1243-5",
				17);
		BusDTO bus3 = new BusDTO(2L,
				"Mersedes",
				"AB1293-5",
				17);
		busService.addBus(bus);
		busService.addBus(bus2);
		busService.addBus(bus3);

		//Create Stuff
		StaffDTO staff = new StaffDTO(1L,
				"375441234567",
				"123@gmail.com",
				"female",
				"Маша",
				"Минск");
		StaffDTO staff2 = new StaffDTO(1L,
				"375291234567",
				"1233@gmail.com",
				"male",
				"Саша",
				"Минск");
		staffService.signUp(staff);
		staffService.signUp(staff2);
		staffService.driveBus(1L, 1L);
		staffService.driveBus(2L, 2L);

		// Create Route
		List<StationDTO> stationDTOS = new ArrayList<>();
		stationDTOS.add(vol1);
		stationDTOS.add(vol2);
		stationDTOS.add(min1);
		RouteDTO route = new RouteDTO("Минск",
				"Одесса",
				5.5f,
				"24.05.2019 13:00",
				stationDTOS,
				bus);
		RouteDTO route2 = new RouteDTO("Минск",
				"Воложин",
				5.5f,
				"24.05.2019 13:30",
				stationDTOS,
				bus2);
		routeService.createRoute(route);
		routeService.createRoute(route2);

		// Create Booking
		bookingService.addBook(1L, 1L);
		bookingService.addBook(1L, 2L);
	}
}
