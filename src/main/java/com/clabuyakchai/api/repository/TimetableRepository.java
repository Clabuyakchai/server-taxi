package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findTimetableByDatetimeContainingAndRoute_FromAndRoute_To(String datetime, String from, String to);
}
