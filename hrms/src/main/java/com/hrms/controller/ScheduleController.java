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
	
	@GetMapping("/list")
	public ResponseEntity<List<ScheduleResDto>> getAllSchedules(){
		log.info("Fetching all resources ...");
		 
		 List<ScheduleResDto> allSchedules = scheduleDao.getAllSchedules();
		 
		return ResponseEntity.ok(allSchedules);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<List<ScheduleResDto>> getSchedulesByDoctor(@PathVariable Long id){
		log.info("Fetching schedule with doctor ID: {}", id);
		
		List<ScheduleResDto> schedulesByDoctor = scheduleDao.getSchedulesByDoctor(id);
		
		return ResponseEntity.ok(schedulesByDoctor);
	}
	
	@PostMapping("/generate")
	public ResponseEntity<String> generateNextMonth() {
		scheduleDao.generateNextMonthSchedule();
		return ResponseEntity.ok("Schedule generation triggered for next month");
	}
}
