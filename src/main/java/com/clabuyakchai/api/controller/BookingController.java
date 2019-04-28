package com.clabuyakchai.api.controller;

import com.clabuyakchai.api.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    BookingRepository repository;
}
