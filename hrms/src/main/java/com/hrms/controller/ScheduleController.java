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

import com.hrms.dto.req.ScheduleReqDto;
import com.hrms.dto.res.ApiResponse;
import com.hrms.dto.res.ResourceResDto;
import com.hrms.dto.res.ScheduleResDto;
import com.hrms.service.ScheduleDao;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/api/schedules")
@Slf4j
public class ScheduleController {
	
	@Autowired
	private ScheduleDao scheduleDao;
	
	@PostMapping
	public ResponseEntity<?> createSchedule(@Valid @RequestBody ScheduleReqDto dto){
		log.info("Request received to create doctor schedule: {}", dto);
		 
		 ScheduleResDto scheduleResDto = scheduleDao.createSchedule( dto);
		 
		 log.info("Patient created successfully with ID: ", scheduleResDto.getId());
		 
		return ResponseEntity.ok(scheduleResDto);
	}
	
	@GetMapping
	public ResponseEntity<List<ScheduleResDto>> getAllResource(){
		log.info("Fetching all resources ...");
		 
		 List<ScheduleResDto> allSchedules = scheduleDao.getAllSchedules();
		 
		return ResponseEntity.ok(allSchedules);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<ScheduleResDto>> getResourceById(@PathVariable Long id){
		log.info("Fetching schedule with doctor ID: {}", id);
		
		List<ScheduleResDto> schedulesByDoctor = scheduleDao.getSchedulesByDoctor(id);
		
		return ResponseEntity.ok(schedulesByDoctor);
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<?> updateResource(@PathVariable Long id, @Valid @RequestBody ScheduleReqDto dto){
//		log.info("Updating schedule with ID: {}", id);
//		
//		ResourceResDto resourceResDto = resourceDao.updateResource(id, dto);
//		
//		return ResponseEntity.ok(resourceResDto);
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteResourceById(@PathVariable Long id){
		log.info("Deleting respurce with ID: {}", id);
		
		scheduleDao.deleteSchedule(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse("Resource deleted successfully" + id));
	}
}
