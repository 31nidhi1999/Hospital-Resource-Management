package com.hrms.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hrms.entity.ResourceType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceReqDto {
	private String resourceName;
	private ResourceType resourceType;
	private Integer totalQuantity;
	@JsonProperty(access = Access.READ_ONLY)
	private Integer availableQuantity;
}
