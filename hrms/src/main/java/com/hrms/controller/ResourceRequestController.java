package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.req.ResourceRequestReqDto;
import com.hrms.dto.res.ApiResponse;
import com.hrms.dto.res.ResourceRequestResDto;
import com.hrms.service.ResourceRequestDao;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin
public class ResourceRequestController {
	@Autowired
	private ResourceRequestDao requestDao;
	
	@PostMapping("/create")
    public ResponseEntity<ApiResponse> createRequest(@Valid @RequestBody ResourceRequestReqDto dto) {
		
		ResourceRequestResDto res = requestDao.createRequest(dto);
        
		return ResponseEntity.ok(new ApiResponse("Resource request created successfully "  + res.getDoctorFirstName()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResourceRequestResDto> getRequestById(@PathVariable Long id) {

        ResourceRequestResDto res = requestDao.getRequestById(id);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResourceRequestResDto>> getAllRequests() {

        List<ResourceRequestResDto> list = requestDao.getAllRequests();

        return ResponseEntity.ok(list);
    }

    @PutMapping("/id/{id}/approve")
    public ResponseEntity<ApiResponse> approveRequest(@PathVariable Long id) {

        ResourceRequestResDto res = requestDao.approve(id);

        return ResponseEntity.ok(new ApiResponse("Resource request approved successfully"));
    }

    @PutMapping("/id/{id}/reject")
    public ResponseEntity<ApiResponse> rejectRequest(@PathVariable Long id) {
    	
    	ResourceRequestResDto res = requestDao.reject(id);
       
        return ResponseEntity.ok(new ApiResponse("Resource request rejected successfully"));
    }
}
