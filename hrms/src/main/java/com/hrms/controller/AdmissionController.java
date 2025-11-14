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

import com.hrms.dto.req.AdmissionReqDto;
import com.hrms.dto.res.AdmissionResDto;
import com.hrms.dto.res.ApiResponse;
import com.hrms.dto.res.DoctorResDto;
import com.hrms.service.AdmisssionDao;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/admissions")
@CrossOrigin
@Slf4j
public class AdmissionController {
	
	@Autowired
	private AdmisssionDao admisssionDao;

	@PostMapping
	public ResponseEntity<AdmissionResDto> admitPatient(@Valid @RequestBody AdmissionReqDto dto){
		 log.info("Request received to admit patient: {}", dto);
		 AdmissionResDto admissionResDto = admisssionDao.admitPatient(dto);
		 log.info("Patient admited  successfully with admission ID: ", admissionResDto.getId());
		return ResponseEntity.ok(admissionResDto);
	}
	
	@GetMapping
	public ResponseEntity<List<AdmissionResDto>> getAllAdmission(){
		log.info("Fetching all admission ...");
		 List<AdmissionResDto> listAll = admisssionDao.listAllAdmission();
		return ResponseEntity.ok(listAll);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAdmissionById(@PathVariable Long id){
		log.info("Fetching admission with ID: {}", id);
		AdmissionResDto admissionResDto = admisssionDao.getAdmissionById(id);
		return ResponseEntity.ok(admissionResDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> dischargePatient(@PathVariable Long id){
		log.info("Deleting doctor with ID: {}", id);
		admisssionDao.dischargePatient(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Patient discharge successfully" + id));
	}
}
