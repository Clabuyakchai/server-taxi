package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Set;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findTimetableByDatetimeContainingAndRoute_FromAndRoute_To(String datetime, String from, String to);
    Timetable findTimetableByTimetableID(Long timetableID);

    @Query(value = "select * from timetable t " +
            "join route r on t.routeid=r.routeid " +
            "join station_route sr on r.routeid = sr.routeid " +
            "join station s on s.stationid = sr.stationid " +
            "join bus_route bs on bs.routeid = r.routeid " +
            "join bus b on b.busid = bs.busid " +
            "join staff st on st.busid = b.busid " +
            " where t.datetime like(:day) and st.name=:name", nativeQuery = true)
    Set<Timetable> findTimetableByStaff(@Param("day") String day, @Param("name") String name);

}
