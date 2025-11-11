package com.hrms.service;

import java.util.List;

import com.hrms.dto.req.ResourceReqDto;
import com.hrms.dto.res.PatientResDto;
import com.hrms.dto.res.ResourceResDto;

public interface ResourceDao {
	ResourceResDto registerResource(Long adminId,ResourceReqDto dto);
	ResourceResDto updateResource(Long resId, ResourceReqDto dto);
	List<ResourceResDto> listAll();
	void deleteResource(Long id);
	ResourceResDto getResourceById(Long id);
	void updateAvailableQuantity(Long resId, int newAvailableQuantity);
    void updateTotalQuantity(Long resId, int newTotalQuantity);
}
