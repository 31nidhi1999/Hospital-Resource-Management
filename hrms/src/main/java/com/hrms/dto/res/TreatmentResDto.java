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
    
    private Long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
}
