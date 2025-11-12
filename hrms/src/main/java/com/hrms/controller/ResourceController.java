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

import com.hrms.dto.res.ResourceResDto;
import com.hrms.service.ResourceDao;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/resources")
public class ResourceController {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@PostMapping
	public ResponseEntity<?> registerResource(@Valid @RequestBody ResourceResDto dto){
		return null;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllResource(){
		return null;
	}
	
	@GetMapping("{/resId}")
	public ResponseEntity<?> getResourceById(@Valid Long resId){
		return null;
	}
	
	@PutMapping("{/resId}")
	public ResponseEntity<?> updateResource(@Valid Long resId, @Valid @RequestBody ResourceResDto dto){
		return null;
	}
	
	@DeleteMapping("{/resId}")
	public ResponseEntity<?> deleteResourceById(@Valid Long resId){
		return null;
	}
}
