package com.clabuyakchai.api.model;

import javax.persistence.*;

@Entity
public class StationRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationRouteID;
    @ManyToOne
    @JoinColumn(name = "routeID", referencedColumnName = "routeID")
    private Route routeStation;
    @ManyToOne
    @JoinColumn(name = "stationID", referencedColumnName = "stationID")
    private Station station;

    public Long getStationRouteID() {
        return stationRouteID;
    }

    public void setStationRouteID(Long stationRouteID) {
        this.stationRouteID = stationRouteID;
    }

    public Route getRouteStation() {
        return routeStation;
    }

    public void setRouteStation(Route routeStation) {
        this.routeStation = routeStation;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
