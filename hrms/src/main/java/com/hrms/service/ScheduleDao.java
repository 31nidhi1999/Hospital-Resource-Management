package com.hrms.service;

import java.util.List;

import com.hrms.dto.req.ScheduleReqDto;
import com.hrms.dto.res.ScheduleResDto;

public interface ScheduleDao {
	ScheduleResDto createSchedule(ScheduleReqDto dto);
    ScheduleResDto updateSchedule(Long id, ScheduleReqDto dto);
    void deleteSchedule(Long id);
    List<ScheduleResDto> getSchedulesByDoctor(Long doctorId);
    List<ScheduleResDto> getAllSchedules();
}
