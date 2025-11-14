package com.hrms.service;

import java.util.List;

import com.hrms.dto.req.ResourceRequestReqDto;
import com.hrms.dto.res.ResourceRequestResDto;

public interface ResourceRequestDao {
	ResourceRequestResDto createRequest(ResourceRequestReqDto dto);
    ResourceRequestResDto getRequestById(Long id);
    List<ResourceRequestResDto> getAllRequests();
    ResourceRequestResDto approve(Long id);
    ResourceRequestResDto reject(Long requestId);
}
