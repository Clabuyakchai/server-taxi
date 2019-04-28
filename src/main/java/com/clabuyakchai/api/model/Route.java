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
    @ManyToMany(mappedBy = "routes")
    private Set<Bus> buses = new HashSet<>();
    @ManyToMany(mappedBy = "routes")
    private Set<Station> stations = new HashSet<>();
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

    public Set<Bus> getBuses() {
        return buses;
    }

    public void setBuses(Set<Bus> buses) {
        this.buses = buses;
    }

//    public Set<Station> getStations() {
//        return stations;
//    }
//
//    public void setStations(Set<Station> stations) {
//        this.stations = stations;
//    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }
}
