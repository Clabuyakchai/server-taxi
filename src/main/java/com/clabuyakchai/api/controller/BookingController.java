package com.clabuyakchai.api.controller;

import com.clabuyakchai.api.dto.BookingDTO;
import com.clabuyakchai.api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/book")
@RestController
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(value = "/add")
    public void addBook(@RequestParam Long localID, @RequestParam Long timetableID){
        bookingService.addBook(localID, timetableID);
    }

    @GetMapping(value = "/delete")
    public void deleteBook(@RequestParam Long bookingID){
        bookingService.deleteBook(bookingID);
    }

    @GetMapping(value = "/booklocal")
    public List<BookingDTO> getBookingByLocalID(@RequestParam  Long localID){
        return bookingService.getBookingByLocalID(localID);
    }
}
