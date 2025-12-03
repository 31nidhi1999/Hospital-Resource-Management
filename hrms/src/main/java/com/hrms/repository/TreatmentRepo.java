package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Treatment;

@Repository
public interface TreatmentRepo extends JpaRepository<Treatment, Long> {
	List<Treatment> findByPatient_Id(Long id);
	List<Treatment> findByDoctor_Id(Long id);
}
