package com.clabuyakchai.api.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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
    @OneToMany(mappedBy = "station")
    private List<StationRoute> stationRoutes;

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

    public List<StationRoute> getStationRoutes() {
        return stationRoutes;
    }

    public void setStationRoutes(List<StationRoute> stationRoutes) {
        this.stationRoutes = stationRoutes;
    }
}
