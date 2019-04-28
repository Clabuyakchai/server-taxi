package com.clabuyakchai.api.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationID;
    @Column(name = "`name`", nullable = false)
    private String name;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String location;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "stationroute",
            joinColumns = {@JoinColumn(name = "stationID")},
            inverseJoinColumns = {@JoinColumn(name = "routeID")}
    )
    private Set<Route> routes = new HashSet<>();

    public Long getStationID() {
        return stationID;
    }

    public void setStationID(Long stationID) {
        this.stationID = stationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }
}
