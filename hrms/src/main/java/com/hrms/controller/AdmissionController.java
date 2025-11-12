package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.hrms.dto.req.AdmissionReqDto;
import com.hrms.service.AdmisssionDao;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admissions")
@CrossOrigin
public class AdmissionController {
	
	@Autowired
	private AdmisssionDao admisssionDao;

	@PostMapping
	public ResponseEntity<?> admitPatient(@Valid @RequestBody AdmissionReqDto dto){
		return null;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllAdmission(){
		return null;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAdmissionById(@PathVariable Long id){
		return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAdmission(@PathVariable Long id, @Valid @RequestBody AdmissionReqDto dto){
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> dischargePatient(@PathVariable Long id){
		return null;
	}
}
