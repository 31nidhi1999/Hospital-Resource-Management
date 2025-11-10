package com.hrms.dto.req;

import com.hrms.entity.ResourceType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceReqDto {
	private String resourceName;
	private ResourceType resourceType;
	private Long admin_id;
	
}
