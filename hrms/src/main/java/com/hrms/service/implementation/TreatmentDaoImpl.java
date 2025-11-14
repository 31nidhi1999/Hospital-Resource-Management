package com.hrms.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ApiException;
import com.hrms.custom_exceptions.ResourceNotFoundException;
import com.hrms.dto.req.TreatmentReqDto;
import com.hrms.dto.res.TreatmentResDto;
import com.hrms.entity.Doctor;
import com.hrms.entity.Patient;
import com.hrms.entity.Treatment;
import com.hrms.repository.DoctorRepo;
import com.hrms.repository.PatientRepo;
import com.hrms.repository.TreatmentRepo;
import com.hrms.service.TreatmentDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TreatmentDaoImpl implements TreatmentDao {
	
	@Autowired
	private TreatmentRepo treatmentRepo;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TreatmentResDto createTreatment(TreatmentReqDto dto) {
		log.info("Creating treatment for patient ID: {}", dto.getPatient_id());

		Doctor doctor = doctorRepo.findById(dto.getDoctor_id())
			.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + dto.getDoctor_id()));
		
		Patient patient = patientRepo.findById(dto.getPatient_id())
		.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + dto.getDoctor_id()));
		
		Treatment treatment = modelMapper.map(dto, Treatment.class);
		
		treatment.setDoctor(doctor);
		treatment.setPatient(patient);

		Treatment savedTreatment = treatmentRepo.save(treatment);
		
		log.info("Treatment created successfully with ID: {}", savedTreatment.getId());
		
		return modelMapper.map(savedTreatment, TreatmentResDto.class);
	}

	@Override
	public TreatmentResDto getTreatmentById(Long id) {
		log.info("Fetching treatment with ID: {}", id);
		
		Treatment treatment = treatmentRepo.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Treatment not found with ID: " + id));
		
		log.info("Treatment fetched successfully with ID: {}", treatment.getId());
		
		treatment.getDoctor().getFirstName();
		treatment.getPatient().getFirstName();
		return modelMapper.map(treatment, TreatmentResDto.class);
	}

	@Override
	public List<TreatmentResDto> getAllTreatments() {
		
		List<TreatmentResDto> allTreatment = treatmentRepo.findAll()
				.stream()
				.map(t->modelMapper.map(t, TreatmentResDto.class))
				.collect(Collectors.toList());

		 log.debug("Total treatment found: ", allTreatment.size());

		    if (allTreatment.isEmpty()) {
		    	log.warn("No treatment found in the database!");
		    } else {
		    	log.info("Successfully fetched treatment list.", allTreatment.size());
		    }

		return allTreatment;
	}

	@Override
	public TreatmentResDto updateTreatment(Long id, TreatmentReqDto dto) {
		log.info("Updating treatment for patient ID: {}", dto.getPatient_id());
		
		if(treatmentRepo.existsById(id)) {
			throw new ApiException("Invalid doctor ID: " + id);
		}

		Doctor doctor = doctorRepo.findById(dto.getDoctor_id())
			.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + dto.getDoctor_id()));
		
		Patient patient = patientRepo.findById(dto.getPatient_id())
		.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + dto.getDoctor_id()));
		
		Treatment treatment = modelMapper.map(dto, Treatment.class);
		
		treatment.setDoctor(doctor);
		treatment.setPatient(patient);

		Treatment savedTreatment = treatmentRepo.save(treatment);
		
		log.info("Treatment created successfully with ID: {}", savedTreatment.getId());
		
		return modelMapper.map(savedTreatment, TreatmentResDto.class);
	}

	@Override
	public void deleteTreatment(Long id) {
		log.info("Deleting treatment with ID: ", id);
		
        Treatment treatment = treatmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with ID: " + id));
        treatmentRepo.delete(treatment);
        log.info("Treatment deleted successfully with ID: ", id);
		
	}
}
