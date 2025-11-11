package com.hrms.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ApiException;
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
		
		if(admissionRepo.existsByPatient_IdAndDischargeDateIsNull(dto.getPatientId())) {
			throw new ApiException("Patient is already admitted and not yet discharged.");
		}
		
		Doctor doctor = doctorRepo.findById(dto.getDoctorId())
		.orElseThrow( ()-> new ResourceNotFoundException("Doctor not found for ID: " + dto.getDoctorId()));
		
		Resource resource = resourceRepo.findById(dto.getResourceId())
							.orElseThrow(()-> new ResourceNotFoundException("Resource not found for ID: " + dto.getResourceId()));	
		
		if(!resource.isAvailable() || resource.getAvailableQuantity() <=0) {
			throw new ApiException("Resource is not available for admission.");
		}
		
		Admission admission = modelMapper.map(dto, Admission.class);
		admission.setPatient(patient);
		admission.setDoctor(doctor);
		admission.setResource(resource);
		
		Admission savedAdmission = admissionRepo.save(admission);
		
		return modelMapper.map(savedAdmission, AdmissionResDto.class);
	}

	@Override
	public AdmissionResDto updateAdmission(Long id, AdmissionReqDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dischargePatient(Long id) {
		log.info("Discharging patient for admission ID: {}", id);
		
		Admission admission = admissionRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Admission not found for ID: " + id));
		
		if(admission.getDischargeDate()!=null) {
			throw new ApiException("Patient is already discharged.");
		}
		
		Resource resource = admission.getResource();
		resource.setAvailableQuantity(resource.getAvailableQuantity() + 1);
		resource.setAvailable(true);
		
		resourceRepo.save(resource);
		
		admission.setDischargeDate(LocalDateTime.now());
		
		admissionRepo.save(admission);
		
		log.info("Patient discharged successfully for admission ID: {}", id);
	}	
}
