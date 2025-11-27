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

import com.hrms.dto.req.ResourceReqDto;
import com.hrms.dto.res.ApiResponse;
import com.hrms.dto.res.PatientResDto;
import com.hrms.dto.res.ResourceResDto;
import com.hrms.service.ResourceDao;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/api/resources")
@Slf4j
public class ResourceController {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerResource(@Valid @RequestBody ResourceReqDto dto){
		log.info("Request received to create patient: {}", dto);
		 
		 ResourceResDto resourceResDto = resourceDao.registerResource( dto);
		 
		 log.info("Patient created successfully with ID: ", resourceResDto.getId());
		 
		 return ResponseEntity
					.ok(new ApiResponse("Resource Register successfully, ID : " + resourceResDto.getId()));
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllResource(){
		log.info("Fetching all resources ...");
		 
		 List<ResourceResDto> listAll = resourceDao.listAll();
		 
		return ResponseEntity.ok(listAll);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<ResourceResDto> getResourceById(@PathVariable Long id){
		log.info("Fetching resource with ID: {}", id);
		
		ResourceResDto resourceResDto = resourceDao.getResourceById(id);
		
		return ResponseEntity.ok(resourceResDto);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateResource(@PathVariable Long id, @Valid @RequestBody ResourceReqDto dto){
		log.info("Updating patient with ID: {}", id);
		
		ResourceResDto resourceResDto = resourceDao.updateResource(id, dto);
		
		return ResponseEntity
				.ok(new ApiResponse("Resource Update successfully, ID : " + resourceResDto.getId()));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteResourceById(@PathVariable Long id){
		log.info("Deleting respurce with ID: {}", id);
		
		resourceDao.deleteResource(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse("Resource deleted successfully" + id));
	}
	
	
	@PutMapping("/{resId}/total/{newTotalQuantity}")
    public ResponseEntity<ApiResponse> updateTotalQuantity(
            @PathVariable Long resId,
            @PathVariable int newTotalQuantity) {

        log.info("Received request to update total quantity for resource ID: {}", resId);

        resourceDao.updateTotalQuantity(resId, newTotalQuantity);

        return ResponseEntity.ok((new ApiResponse("Total quantity updated successfully for resource ID: " + resId)));
    }
}
