package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.res.PatientResDto;
import com.hrms.service.PatientDao;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/patients")
public class PatientController {
	
	@Autowired
	private PatientDao patientDao;
	
	@PostMapping
	public ResponseEntity<?> registerPatient(@Valid @RequestBody PatientResDto dto){
		return null;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllPatient(){
		return null;
	}
	
	@GetMapping("{/patId}")
	public ResponseEntity<?> getPatientById(@Valid Long patId){
		return null;
	}
	
	@PutMapping("{/patId}")
	public ResponseEntity<?> updatePatient(@Valid Long patId, @Valid @RequestBody PatientResDto dto){
		return null;
	}
	
	@DeleteMapping("{/patId}")
	public ResponseEntity<?> deletePatientById(@Valid Long patId){
		return null;
	}
}
