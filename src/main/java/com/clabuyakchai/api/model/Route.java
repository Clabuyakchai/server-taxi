package com.clabuyakchai.api.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routeID")
    private Long routeID;
    @Column(name = "`from`", nullable = false)
    private String from;
    @Column(name = "`to`", nullable = false)
    private String to;
    @Column(name = "price", nullable = false)
    private Float price;
    @OneToMany(mappedBy = "routeBus")
    private List<BusRoute> busRoutes;
    @OneToMany(mappedBy = "routeStation")
    private List<StationRoute> stationRoutes;
    @OneToMany(mappedBy = "route")
    private List<Timetable> timetables;

    public Long getRouteID() {
        return routeID;
    }

    public void setRouteID(Long routeID) {
        this.routeID = routeID;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<BusRoute> getBusRoutes() {
        return busRoutes;
    }

    public void setBusRoutes(List<BusRoute> busRoutes) {
        this.busRoutes = busRoutes;
    }

    public List<StationRoute> getStationRoutes() {
        return stationRoutes;
    }

    public void setStationRoutes(List<StationRoute> stationRoutes) {
        this.stationRoutes = stationRoutes;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }
}
