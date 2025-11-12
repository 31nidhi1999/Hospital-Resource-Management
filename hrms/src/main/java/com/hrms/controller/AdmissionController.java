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
	
	@GetMapping("{/adId}")
	public ResponseEntity<?> getAdmissionById(@Valid Long adId){
		return null;
	}
	
	@PutMapping("{/adId}")
	public ResponseEntity<?> updateAdmission(@Valid Long adId, @Valid @RequestBody AdmissionReqDto dto){
		return null;
	}
	
	@DeleteMapping("{/patId}")
	public ResponseEntity<?> dischargePatient(@Valid Long patId){
		return null;
	}
}
