package com.hrms.dto.res;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreatmentResDto {
	private Long id;
    private String diagnosis;
    private String prescribedMedication;
    private LocalDateTime createdAt;
    
    private Long doctor_id;
    private String doctorFirstName;
    
    private Long patient_id;
    private String patientFirstName;
}
