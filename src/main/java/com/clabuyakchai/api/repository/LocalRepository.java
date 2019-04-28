package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
//    @Query("SELECT '*' FROM local")
//    List<Local> getAllLocal();
}
