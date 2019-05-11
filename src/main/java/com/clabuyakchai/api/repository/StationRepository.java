package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Station findStationByStationID(Long id);
    List<Station> findStationsByCity(String city);
    Station findStationByLatAndLng(String lat, String lng);
}
