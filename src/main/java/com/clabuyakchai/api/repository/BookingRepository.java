package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.Booking;
import com.clabuyakchai.api.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingsByLocal(Local local);

    @Query(value = " select * from booking b " +
            " join timetable t on t.timetableID = b.timetableID" +
            " join local l on l.localID = b.localID where l.localID = :id and t.datetime > :date", nativeQuery = true)
    List<Booking> getBookingByLocalIDAndDatetime(@Param("id") Long id, @Param("date") String date);
}
