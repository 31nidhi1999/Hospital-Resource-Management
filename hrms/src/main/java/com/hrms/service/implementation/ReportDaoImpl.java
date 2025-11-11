package com.hrms.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.dto.res.ReportResDto;
import com.hrms.entity.Report;
import com.hrms.helper.ReportGeneratorHelper;
import com.hrms.repository.ReportRepo;
import com.hrms.service.ReportDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ReportDaoImpl implements ReportDao{
	
	@Autowired
	private ReportGeneratorHelper generatorHelper;
	
	@Autowired
	private ReportRepo reportRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ReportResDto> generateReports() {
		
		List<ReportResDto> reports = new ArrayList<>(); 
		
		Report utilizationReport = generatorHelper.generateResourceUtilizationReport();
		reports.add(modelMapper.map(utilizationReport,ReportResDto.class));
		
		Report admissionReport = generatorHelper.generatePatientAdmissionReport();
		reports.add(modelMapper.map(admissionReport,ReportResDto.class));
		
		Report performanceReport = generatorHelper.generateStaffPerformanceReport();
		reports.add(modelMapper.map(performanceReport,ReportResDto.class));
		
		return reports;
	}

	@Override
	public List<ReportResDto> getAllReport() {
		log.info("Fetching all reports...");
		
		List<ReportResDto> allReport = reportRepo.findAll()
		.stream()
		.map(r -> modelMapper.map(r, ReportResDto.class))
		.collect(Collectors.toList());
		
		 log.debug("Total reports found: ", allReport.size());
		 
		 if (allReport.isEmpty()) {
		    	log.warn("No doctors found in the database!");
		    } else {
		    	log.info("Successfully fetched doctors list.", allReport.size());
		    }
		
		return allReport;
	}
	
}
