package com.hrms.dto.res;

import com.hrms.entity.ResourceType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceResDto {
	private Long id;
	private String resourceName;
	private ResourceType resourceType;
	private Boolean active;
	private Long adminId;
}
