package com.clabuyakchai.api.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busID;
    @Column(nullable = false)
    private String busmodel;
    @OneToMany(mappedBy = "bus")
    private List<Staff> staff;
    @ManyToOne
    @JoinColumn(name = "typeBusID", referencedColumnName = "typeBusID")
    private TypeBus typeBus;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "busroute",
            joinColumns = {@JoinColumn(name = "busID")},
            inverseJoinColumns = {@JoinColumn(name = "routeID")}
    )
    private Set<Route> routes = new HashSet<>();


    public Long getBusID() {
        return busID;
    }

    public void setBusID(Long busID) {
        this.busID = busID;
    }

    public String getBusmodel() {
        return busmodel;
    }

    public void setBusmodel(String busmodel) {
        this.busmodel = busmodel;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public TypeBus getTypeBus() {
        return typeBus;
    }

    public void setTypeBus(TypeBus typeBus) {
        this.typeBus = typeBus;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }
}
