package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Patient;
import java.util.List;


@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
	Patient findByEmail(String email);

	boolean existsByEmail(String email);
}
