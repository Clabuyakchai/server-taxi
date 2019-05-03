package com.clabuyakchai.api.service.impl;

import com.clabuyakchai.api.dto.BookingDTO;
import com.clabuyakchai.api.model.Booking;
import com.clabuyakchai.api.model.Local;
import com.clabuyakchai.api.model.Timetable;
import com.clabuyakchai.api.repository.BookingRepository;
import com.clabuyakchai.api.repository.LocalRepository;
import com.clabuyakchai.api.repository.TimetableRepository;
import com.clabuyakchai.api.service.BookingService;
import com.clabuyakchai.api.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final LocalRepository localRepository;
    private final TimetableRepository timetableRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, LocalRepository localRepository, TimetableRepository timetableRepository) {
        this.bookingRepository = bookingRepository;
        this.localRepository = localRepository;
        this.timetableRepository = timetableRepository;
    }

    @Override
    public void addBook(Long localID, Long timetableID) {
        Local local = localRepository.findLocalByLocalID(localID);
        Timetable timetable = timetableRepository.findTimetableByTimetableID(timetableID);
        bookingRepository.save(new Booking(timetable, local));
    }

    @Override
    public void deleteBook(Long bookingID) {
        bookingRepository.deleteById(bookingID);
    }

    @Override
    public List<BookingDTO> getBookingByLocalID(Long localID) {
        List<Booking> bookings = bookingRepository.findBookingsByLocal(localRepository.findLocalByLocalID(localID));
        return Mapper.mapListBookingToListBookingDto(bookings);
    }
}
