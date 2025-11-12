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

import com.hrms.dto.req.DoctorReqDto;
import com.hrms.service.DoctorDao;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/doctors")
public class DoctorContoller {
	
	@Autowired
	private DoctorDao doctorDao;
	
	@PostMapping
	public ResponseEntity<?> registerDoctor(@Valid @RequestBody DoctorReqDto dto){
		return null;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllDoctor(){
		return null;
	}
	
	@GetMapping("{/docId}")
	public ResponseEntity<?> getDoctorById(@Valid Long docId){
		return null;
	}
	
	@PutMapping("{/docId}")
	public ResponseEntity<?> updateDoctor(@Valid Long docId, @Valid @RequestBody DoctorReqDto dto){
		return null;
	}
	
	@DeleteMapping("{/docId}")
	public ResponseEntity<?> deleteDoctorById(@Valid Long docId){
		return null;
	}
}
