package com.clabuyakchai.api.model;

import javax.persistence.*;

@Entity
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busRouteID;
    @ManyToOne
    @JoinColumn(name = "routeID", referencedColumnName = "routeID")
    private Route routeBus;
    @ManyToOne
    @JoinColumn(name = "busID", referencedColumnName = "busID")
    private Bus busRoute;

    public BusRoute() {
    }

    public BusRoute(Route routeBus, Bus busRoute) {
        this.routeBus = routeBus;
        this.busRoute = busRoute;
    }

    public Long getBusRouteID() {
        return busRouteID;
    }

    public void setBusRouteID(Long busRouteID) {
        this.busRouteID = busRouteID;
    }

    public Route getRouteBus() {
        return routeBus;
    }

    public void setRouteBus(Route routeBus) {
        this.routeBus = routeBus;
    }

    public Bus getBus() {
        return busRoute;
    }

    public void setBus(Bus bus) {
        this.busRoute = bus;
    }
}
