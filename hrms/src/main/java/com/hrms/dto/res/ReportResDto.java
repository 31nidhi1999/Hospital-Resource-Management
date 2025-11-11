package com.hrms.dto.res;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportResDto {
	private Long id;
    private String title;
    private String description;
    private LocalDateTime generatedAt;
    private Long adminId;
}
