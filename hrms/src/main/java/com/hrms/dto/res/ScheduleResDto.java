package com.hrms.dto.res;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScheduleResDto {
	private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String shiftType;

    private Long doctorId;
    private String doctorName;
}
