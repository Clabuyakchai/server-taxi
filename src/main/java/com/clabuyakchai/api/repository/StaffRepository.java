package com.clabuyakchai.api.repository;

import com.clabuyakchai.api.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Boolean existsStaffByPhone(String phone);
    Staff findStaffByPhone(String phone);
    Staff findStaffByStaffID(Long staffID);
    Staff findStaffByName(String name);
}
