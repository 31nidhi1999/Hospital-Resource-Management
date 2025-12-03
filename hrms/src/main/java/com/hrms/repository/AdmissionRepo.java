package com.hrms.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Admission;


@Repository
public interface AdmissionRepo extends JpaRepository<Admission, Long>{
	
	List<Admission> findByPatient_Id(Long id);
	List<Admission> findByDoctor_Id(Long id);
	Boolean existsByPatient_IdAndDischargeDateIsNull(Long patientId);
	List<Admission> findAllByIsActiveTrue();
	List<Admission>  findAllByIsActiveFalse();
	
}
