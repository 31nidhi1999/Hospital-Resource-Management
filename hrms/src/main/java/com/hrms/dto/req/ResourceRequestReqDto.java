package com.hrms.dto.req;

import com.hrms.entity.RequestStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceRequestReqDto {
	private RequestStatus status = RequestStatus.PENDING;
	private Long doctorId;
	private Long patientId;
	private Long resourceId;
}
