package com.hrms.dto.res;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AdmissionResDto {
	private Long id;
    private LocalDateTime admissionDate;
    private LocalDateTime dischargeDate;

    private Long patientId;
    private String patientName;

    private Long doctorId;
    private String doctorName;

    private Long resourceId;
    private String resourceName; 
}
