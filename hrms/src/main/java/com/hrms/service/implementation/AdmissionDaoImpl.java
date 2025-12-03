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
import com.hrms.entity.ResourceAllocation;
import com.hrms.repository.AdmissionRepo;
import com.hrms.repository.DoctorRepo;
import com.hrms.repository.PatientRepo;
import com.hrms.repository.ResourceAllocationRepo;
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
	
	@Autowired
	private ResourceAllocationRepo allocationRepo;

    AdmissionDaoImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

	@Override
	public List<AdmissionResDto> listAllAdmission() {
		log.info("Fetching all admissions ...");
		
		List<AdmissionResDto> allAdmission = admissionRepo.findAllByIsActiveFalse()
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
	public AdmissionResDto admitPatient(AdmissionReqDto dto) {
		log.info("Admitting new patient with ID: ", dto.getPatient_id());
		
		Patient patient = patientRepo.findById(dto.getPatient_id())
				.orElseThrow(()-> new ResourceNotFoundException("Patient not found for ID: " + dto.getPatient_id()));
		
		if(admissionRepo.existsByPatient_IdAndDischargeDateIsNull(dto.getPatient_id())) {
			throw new ApiException("Patient is already admitted and not yet discharged.");
		}
		
		Doctor doctor = doctorRepo.findById(dto.getDoctor_id())
		.orElseThrow( ()-> new ResourceNotFoundException("Doctor not found for ID: " + dto.getDoctor_id()));
		
		Admission admission = modelMapper.map(dto, Admission.class);
		admission.setPatient(patient);
		admission.setDoctor(doctor);
		
		Admission savedAdmission = admissionRepo.save(admission);
		
		return modelMapper.map(savedAdmission, AdmissionResDto.class);
	}

	@Override
	public void dischargePatient(Long id) {
		log.info("Discharging patient for admission ID: {}", id);
		
		Admission admission = admissionRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Admission not found for ID: " + id));
		
		if(admission.getDischargeDate()!=null) {
			throw new ApiException("Patient is already discharged.");
		}
		
		log.info("Discharging patient for admission ID: {}", id);

        admission.setActive(false);
        admission.setDischargeDate(LocalDateTime.now());
        admissionRepo.save(admission);
	    
        List<ResourceAllocation> allocations =
                allocationRepo.findByAdmissionIdAndIsActive(id, true);

        log.info("Active resource allocations found: {}", allocations.size());
        
        for (ResourceAllocation allocation : allocations) {

            Resource resource = allocation.getResource();
            resource.setAvailableQuantity(resource.getAvailableQuantity() + 1);
            resourceRepo.save(resource);

            allocation.setActive(false);
            allocation.setDeallocatedAt(LocalDateTime.now());
            allocationRepo.save(allocation);

            log.info("Resource {} deallocated from admission {}", resource.getId(), id);
        }
	}

	@Override
	public List<AdmissionResDto> getAdmissionByDoctorId(Long id) {
		log.info("Fetching all admissions ...");
		
		if(!doctorRepo.existsById(id)) {
			throw new ApiException("Invalid doctor ID: " + id);
		}

		List<AdmissionResDto> allAdmission = admissionRepo.findByDoctor_Id(id).stream()
				.map(a -> modelMapper.map(a, AdmissionResDto.class)).collect(Collectors.toList());

		log.debug("Total Admission found: ", allAdmission.size());
		if (allAdmission.isEmpty()) {
			log.warn("No Addmission found in the database!");
		} else {
			log.info("Successfully fetched Admission list.", allAdmission.size());
		}
		return allAdmission;
	}

	@Override
	public List<AdmissionResDto> getAdmissionByPatientId(Long id) {
		log.info("Fetching all admissions ...");
		
		if(!patientRepo.existsById(id)) {
			throw new ApiException("Invalid patient ID: " + id);
		}

		List<AdmissionResDto> allAdmission = admissionRepo.findByPatient_Id(id).stream()
				.map(a -> modelMapper.map(a, AdmissionResDto.class)).collect(Collectors.toList());

		log.debug("Total Admission found: ", allAdmission.size());
		if (allAdmission.isEmpty()) {
			log.warn("No Addmission found in the database!");
		} else {
			log.info("Successfully fetched Admission list.", allAdmission.size());
		}
		return allAdmission;
	}

	@Override
	public List<AdmissionResDto> getAllActiveAdmission() {
	log.info("Fetching all admissions ...");
			
			List<AdmissionResDto> allAdmission = admissionRepo.findAllByIsActiveTrue()
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
}
