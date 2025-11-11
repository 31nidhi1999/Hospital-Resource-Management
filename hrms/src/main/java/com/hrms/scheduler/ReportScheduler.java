package com.hrms.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hrms.helper.ReportGeneratorHelper;
import com.hrms.service.ReportDao;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReportScheduler {
	
	@Autowired
	private ReportGeneratorHelper helper;
	
	//every midnight
	@Scheduled(cron="0 0 0 * * ?")
	public void generateResurceUtilizationReport() {
		log.info("Scheduled Task: Generating daily Resource Utilization Report...");
		helper.generateResourceUtilizationReport();
	}
	
	//generate report every moday at 6am
	@Scheduled(cron="0 0 6 ? * MON")
	public void generatePatientAdmissionReport() {
		log.info("Scheduled Task: Generating weekly Patient Admission Report...");
		helper.generatePatientAdmissionReport();
	}
	
	//generate reprt every 1 of month
	@Scheduled(cron="0 0 2 1 * ?")
	public void generateStaffPerformanceReport() {
		log.info("Scheduled Task: Generating monthly Staff Performance Report...");
		helper.generateStaffPerformanceReport();
	}
}
