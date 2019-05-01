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
    @Column(nullable = false)
    private String carNumber;
    @Column(nullable = false)
    private Integer countSeat;
    @OneToMany(mappedBy = "bus")
    private List<Staff> staff;
    @OneToMany(mappedBy = "busRoute")
    private List<BusRoute> busRoutes;

    public Bus() {
    }

    public Bus(String busmodel, String carNumber, Integer countSeat) {
        this.busmodel = busmodel;
        this.carNumber = carNumber;
        this.countSeat = countSeat;
    }

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

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getCountSeat() {
        return countSeat;
    }

    public void setCountSeat(Integer countSeat) {
        this.countSeat = countSeat;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public List<BusRoute> getBusRoutes() {
        return busRoutes;
    }

    public void setBusRoutes(List<BusRoute> busRoutes) {
        this.busRoutes = busRoutes;
    }
}
