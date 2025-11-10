package com.hrms.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ResourceNotFoundException;
import com.hrms.dto.req.ResourceReqDto;
import com.hrms.dto.res.PatientResDto;
import com.hrms.dto.res.ResourceResDto;
import com.hrms.entity.Patient;
import com.hrms.entity.Resource;
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
	private ModelMapper modelMapper;

	@Override
	public ResourceResDto registerResource(ResourceReqDto dto) {
		log.info("Registering new resource: {}", dto.getResourceName());
		Resource resource = modelMapper.map(dto,Resource.class);
		
		return null;
	}

	@Override
	public ResourceResDto updateResource(Long resId, ResourceReqDto dto) {
		Resource resource = resourceRepo.findById(resId)
										.orElseThrow(()-> new ResourceNotFoundException("Resource not found for id " + resId));
		
		
		
		return null;
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
		log.info("Request received to delete (soft delete) resource with ID: {}", id);

	    Resource resource = resourceRepo.findById(id)
	    		.orElseThrow(()-> new ResourceNotFoundException("Resource not found by id" + id));

	    log.debug("Resource found for deletion. ID: {}, Resource Name: {}", id, resource.getResourceName());

	    resourceRepo.save(resource);

	    log.info("Resource with ID: {} has been soft deleted successfully.", id);
		
	}

	@Override
	public ResourceResDto getResourceById(Long id) {
		Resource resource = resourceRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Resource not found for id " + id));
		return null;
	}
}
