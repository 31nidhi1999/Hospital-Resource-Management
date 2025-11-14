package com.hrms.service;

import java.util.List;

import com.hrms.dto.req.AdmissionReqDto;
import com.hrms.dto.res.AdmissionResDto;

public interface AdmisssionDao {
	List<AdmissionResDto> listAllAdmission();
	AdmissionResDto getAdmissionById(long id);
	AdmissionResDto admitPatient(AdmissionReqDto dto);
	void dischargePatient(Long id);
}
