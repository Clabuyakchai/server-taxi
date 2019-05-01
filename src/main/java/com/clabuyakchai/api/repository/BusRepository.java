package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus findBusByBusID(Long busID);
}
