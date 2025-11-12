package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.req.PatientReqDto;
import com.hrms.dto.res.ApiResponse;
import com.hrms.dto.res.DoctorResDto;
import com.hrms.dto.res.PatientResDto;
import com.hrms.service.PatientDao;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/api/patients")
@Slf4j
public class PatientController {
	
	@Autowired
	private PatientDao patientDao;
	
	@PostMapping
	public ResponseEntity<PatientResDto> registerPatient(@Valid @RequestBody PatientReqDto dto){
		 log.info("Request received to create patient: {}", dto);
		 
		 PatientResDto registerDoctor = patientDao.registerPatient(dto);
		 
		 log.info("Patient created successfully with ID: ", registerDoctor.getId());
		 
		return ResponseEntity.ok(registerDoctor);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllPatient(){
		 log.info("Fetching all doctors ...");
		 
		 List<PatientResDto> listAll = patientDao.listAll();
		 
		return ResponseEntity.ok(listAll);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PatientResDto> getPatientById(@PathVariable Long id){
		log.info("Fetching patient with ID: {}", id);
		
		PatientResDto patientResDto = patientDao.getPatientById(id);
		
		return ResponseEntity.ok(patientResDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PatientResDto> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientReqDto dto){
		log.info("Updating patient with ID: {}", id);
		
		PatientResDto patientResDto = patientDao.updatePatient(id, dto);
		
		return ResponseEntity.ok(patientResDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePatientById(@PathVariable Long id){
		log.info("Deleting patient with ID: {}", id);
		
		patientDao.deletePatient(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse("Patient deleted successfully" + id));
	}
}
