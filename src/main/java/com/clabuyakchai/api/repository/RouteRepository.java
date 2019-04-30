package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findRoutesByFromAndTo(String from, String to);

    Route findTopByOrderByRouteIDDesc();
}
