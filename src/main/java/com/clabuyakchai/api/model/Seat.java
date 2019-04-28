package com.clabuyakchai.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatID;
    @Column(nullable = false)
    private String location;
    @OneToMany(mappedBy = "seat")
    private List<TypeBus> typeBuses;
    @OneToMany(mappedBy = "seat")
    private List<Booking> bookings;

    public Long getSeatID() {
        return seatID;
    }

    public void setSeatID(Long seatID) {
        this.seatID = seatID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<TypeBus> getTypeBuses() {
        return typeBuses;
    }

    public void setTypeBuses(List<TypeBus> typeBuses) {
        this.typeBuses = typeBuses;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
