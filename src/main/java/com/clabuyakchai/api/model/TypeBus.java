package com.clabuyakchai.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class TypeBus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeBusID;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "typeBus")
    private List<Bus> buses;
    @ManyToOne
    @JoinColumn(name = "seatID", referencedColumnName = "seatID")
    private Seat seat;

    public Long getTypeBusID() {
        return typeBusID;
    }

    public void setTypeBusID(Long typeBusID) {
        this.typeBusID = typeBusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
