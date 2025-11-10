package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Doctor;
import java.util.List;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	Doctor findByEmail(String email);
}
