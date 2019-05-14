package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.BookingDTO;
import com.clabuyakchai.api.model.Booking;

import java.util.List;

public interface BookingService {
    void addBook(Long localID, Long timetableID);
    void deleteBook(Long bookingID);
    List<BookingDTO> getBookingByLocalID(Long localID);
    List<BookingDTO> getBookingByLocalID(Long localID, String datetime);
}
