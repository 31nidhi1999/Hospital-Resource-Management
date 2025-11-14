package com.hrms.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.custom_exceptions.ResourceNotFoundException;
import com.hrms.dto.req.UserReqDto;
import com.hrms.dto.res.UserResDto;
import com.hrms.dto.res.DoctorResDto;
import com.hrms.entity.Doctor;
import com.hrms.entity.User;
import com.hrms.repository.AdminRepo;
import com.hrms.service.AdminDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserResDto registerAdmin(UserReqDto dto) {
		log.info("Creating new doctor with name: ", dto.getEmail());

		User admin = modelMapper.map(dto, User.class);
		admin.setRole(dto.getRole());
		
		User savedAdmin = adminRepo.save(admin);
		log.debug("Doctor saved with ID: ", savedAdmin.getId());
		
		UserResDto adminResDto = modelMapper.map(savedAdmin, UserResDto.class);
		
		log.info("Doctor created successfully: ID = , Email = ", 
				adminResDto.getId(), adminResDto.getEmail());
		
		return adminResDto;
	}

	@Override
	public UserResDto updateAdmin(Long userId, UserReqDto dto) {
		log.info("Updating admin with ID: {}", userId);
		
		User admin = adminRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("Admin not found for id : " + userId));
		 
		modelMapper.map(dto, admin);
		log.debug("Mapped adminReqDto to existing admin entity for ID: ", userId);
		
		User admin2 = adminRepo.save(admin);
		log.debug("admin entity saved successfully with ID: ", admin2.getId());
		
		UserResDto adminResDto = modelMapper.map(admin2, UserResDto.class);
		 log.info("admin updated successfully: ID = , email = ", 
				 adminResDto.getId(), adminResDto.getEmail());

		return adminResDto;
	}

	@Override
	public List<UserResDto> listAll() {
		log.info("Fetching all admins...");

	    List<UserResDto> alladmins = adminRepo.findAll()
	            .stream()
	            .filter(a -> a.getRole().toString()=="ADMIN")
	            .map(d -> modelMapper.map(d, UserResDto.class))
	            .collect(Collectors.toList());

	    log.debug("Total admin found: ", alladmins.size());

	    if (alladmins.isEmpty()) {
	    	log.warn("No admins found in the database!");
	    } else {
	    	log.info("Successfully fetched admin list.", alladmins.size());
	    }

	    return alladmins;
	}

	@Override
	public void deleteAdmin(Long id) {
		log.info("Request received to delete (soft delete) aadmin with ID: {}", id);

		User admin = adminRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found for id : " + id));

	    log.debug("Doctor found for deletion. ID: {}, Email: {} ", id, admin.getEmail());

	    admin.setActive(false);
	    adminRepo.save(admin);

	    log.info("Admin with ID:  has been soft deleted successfully.", id);
	}

	@Override
	public UserResDto getAdminById(Long id) {
		log.info("Fetching Admin with ID: ", id);

		User admin = adminRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Admin not found for id " + id));

		log.debug("Admin found: ID = , Admin Email = ", id, admin.getEmail());

		return modelMapper.map(admin, UserResDto.class);
	}

}
