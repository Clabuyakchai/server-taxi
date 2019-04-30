package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.StationRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRouteRepository extends JpaRepository<StationRoute, Long> {
}
