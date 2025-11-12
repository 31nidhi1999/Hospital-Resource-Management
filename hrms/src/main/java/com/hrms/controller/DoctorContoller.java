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

import com.hrms.dto.req.DoctorReqDto;
import com.hrms.dto.res.ApiResponse;
import com.hrms.dto.res.DoctorResDto;
import com.hrms.service.DoctorDao;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/api/doctors")
@Slf4j
public class DoctorContoller {
	
	@Autowired
	private DoctorDao doctorDao;
	
	@PostMapping
	public ResponseEntity<DoctorResDto> registerDoctor(@PathVariable @RequestBody DoctorReqDto dto){
		 log.info("Request received to create doctor: {}", dto);
		 DoctorResDto registerDoctor = doctorDao.registerDoctor(dto);
		 log.info("Doctor created successfully with ID: ", registerDoctor.getId());
		return ResponseEntity.ok(registerDoctor);
	}
	
	@GetMapping
	public ResponseEntity<List<DoctorResDto>> getAllDoctor(){
		 log.info("Fetching all doctors ...");
		 List<DoctorResDto> listAll = doctorDao.listAll();
		return ResponseEntity.ok(listAll);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DoctorResDto> getDoctorById(@PathVariable Long id){
		log.info("Fetching doctor with ID: {}", id);
		DoctorResDto doctorResDto = doctorDao.getDoctorById(id);
		return ResponseEntity.ok(doctorResDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DoctorResDto> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorReqDto dto){
		log.info("Updating doctor with ID: {}", id);
		DoctorResDto doctorResDto = doctorDao.updateDoctor(id, dto);
		return ResponseEntity.ok(doctorResDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteDoctorById(@PathVariable Long id){
		log.info("Deleting doctor with ID: {}", id);
		doctorDao.deleteDoctor(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Doctor deleted successfully" + id));
	}
}
