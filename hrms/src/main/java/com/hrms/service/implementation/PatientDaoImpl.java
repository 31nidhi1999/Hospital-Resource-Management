package com.hrms.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ApiException;
import com.hrms.custom_exceptions.ResourceNotFoundException;
import com.hrms.dto.req.PatientReqDto;
import com.hrms.dto.res.DoctorResDto;
import com.hrms.dto.res.PatientResDto;
import com.hrms.entity.Doctor;
import com.hrms.entity.Patient;
import com.hrms.repository.PatientRepo;
import com.hrms.service.PatientDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PatientDaoImpl implements PatientDao {
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<PatientResDto> listAll() {
		log.info("Fetching all patients...");

	    List<PatientResDto> allPatients = patientRepo.findAll()
	            .stream()
	            .map(p -> modelMapper.map(p, PatientResDto.class))
	            .collect(Collectors.toList());

	    log.debug("Total patients found: ", allPatients.size());

	    if (allPatients.isEmpty()) {
	    	log.warn("No patients found in the database!");
	    } else {
	    	log.info("Successfully fetched patient list.", allPatients.size());
	    }

	    return allPatients;
	}

	@Override
	public PatientResDto registerPatient(PatientReqDto dto) {
		log.info("Creating new patient with name: ", dto.getEmail());

		Patient patient = modelMapper.map(dto, Patient.class);
		patient.setRole(dto.getRole());
		
		if(patientRepo.existsByEmail(dto.getEmail())) {
			throw new ApiException("user alrady exisit");
		}
		
		patient.setPassword(encoder.encode(dto.getPassword()));
		Patient savedPatient = patientRepo.save(patient);
		log.debug("Patient saved with ID: ", savedPatient.getId());
		
		PatientResDto patientResDto = modelMapper.map(savedPatient, PatientResDto.class);
		
		log.info("Patient created successfully: ID = , Email = ", 
				patientResDto.getId(), patientResDto.getEmail());
		
		return patientResDto;
	}

	@Override
	public PatientResDto updatePatient(Long patId, PatientReqDto dto) {
		log.info("Updating patient with ID: ", patId);
		
		Patient patient = patientRepo.findById(patId)
	    		.orElseThrow(()-> new ResourceNotFoundException("Patient not found by id" + patId));
		 
		modelMapper.map(dto, patient);
		patient.setPassword(encoder.encode(dto.getPassword()));
		log.debug("Mapped PatientReqDto to existing Patient entity for ID: ", patId);
		
		Patient savedPatient = patientRepo.save(patient);
		log.debug("Patient updated entity saved successfully with ID: ", savedPatient.getId());
		
		PatientResDto patientResDto = modelMapper.map(savedPatient, PatientResDto.class);
		 log.info("Patient updated successfully: ID = , email = ", 
				 patientResDto.getId(), patientResDto.getEmail());

		return patientResDto;
	}

	@Override
	public void deletePatient(Long id) {
		log.info("Request received to delete (soft delete) patient with ID: {}", id);

	    Patient patient = patientRepo.findById(id)
	    		.orElseThrow(()-> new ResourceNotFoundException("Patient not found by id : " + id));

	    log.debug("Patient found for deletion. ID: {}, Email: {}", id, patient.getEmail());

	    patient.setActive(false);
	    patientRepo.save(patient);

	    log.info("Patient with ID: {} has been soft deleted successfully.", id);
	}

	@Override
	public PatientResDto getPatientById(Long id) {
		log.info("Fetching patient with ID: {}", id);

		Patient patient = patientRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Patient not found for id " + id));

		log.debug("Patient found: ID = {}, Doctor Email = {}", id, patient.getEmail());

		return modelMapper.map(patient, PatientResDto.class);
	}

}
