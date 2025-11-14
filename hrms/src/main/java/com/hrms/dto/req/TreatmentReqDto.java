package com.hrms.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreatmentReqDto {
	
	@NotBlank(message = "diagnosis disease name required")
	private String diagnosis;
	
	@NotBlank(message = "Medicine name required")
    private String prescribedMedication;
    
	@NotNull(message = "Doctor Id required")
    private Long doctor_id;
	
	@NotNull(message = "Patient Id required")
    private Long patient_id;
}
