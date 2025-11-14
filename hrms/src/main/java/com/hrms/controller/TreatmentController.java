package com.hrms.controller;

import java.util.List;

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

import com.hrms.dto.req.TreatmentReqDto;
import com.hrms.dto.res.ApiResponse;
import com.hrms.dto.res.TreatmentResDto;
import com.hrms.service.TreatmentDao;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/api/treatments")
@Slf4j
public class TreatmentController {
	
	@Autowired
	private TreatmentDao treatmentDao;
	
	@PostMapping
	public ResponseEntity<ApiResponse> createTreatment(@Valid @RequestBody TreatmentReqDto dto){
		log.info("Request received to create treatment for patientId: {}", dto.getPatient_id());
		TreatmentResDto treatment = treatmentDao.createTreatment(dto);
        return ResponseEntity
        		.ok(new ApiResponse("Treatment created successfully ID : " + treatment.getId() + " Name of Patient : " +  treatment.getPatientFirstName() + " Name of Doctor : " +  treatment.getDoctorFirstName()));
	}
	
	@GetMapping
	public ResponseEntity<List<TreatmentResDto>> getAllTreatments(){
		log.info("Fetching all treatments");
        return ResponseEntity.ok(treatmentDao.getAllTreatments());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTreatmentById(@PathVariable Long id){
		log.info("Fetching treatment with ID: {}", id);
        return ResponseEntity.ok(treatmentDao.getTreatmentById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateTreatment(@PathVariable Long id, @Valid @RequestBody TreatmentReqDto dto){
		log.info("Updating treatment. ID: {}, Updated Fields: {}", id, dto);
		TreatmentResDto treatment = treatmentDao.updateTreatment(id,dto);
        return ResponseEntity
        		.ok(new ApiResponse("Treatment created successfully ID : " + treatment.getId() + " Name of Patient : " +  treatment.getPatientFirstName() + " Name of Doctor : " +  treatment.getDoctorFirstName()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTreatmentById(@PathVariable Long id){
		log.info("Deleting treatment with ID: {}", id);
        treatmentDao.deleteTreatment(id);
        return ResponseEntity.ok(new ApiResponse("Treatment deleted successfully " + id));
	}
}
