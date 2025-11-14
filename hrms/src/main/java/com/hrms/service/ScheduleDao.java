package com.hrms.service;

import java.util.List;

import com.hrms.dto.res.ScheduleResDto;

public interface ScheduleDao {
	
	void generateNextMonthSchedule();
    List<ScheduleResDto> getSchedulesByDoctor(Long doctorId);
    List<ScheduleResDto> getAllSchedules();
}
