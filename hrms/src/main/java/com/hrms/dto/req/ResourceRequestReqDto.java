package com.hrms.dto.req;

import com.hrms.entity.RequestStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceRequestReqDto {
	private RequestStatus status = RequestStatus.PENDING;
	
	@NotNull(message = "Doctor ID is required")
	private Long doctor_id;
	
	@NotNull(message = "Patient ID is required")
	private Long patient_id;
	
	@NotNull(message = "Resource ID is required")
	private Long resource_id;
	
	@NotNull(message = "Admission ID required")
    private Long admission_id;
}
