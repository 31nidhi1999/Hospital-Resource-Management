package com.hrms.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hrms.entity.ResourceType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceReqDto {
	
	@NotBlank(message="Resource name is required")
	private String resourceName;
	
	@NotNull(message="Resource type is required")
	private ResourceType resourceType;
	
	@NotNull(message="Total quantity resource is required")
	private Integer totalQuantity;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Integer availableQuantity;
}
