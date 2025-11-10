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
import com.hrms.entity.Doctor;
import com.hrms.entity.Patient;
import com.hrms.entity.Resource;
import com.hrms.repository.AdmissionRepo;
import com.hrms.repository.DoctorRepo;
import com.hrms.repository.PatientRepo;
import com.hrms.repository.ResourceRepo;
import com.hrms.service.AdmisssionDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AdmissionDaoImpl implements AdmisssionDao {

    private final PatientRepo patientRepo;
	
	@Autowired
	private AdmissionRepo admissionRepo;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private ResourceRepo resourceRepo;
	
	@Autowired
	private ModelMapper modelMapper;

    AdmissionDaoImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

	@Override
	public List<AdmissionResDto> listAllAdmission() {
		log.info("Fetching all admissions ...");
		
		List<AdmissionResDto> allAdmission = admissionRepo.findAll()
														.stream()
														.map(a -> modelMapper.map(a, AdmissionResDto.class))
														.collect(Collectors.toList());
		
		log.debug("Total Admission found: ", allAdmission.size());
		if(allAdmission.isEmpty()) {
			log.warn("No Addmission found in the database!");
		}else {
			log.info("Successfully fetched Admission list.", allAdmission.size());
		}
		return allAdmission;
	}

	@Override
	public AdmissionResDto getAdmissionById(long id) {
		log.info("Fetching admission details for ID: {}", id);
		
		Admission admission = admissionRepo.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("Admission not found for ID: " + id));
		
		log.debug("Admission found: ID = ,Patient Email = ", id, admission.getPatient().getEmail());
		
		return modelMapper.map(admission, AdmissionResDto.class);
	}

	@Override
	public AdmissionResDto admidPatient(AdmissionReqDto dto) {
		log.info("Admitting new patient with ID: ", dto.getPatientId());
		
		Patient patient = patientRepo.findById(dto.getPatientId())
				.orElseThrow(()-> new ResourceNotFoundException("Patient not found for ID: " + dto.getPatientId()));
		
		Doctor doctor = doctorRepo.findById(dto.getDoctorId())
		.orElseThrow( ()-> new ResourceNotFoundException("Doctor not found for ID: " + dto.getDoctorId()));
		
		Resource resource = resourceRepo.findById(dto.getResourceId())
							.orElseThrow(()-> new ResourceNotFoundException("Resource not found for ID: " + dto.getResourceId()));		
		return null;
	}

	@Override
	public AdmissionResDto updateAdmission(Long id, AdmissionReqDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dischargePatient(Long id) {
		// TODO Auto-generated method stub
		
	}	
}
