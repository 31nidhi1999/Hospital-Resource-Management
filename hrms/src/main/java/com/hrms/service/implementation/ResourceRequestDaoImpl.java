package com.hrms.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ApiException;
import com.hrms.custom_exceptions.ResourceNotFoundException;
import com.hrms.dto.req.ResourceRequestReqDto;
import com.hrms.dto.res.ResourceRequestResDto;
import com.hrms.dto.res.ResourceResDto;
import com.hrms.dto.res.TreatmentResDto;
import com.hrms.entity.Doctor;
import com.hrms.entity.Patient;
import com.hrms.entity.RequestStatus;
import com.hrms.entity.Resource;
import com.hrms.entity.ResourceRequest;
import com.hrms.entity.Treatment;
import com.hrms.repository.DoctorRepo;
import com.hrms.repository.PatientRepo;
import com.hrms.repository.ResourceRepo;
import com.hrms.repository.ResourceRequestRepo;
import com.hrms.service.ResourceRequestDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ResourceRequestDaoImpl implements ResourceRequestDao {
	
	@Autowired
	private ResourceRequestRepo requestRepo;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private ResourceRepo resourceRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResourceRequestResDto createRequest(ResourceRequestReqDto dto) {
		log.info("Creating new resource request for doctor ID: {}", dto.getDoctorId());
		
		Patient patient = patientRepo.findById(dto.getPatientId())
				.orElseThrow(()-> new ResourceNotFoundException("Patient not found for ID: " + dto.getPatientId()));
		
		Doctor doctor = doctorRepo.findById(dto.getDoctorId())
		.orElseThrow( ()-> new ResourceNotFoundException("Doctor not found for ID: " + dto.getDoctorId()));
		
		Resource resource = resourceRepo.findById(dto.getResourceId())
							.orElseThrow(()-> new ResourceNotFoundException("Resource not found for ID: " + dto.getResourceId()));	
		
		ResourceRequest request = modelMapper.map(dto, ResourceRequest.class);
		request.setDoctor(doctor);
		request.setPatient(patient);
		request.setResource(resource);
		
		ResourceRequest savedRequest = requestRepo.save(request);
		
		log.info("Resource request created successfully with ID: {}", savedRequest.getId());
		
		return modelMapper.map(resource, ResourceRequestResDto.class);
	}

	@Override
	public ResourceRequestResDto getRequestById(Long id) {
		log.info("Fetching ResourceRequest with ID: ", id);
		
		ResourceRequest request = requestRepo.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Treatment not found with ID: " + id));
		
		log.info("ResourceRequest fetched successfully with ID: ", request.getId());
		
		return modelMapper.map(request, ResourceRequestResDto.class);
	}

	@Override
	public List<ResourceRequestResDto> getAllRequests() {
		log.info("Fetching all resource requests");
		
        List<ResourceRequestResDto> allRequest = requestRepo.findAll()
                .stream()
                .map(r->modelMapper.map(r, ResourceRequestResDto.class))
                .collect(Collectors.toList());
        
        log.debug("Total Resource Request found: ", allRequest.size());

	    if (allRequest.isEmpty()) {
	    	log.warn("No  Resource Request found in the database!");
	    } else {
	    	log.info("Successfully fetched  Resource Request list.", allRequest.size());
	    }
		return allRequest;
	}

	@Override
	public ResourceRequestResDto updateRequestStatus(Long id, String status) {
		log.info("Uppdating new resource request for doctor ID: ", id);
		
		ResourceRequest request = requestRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Resource not found for ID: " + id));
		
		
		
		ResourceRequest savedRequest = requestRepo.save(request);
		
		log.info("Resource request created successfully with ID: {}", savedRequest.getId());
		
		return modelMapper.map(resource, ResourceRequestResDto.class);
	}

	@Override
	public void deleteRequest(Long id) {
		
        ResourceRequest request = requestRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Request not found with ID: " + id));
        requestRepo.delete(request);
        
        log.info("Resource Request deleted successfully with ID: {}", id);
		
	}
}
