package com.clabuyakchai.api.model;

import javax.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingID")
    private Long bookingID;
    @ManyToOne
    @JoinColumn(name = "timetableID", referencedColumnName = "timetableID")
    private Timetable timetable;
    @ManyToOne
    @JoinColumn(name = "localID", referencedColumnName = "localID")
    private Local local;
    @ManyToOne
    @JoinColumn(name = "seatID", referencedColumnName = "seatID")
    private Seat seat;

    public Long getBookingID() {
        return bookingID;
    }

    public void setBookingID(Long bookingID) {
        this.bookingID = bookingID;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
