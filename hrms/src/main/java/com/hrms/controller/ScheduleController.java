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

import com.hrms.dto.req.ScheduleReqDto;
import com.hrms.service.ScheduleDao;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/schedules")
public class ScheduleController {
	
	@Autowired
	private ScheduleDao scheduleDao;
	
	@PostMapping
	public ResponseEntity<?> registerResource(@Valid @RequestBody ScheduleReqDto dto){
		return null;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllResource(){
		return null;
	}
	
	@GetMapping("{/secId}")
	public ResponseEntity<?> getResourceById(@Valid Long secId){
		return null;
	}
	
	@PutMapping("{/secId}")
	public ResponseEntity<?> updateResource(@Valid Long secId, @Valid @RequestBody ScheduleReqDto dto){
		return null;
	}
	
	@DeleteMapping("{/secId}")
	public ResponseEntity<?> deleteResourceById(@Valid Long secId){
		return null;
	}
}
