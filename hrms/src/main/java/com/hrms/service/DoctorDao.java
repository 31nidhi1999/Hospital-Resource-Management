package com.hrms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hrms.dto.req.DoctorReqDto;
import com.hrms.dto.res.DoctorResDto;

import jakarta.transaction.Transactional;

public interface DoctorDao {
	DoctorResDto registerDoctor(DoctorReqDto dto);
	DoctorResDto updateDoctor(Long docId, DoctorReqDto dto);
	List<DoctorResDto> listAll();
	List<DoctorResDto> listAllActive();
}
