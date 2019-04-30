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
    @OneToMany(mappedBy = "busRoute")
    private List<BusRoute> busRoutes;


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

    public List<BusRoute> getBusRoutes() {
        return busRoutes;
    }

    public void setBusRoutes(List<BusRoute> busRoutes) {
        this.busRoutes = busRoutes;
    }
}
