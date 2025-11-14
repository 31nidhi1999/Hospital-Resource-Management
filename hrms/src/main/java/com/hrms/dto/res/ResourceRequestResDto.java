package com.hrms.dto.res;

import java.time.LocalDateTime;

import com.hrms.entity.RequestStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceRequestResDto {
	private Long id;
    private LocalDateTime requestDate;
    private RequestStatus status;

    private Long doctorId;
    private String doctorFirstName;

    private Long patientId;
    private String patientFirstName;

    private Long resourceId;
    private String resourceName;
    
    private Long admissionId;
}
