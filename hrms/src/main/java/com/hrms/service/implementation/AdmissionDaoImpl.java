package com.hrms.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ResourceNotFoundException;
import com.hrms.dto.req.AdmissionReqDto;
import com.hrms.dto.res.AdmissionResDto;
import com.hrms.entity.Admission;
import com.hrms.repository.AdmissionRepo;
import com.hrms.service.AdmisssionDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AdmissionDaoImpl implements AdmisssionDao {
	
	@Autowired
	private AdmissionRepo admissionRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<AdmissionResDto> getAllAdmission() {
		log.info("Fetching all admissions...");
		
		List<AdmissionResDto> allAdmission = admissionRepo.findAll()
					 .stream().map(a -> modelMapper.map(a, AdmissionResDto.class))
					 .collect(Collectors.toList());
		
		log.debug("Total admissions found: ", allAdmission.size());
		
		if (allAdmission.isEmpty()) {
	    	log.warn("No Admission found in the database!");
	    } else {
	    	log.info("Successfully fetched Admission list.", allAdmission.size());
	    }
		
		return allAdmission;
	}

	@Override
	public AdmissionResDto getAdmissionById(long id) {
		log.info("Fetching admission with ID: ", id);
		
		Admission admission = admissionRepo.findById(id)
									 .orElseThrow(()-> new ResourceNotFoundException("Resource not found with id " + id));
		
		log.debug("Admission found: ID = , Patient Email = ", admission.getId(), admission.getPatient().getEmail());
		
		return modelMapper.map(admission, AdmissionResDto.class);
	}

	@Override
	public AdmissionResDto registerAdmission(AdmissionReqDto dto) {
		log.info("Creating new admission for patient ID: {}", dto.getPatientId());
		
		return null;
	}

	@Override
	public AdmissionResDto updateAdmission(Long id,AdmissionReqDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAdmission(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
