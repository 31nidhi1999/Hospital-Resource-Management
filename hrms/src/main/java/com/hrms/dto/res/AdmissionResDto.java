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
    private String patientFirstName;
    private String patientLastName;

    private Long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private Boolean active;
}
