package com.hrms.service.implementation;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ResourceNotFoundException;
import com.hrms.dto.req.DoctorReqDto;
import com.hrms.dto.res.DoctorResDto;
import com.hrms.entity.Doctor;
import com.hrms.repository.DoctorRepo;
import com.hrms.service.DoctorDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class DoctorDaoImpl implements DoctorDao {
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DoctorResDto registerDoctor(DoctorReqDto dto) {
		log.info("Creating new doctor with name: ", dto.getEmail());

		Doctor doctor = modelMapper.map(dto, Doctor.class);
		doctor.setRole(dto.getRole());
		
		Doctor savedDoctor = doctorRepo.save(doctor);
		log.debug("Doctor saved with ID: ", savedDoctor.getId());
		
		DoctorResDto doctorResDto = modelMapper.map(savedDoctor, DoctorResDto.class);
		
		log.info("Doctor created successfully: ID = , Email = ", 
                doctorResDto.getId(), doctorResDto.getEmail());
		
		return doctorResDto;
	}

	@Override
	public DoctorResDto updateDoctor(Long docId,DoctorReqDto dto) {
		log.info("Updating doctor with ID: ", docId);
		
		Doctor doctor = doctorRepo.findById(docId)
	            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found for id : " + docId));
		 
		modelMapper.map(dto, doctor);
		log.debug("Mapped DoctorReqDto to Doctor entity for ID: ", docId);
		
		Doctor savedDoctor = doctorRepo.save(doctor);
		log.debug("Doctor entity saved successfully with ID: ", savedDoctor.getId());
		
		DoctorResDto doctorResDto = modelMapper.map(savedDoctor, DoctorResDto.class);
		 log.info("Doctor updated successfully: ID = , email = ", 
	             doctorResDto.getId(), doctorResDto.getEmail());

		return doctorResDto;
	}

	@Override
	public List<DoctorResDto> listAll() {
		log.info("Fetching all doctors...");

	    List<DoctorResDto> allDoctors = doctorRepo.findAll()
	            .stream()
	            .map(d -> modelMapper.map(d, DoctorResDto.class))
	            .collect(Collectors.toList());

	    log.debug("Total doctors found: ", allDoctors.size());

	    if (allDoctors.isEmpty()) {
	    	log.warn("No doctors found in the database!");
	    } else {
	    	log.info("Successfully fetched doctors list.", allDoctors.size());
	    }

	    return allDoctors;
	}

	@Override
	public void deleteDoctor(Long id) {
	    log.info("Request received to delete (soft delete) doctor with ID: {}", id);

	    Doctor doctor = doctorRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found for id : " + id));

	    log.debug("Doctor found for deletion. ID: {}, Email: {}", id, doctor.getEmail());

	    doctor.setActive(false);
	    doctorRepo.save(doctor);

	    log.info("Doctor with ID:  has been soft deleted successfully.", id);
	}


	@Override
	public DoctorResDto getDoctorById(Long id) {
		log.info("Fetching doctor with ID: ", id);

		Doctor doctor = doctorRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Doctor not found for id " + id));

		log.debug("Doctor found: ID = , Doctor Email = ", id, doctor.getEmail());

		return modelMapper.map(doctor, DoctorResDto.class);
	}
}
