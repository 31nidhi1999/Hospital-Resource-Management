package com.hrms.dto.req;

import com.hrms.entity.RequestStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceRequestReqDto {
	private RequestStatus status = RequestStatus.PENDING;
	
	@NotBlank(message = "Doctor name is required")
	private Long doctorId;
	
	@NotBlank(message = "Patient name is required")
	private Long patientId;
	
	@NotBlank(message = "Resource name is required")
	private Long resourceId;
}
