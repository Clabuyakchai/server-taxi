package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.Booking;
import com.clabuyakchai.api.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingsByLocal(Local local);
}
