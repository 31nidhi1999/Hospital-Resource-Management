package com.hrms.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ResourceNotFoundException;
import com.hrms.dto.res.ScheduleResDto;
import com.hrms.entity.Doctor;
import com.hrms.entity.Schedule;
import com.hrms.helper.ScheduleHelper;
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
	
	@Autowired
	private ScheduleHelper helper;

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

	public void generateNextMonthSchedule() {
		List<Doctor> activeDoctors = doctorRepo.findByIsActiveTrue();
		if (activeDoctors.isEmpty()) {
		log.warn("No active doctors found - skipping schedule generation");
		return;
		}

		LocalDate nextMonth = LocalDate.now().plusMonths(1).withDayOfMonth(1);
		int daysInMonth = nextMonth.lengthOfMonth();

		for (int day = 1; day <= daysInMonth; day++) {
		LocalDate date = nextMonth.withDayOfMonth(day);
		List<Schedule> schedulesForDay = helper.buildSchedulesForDate(date, activeDoctors);
		scheduleRepo.saveAll(schedulesForDay);
		}


		log.info("Generated schedules for {} doctors for month {}", activeDoctors.size(), nextMonth.getMonth());
		}

}
