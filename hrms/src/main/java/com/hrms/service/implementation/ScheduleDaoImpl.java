package com.hrms.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ResourceNotFoundException;
import com.hrms.dto.req.ScheduleReqDto;
import com.hrms.dto.res.ScheduleResDto;
import com.hrms.entity.Doctor;
import com.hrms.entity.Schedule;
import com.hrms.repository.DoctorRepo;
import com.hrms.repository.ScheduleRepo;
import com.hrms.service.ScheduleDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ScheduleDaoImpl implements ScheduleDao{
	
	@Autowired
	private ScheduleRepo scheduleRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Override
	public ScheduleResDto createSchedule(ScheduleReqDto dto) {
		log.info("Creating schedule for Doctor ID: {}", dto.getDoctorId());
		
		Doctor doctor = doctorRepo.findById(dto.getDoctorId())
				.orElseThrow(()-> new ResourceNotFoundException("Doctor not found for ID: " + dto.getDoctorId()));
		
		Schedule schedule = modelMapper.map(dto, Schedule.class);
		
		schedule.setDoctor(doctor);
		Schedule savedSchedule = scheduleRepo.save(schedule);
		
		log.info("Schedule created successfully for Doctor: {}", doctor.getEmail());
		
		return modelMapper.map(savedSchedule, ScheduleResDto.class);
	}

	@Override
	public ScheduleResDto updateSchedule(Long id, ScheduleReqDto dto) {
		log.info("Updating schedule with ID: {}", id);
		
		Schedule schedule = scheduleRepo.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("Schedule not found for ID: " + dto.getDoctorId()));
		
		Doctor doctor = doctorRepo.findById(dto.getDoctorId())
			.orElseThrow(()-> new ResourceNotFoundException("Doctor not found for ID: " + dto.getDoctorId()));
		
		Schedule schedule2 = modelMapper.map(schedule, Schedule.class);
		Schedule savedSchedule = scheduleRepo.save(schedule2);
		
		log.info("Schedule updated successfully.");
		return modelMapper.map(savedSchedule, ScheduleResDto.class);
	}

	@Override
	public void deleteSchedule(Long id) {
		log.info("Deleting schedule with ID: {}", id);
		
        Schedule schedule = scheduleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found for ID: " + id));
        
        scheduleRepo.delete(schedule);
        
        log.info("Schedule deleted successfully.");
		
	}

	@Override
	public List<ScheduleResDto> getSchedulesByDoctor(Long doctorId) {
		log.info("Fetching all schedule for Doctor...");
		
		List<ScheduleResDto> allSchedule = scheduleRepo.findByDoctor_Id(doctorId)
				.stream()
				.map(s->modelMapper.map(s, ScheduleResDto.class))
				.collect(Collectors.toList());
		
		
		log.debug("Total schedule found: ", allSchedule.size());
		 
		 if (allSchedule.isEmpty()) {
		    	log.warn("No schedule found in the database!");
		    } else {
		    	log.info("Successfully fetched schedule list.", allSchedule.size());
		    }
		
		return allSchedule;
	}

	@Override
	public List<ScheduleResDto> getAllSchedules() {
		log.info("Fetching all schedule...");
		
		List<ScheduleResDto> allSchedule = scheduleRepo.findAll()
				.stream()
				.map(s -> modelMapper.map(s, ScheduleResDto.class))
				.collect(Collectors.toList());
		
		log.debug("Total schedule found: ", allSchedule.size());
		 
		 if (allSchedule.isEmpty()) {
		    	log.warn("No schedule found in the database!");
		    } else {
		    	log.info("Successfully fetched schedule list.", allSchedule.size());
		    }
		
		return allSchedule;
	}

}
