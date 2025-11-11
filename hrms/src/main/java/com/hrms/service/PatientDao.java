package com.hrms.service;

import java.util.List;

import com.hrms.dto.req.PatientReqDto;
import com.hrms.dto.res.DoctorResDto;
import com.hrms.dto.res.PatientResDto;

public interface PatientDao {
	PatientResDto registerPatient(PatientReqDto dto);
	PatientResDto updatePatient(Long patId, PatientReqDto dto);
	List<PatientResDto> listAll();
	void deletePatient(Long id);
	PatientResDto getPatientById(Long id);
}
