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

import com.hrms.dto.req.TreatmentReqDto;
import com.hrms.service.TreatmentDao;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/treatments")
public class TreatmentController {
	
	@Autowired
	private TreatmentDao treatmentDao;
	
	@PostMapping
	public ResponseEntity<?> createTreatment(@Valid @RequestBody TreatmentReqDto dto){
		return null;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllTreatments(){
		return null;
	}
	
	@GetMapping("{/treId}")
	public ResponseEntity<?> getTreatmentById(@Valid Long treId){
		return null;
	}
	
	@PutMapping("{/treId}")
	public ResponseEntity<?> updateTreatment(@Valid Long treId, @Valid @RequestBody TreatmentReqDto dto){
		return null;
	}
	
	@DeleteMapping("{/treId}")
	public ResponseEntity<?> deleteTreatmentById(@Valid Long treId){
		return null;
	}
}
