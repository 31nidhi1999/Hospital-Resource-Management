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

import com.hrms.dto.req.UserReqDto;
import com.hrms.dto.res.UserResDto;
import com.hrms.dto.res.ApiResponse;
import com.hrms.service.AdminDao;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/api/admins")
@Slf4j
public class AdminController {
	@Autowired
	private AdminDao adminDao;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerAdmin(@Valid @RequestBody UserReqDto dto){
		 log.info("Request received to create admin: {}", dto);
		 UserResDto registerAdmin = adminDao.registerAdmin(dto);
		 log.info("Admin created successfully with ID: ", registerAdmin.getId());
		return ResponseEntity
				.ok(new ApiResponse("Admin created successfully: ID : " + registerAdmin.getId() +" Email : " +  registerAdmin.getEmail()));
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<UserResDto>> getAllAdmin(){
		 log.info("Fetching all Admin ...");
		 List<UserResDto> listAll = adminDao.listAll();
		return ResponseEntity.ok(listAll);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<UserResDto> getAdminById(@PathVariable Long id){
		log.info("Fetching Admin with ID: {}", id);
		UserResDto adminResDto = adminDao.getAdminById(id);
		return ResponseEntity.ok(adminResDto);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateAdmin(@PathVariable Long id, @Valid @RequestBody UserReqDto dto){
		log.info("Updating Admin with ID: {}", id);
		UserResDto adminResDto = adminDao.updateAdmin(id, dto);
		return ResponseEntity
				.ok(new ApiResponse("Admin deatil updated successfully: ID : " + adminResDto.getId() +" Email : " +  adminResDto.getEmail()));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<ApiResponse> deleteAdminById(@PathVariable Long id){
		log.info("Deleting admin with ID: {}", id);
		adminDao.deleteAdmin(id);
		return ResponseEntity.ok(new ApiResponse("Admin deleted successfully " + id));
	}
}
