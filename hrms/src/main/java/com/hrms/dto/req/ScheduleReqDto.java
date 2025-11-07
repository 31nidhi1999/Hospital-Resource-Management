package com.hrms.dto.req;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScheduleReqDto {
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String shiftType;

    private Long doctorId;
}
