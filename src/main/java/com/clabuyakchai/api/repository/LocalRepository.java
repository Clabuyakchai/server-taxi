package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    Local findLocalByPhone(String phone);
    Boolean existsLocalByPhone(String phone);
    Local findLocalByLocalID(Long localID);
    @Query(value = " select * from local l " +
            " join booking b on l.localID = b.localID " +
            " where b.timetableID = :timetableID", nativeQuery = true)
    List<Local> findLocalByTimetable(@Param("timetableID") Long timetableID);
}
