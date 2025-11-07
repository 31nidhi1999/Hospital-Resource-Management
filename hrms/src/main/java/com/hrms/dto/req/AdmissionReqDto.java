package com.hrms.dto.req;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AdmissionReqDto {
	private LocalDateTime dischargeDate;
	private Long patientId;
	private Long doctorId;
	private Long resourceId;
}
