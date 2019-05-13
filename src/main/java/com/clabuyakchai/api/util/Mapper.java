package com.clabuyakchai.api.util;

import com.clabuyakchai.api.dto.*;
import com.clabuyakchai.api.model.*;

import java.util.ArrayList;
import java.util.List;


public class Mapper {

    public static LocalDTO mapLocalToLocalDto(Local local) {
        return new LocalDTO(local.getLocalID(),
                local.getPhone(),
                local.getEmail(),
                local.getGender(),
                local.getName());
    }

    public static Local mapLocalDtoToLocal(LocalDTO localDTO, Boolean flag) {
        Local local = new Local(localDTO.getName(),
                localDTO.getEmail(),
                localDTO.getPhone(),
                localDTO.getGender());

        if (flag) {
            local.setLocalID(localDTO.getLocalID());
        }

        return local;
    }

    public static List<BookingDTO> mapListBookingToListBookingDto(List<Booking> bookings) {
        List<BookingDTO> listBookDto = new ArrayList<>();
        for (Booking b : bookings) {
            listBookDto.add(new BookingDTO(b.getBookingID(),
                    b.getTimetable().getRoute().getFrom(),
                    b.getTimetable().getRoute().getTo(),
                    b.getTimetable().getRoute().getPrice(),
                    b.getTimetable().getDatetime()));
        }
        return listBookDto;
    }

    public static BusDTO mapBusToBusDto(Bus bus){
        return new BusDTO(bus.getBusID(),
                bus.getBusmodel(),
                bus.getCarNumber(),
                bus.getCountSeat());
    }

    public static List<BusDTO> mapListBusToListBusDTO(List<Bus> buses){
        List<BusDTO> busDTOS = new ArrayList<>();
        for (Bus b : buses) {
            busDTOS.add(new BusDTO(b.getBusID(),
                    b.getBusmodel(),
                    b.getCarNumber(),
                    b.getCountSeat()));
        }
        return busDTOS;
    }

    public static Bus mapBusDtoToBus(BusDTO busDTO, Boolean flag){
        Bus bus = new Bus(busDTO.getBusmodel(),
                busDTO.getCarNumber(),
                busDTO.getCountSeat());

        if (flag){
            bus.setBusID(busDTO.getBusID());
        }

        return bus;
    }

    public static RouteDTO mapRouteToRouteDto(Route route) {
        return new RouteDTO(route.getFrom(),
                route.getTo(),
                route.getPrice());
    }

    public static Route mapRouteDtoToRoute(RouteDTO routeDTO, Boolean flag) {
        Route route = new Route();
        route.setFrom(routeDTO.getFrom());
        route.setTo(routeDTO.getTo());
        route.setPrice(routeDTO.getPrice());

        if (flag){
            route.setRouteID(routeDTO.getRouteID());
        }

        return route;
    }

    public static StationDTO mapStationToStationDto(Station station){
        return new StationDTO(station.getStationID(),
                station.getName(),
                station.getCity(),
                station.getLat(),
                station.getLng());
    }

    public static Station mapStationDtoToStation(StationDTO stationDTO, Boolean flag) {
        Station station = new Station(stationDTO.getName(),
                stationDTO.getCity(),
                stationDTO.getLat(),
                stationDTO.getLng());

        if (flag) {
            station.setStationID(stationDTO.getStationID());
        }

        return station;
    }

    public static StaffDTO mapStaffToStaffDto(Staff staff){
        return new StaffDTO(staff.getStaffID(),
                staff.getPhone(),
                staff.getEmail(),
                staff.getGender(),
                staff.getName(),
                staff.getAddress());
    }

    public static Staff mapStaffDtoToStaff(StaffDTO staffDTO, Boolean flag){
        Staff staff = new Staff(staffDTO.getName(),
                staffDTO.getEmail(),
                staffDTO.getPhone(),
                staffDTO.getAddress(),
                staffDTO.getGender());

        if (flag){
            staff.setStaffID(staffDTO.getStaffID());
        }

        return staff;
    }

    public static List<StationDTO> mapListStationToListStationDto(List<Station> stations){
        List<StationDTO> stationDTOList = new ArrayList<>();
        for (Station station: stations) {
            stationDTOList.add(new StationDTO(station.getStationID(),
                    station.getName(),
                    station.getCity(),
                    station.getLat(),
                    station.getLng()));
        }
        return stationDTOList;
    }

    public static StaffDTO mapLocalToStaffDTO(Local local){
        return new StaffDTO(local.getLocalID(),
                local.getPhone(),
                local.getEmail(),
                local.getGender(),
                local.getName());
    }
}
