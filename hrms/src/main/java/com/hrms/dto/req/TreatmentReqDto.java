package com.hrms.dto.req;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreatmentReqDto {
	private String diagnosis;
    private String prescribedMedication;
    
    private Long doctorId;
    private Long patientId;
}
