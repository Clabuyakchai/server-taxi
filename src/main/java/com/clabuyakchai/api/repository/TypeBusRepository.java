package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.TypeBus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeBusRepository extends JpaRepository<TypeBus, Long> {
}
