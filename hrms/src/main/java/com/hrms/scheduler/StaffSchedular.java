package com.hrms.scheduler;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hrms.service.ScheduleDao;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StaffSchedular {
	
		@Autowired
		private ScheduleDao scheduleDao;
		
		@Scheduled(cron = "0 5 0 * * ?")
		public void runOnSecondLastDay() {
		LocalDate today = LocalDate.now();
		LocalDate lastDay = today.withDayOfMonth(today.lengthOfMonth());
		LocalDate secondLast = lastDay.minusDays(1);
	
	
		if (today.equals(secondLast)) {
			log.info("Today is second-last day: {} - generating schedules", today);
			scheduleDao.generateNextMonthSchedule();
		}
	}
}
