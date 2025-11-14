package com.hrms.service;

import java.util.List;

import com.hrms.dto.req.UserReqDto;
import com.hrms.dto.res.UserResDto;

import jakarta.validation.Valid;


public interface AdminDao {
	UserResDto registerAdmin(UserReqDto dto);
	UserResDto updateAdmin(Long userId, UserReqDto dto);
	List<UserResDto> listAll();
	void deleteAdmin(Long id);
	UserResDto getAdminById(Long id);
}
