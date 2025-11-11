package com.hrms.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Admission;

@Repository
public interface AdmissionRepo extends JpaRepository<Admission, Long>{
	
	Boolean existsByPatient_IdAndDischargeDateIsNull(Long patientId);
}
