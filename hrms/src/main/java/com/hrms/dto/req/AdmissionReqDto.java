package com.hrms.dto.req;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AdmissionReqDto {
	
	@NotNull(message = "Patient ID is required")
	private Long patientId;
	
	@NotNull(message = "Doctor ID is required")
	private Long doctorId;
	
	@NotNull(message = "Resource ID is required")
	private Long resourceId;
}
