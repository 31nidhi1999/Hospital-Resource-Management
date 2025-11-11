package com.hrms.service;

import java.util.List;

import com.hrms.dto.res.ReportResDto;

public interface ReportDao {
	List<ReportResDto> generateReports();
	List<ReportResDto> getAllReport();
}
