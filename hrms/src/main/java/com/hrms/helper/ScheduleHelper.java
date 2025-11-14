package com.hrms.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.entity.Doctor;
import com.hrms.entity.Schedule;
import com.hrms.entity.ShiftType;

@Component
public class ScheduleHelper {
	public LocalDateTime getStartTime(LocalDate date, ShiftType shift) {
		return switch (shift) {
			case MORNING -> date.atTime(6, 0);
			case EVENING -> date.atTime(14, 0);
			case NIGHT -> date.atTime(22, 0);
		};
	}


		public LocalDateTime getEndTime(LocalDate date, ShiftType shift) {
			return switch (shift) {
				case MORNING -> date.atTime(14, 0);
				case EVENING -> date.atTime(22, 0);
				case NIGHT -> date.plusDays(1).atTime(6, 0);
			};
		}
		
		public ShiftType assignShift(int index, int totalDoctors) {
			int mod = index % 3;
			return switch (mod) {
			case 0 -> ShiftType.MORNING;
			case 1 -> ShiftType.EVENING;
			default -> ShiftType.NIGHT;
			};
			}


			public List<Schedule> buildSchedulesForDate(LocalDate date, List<Doctor> doctors) {
				List<Schedule> result = new ArrayList<>();
				int total = doctors.size();
				for (int i = 0; i < total; i++) {
					Doctor doc = doctors.get(i);
					ShiftType shift = assignShift(i, total);
					Schedule s = new Schedule();
					s.setDoctor(doc);
					s.setShiftType(shift);
					s.setStartTime(getStartTime(date, shift));
					s.setEndTime(getEndTime(date, shift));
					result.add(s);
				}
				return result;
			}
}
