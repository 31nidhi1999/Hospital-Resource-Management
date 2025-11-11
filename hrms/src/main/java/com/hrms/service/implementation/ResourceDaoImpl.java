package com.hrms.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ApiException;
import com.hrms.custom_exceptions.ResourceNotFoundException;
import com.hrms.dto.req.ResourceReqDto;
import com.hrms.dto.res.PatientResDto;
import com.hrms.dto.res.ResourceResDto;
import com.hrms.entity.Admin;
import com.hrms.entity.Patient;
import com.hrms.entity.Resource;
import com.hrms.repository.AdminRepo;
import com.hrms.repository.ResourceRepo;
import com.hrms.service.ResourceDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ResourceDaoImpl implements ResourceDao {
	
	@Autowired
	private ResourceRepo resourceRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResourceResDto registerResource(Long adminId,ResourceReqDto dto) {
		log.info("Registering new resource: ", dto.getResourceName());
		
		Admin admin = adminRepo.findById(adminId)
		.orElseThrow(()-> new ResourceNotFoundException("Admin not found for id " +  adminId));
		
		
		Resource resource = modelMapper.map(dto, Resource.class);
		admin.getResources().add(resource);
		
		Resource savedResource = resourceRepo.save(resource);
		log.info("Resource registered successfully with ID: ", savedResource.getId());
		
		return modelMapper.map(savedResource, ResourceResDto.class);
	}

	@Override
	public ResourceResDto updateResource(Long resId, ResourceReqDto dto) {
		log.info("Updating resource with ID: ", resId);
		
		Resource resource = resourceRepo.findById(resId)
										.orElseThrow(()-> new ResourceNotFoundException("Resource not found for id " + resId));
		
		Resource resource2 = modelMapper.map(dto, Resource.class);
		Resource upatedResource = resourceRepo.save(resource2);
		log.info("Resource updated successfully with ID: ", upatedResource.getId());
		
		return modelMapper.map(upatedResource, ResourceResDto.class);
	}

	@Override
	public List<ResourceResDto> listAll() {
		log.info("Fetching all resource...");

	    List<ResourceResDto> allResources = resourceRepo.findAll()
	            .stream()
	            .map(r -> modelMapper.map(r, ResourceResDto.class))
	            .collect(Collectors.toList());

	    log.debug("Total patients found: ", allResources.size());

	    if (allResources.isEmpty()) {
	    	log.warn("No patients found in the database!");
	    } else {
	    	log.info("Successfully fetched patient list.", allResources.size());
	    }

	    return allResources;
	}

	@Override
	public void deleteResource(Long id) {
		log.info("Request received to delete (soft delete) resource with ID: ", id);

	    Resource resource = resourceRepo.findById(id)
	    		.orElseThrow(()-> new ResourceNotFoundException("Resource not found by id" + id));

	    log.debug("Resource found for deletion. ID: , Resource Name: ", id, resource.getResourceName());

	    resourceRepo.save(resource);

	    log.info("Resource with ID:  has been soft deleted successfully.", id);
		
	}

	@Override
	public ResourceResDto getResourceById(Long id) {
		log.info("Fetching resource with ID: {}", id);
		
		Resource resource = resourceRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Resource not found for id " + id));
		
		log.debug("Resource found: ID = ,Resource Name = ", id, resource.getResourceName());
		
		return modelMapper.map(resource, ResourceResDto.class);
	}

	@Override
	public void updateAvailableQuantity(Long resId, int newAvailableQuantity) {
		 log.info("Updating available quantity for resource ID: ", resId);
		 
		 Resource resource = resourceRepo.findById(resId)
		 .orElseThrow(()-> new ResourceNotFoundException("Resource not found for id " + resId));
		 
		 if(newAvailableQuantity > resource.getTotalQuantity()) {
			 throw new ApiException("Available quantity cannot exceed total quantity");
		 }
		 
		 resource.setAvailableQuantity(newAvailableQuantity);
		 resourceRepo.save(resource);
		 
		 log.info("Available quantity updated successfully for resource ID: {}", resId);
	}

	@Override
	public void updateTotalQuantity(Long resId, int newTotalQuantity) {
		log.info("Updating total quantity for resource ID: ", resId);
		
		Resource resource = resourceRepo.findById(resId)
				.orElseThrow(()-> new ResourceNotFoundException("Resource not found for ID: " + resId));
		
		 if (newTotalQuantity < resource.getAvailableQuantity()) {
	            throw new ApiException("Total quantity cannot be less than available quantity");
	        }
		 
		 resource.setAvailableQuantity(newTotalQuantity);
		 resourceRepo.save(resource);
		 log.info("Total quantity updated successfully for resource ID: ", resId);
	}
}
