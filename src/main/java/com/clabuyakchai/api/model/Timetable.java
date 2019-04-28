package com.clabuyakchai.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timetableID;
    @Column(name = "`datetime`", nullable = false)
    private String datetime;
    @ManyToOne
    @JoinColumn(name = "routeID", referencedColumnName = "routeID")
    private Route route;
    @OneToMany(mappedBy = "timetable")
    private List<Booking> bookingList;

    public Long getTimetableID() {
        return timetableID;
    }

    public void setTimetableID(Long timetableID) {
        this.timetableID = timetableID;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
