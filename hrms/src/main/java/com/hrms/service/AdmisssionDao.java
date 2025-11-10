package com.hrms.service;

import java.util.List;

import com.hrms.dto.req.AdmissionReqDto;
import com.hrms.dto.res.AdmissionResDto;

public interface AdmisssionDao {
	List<AdmissionResDto> getAllAdmission();
	AdmissionResDto getAdmissionById(long id);
	AdmissionResDto registerAdmission(AdmissionReqDto dto);
	AdmissionResDto updateAdmission(Long id, AdmissionReqDto dto);
	void deleteAdmission(Long id);
}
