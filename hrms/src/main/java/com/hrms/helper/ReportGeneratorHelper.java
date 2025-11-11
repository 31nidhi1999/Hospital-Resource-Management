package com.hrms.helper;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.entity.Report;
import com.hrms.repository.ReportRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReportGeneratorHelper {
	
	@Autowired
	private ReportRepo reportRepo;
	
	public Report generateResourceUtilizationReport() {
        Report report = new Report();
        report.setTitle("Resource Utilization Report");
        report.setDescription("Daily summary of resource usage across hospital departments.");
        report.setGeneratedAt(LocalDateTime.now());
        reportRepo.save(report);
        log.info("✅ Resource Utilization Report generated.");
        return report;
    }

    public Report generatePatientAdmissionReport() {
        Report report = new Report();
        report.setTitle("Patient Admission Report");
        report.setDescription("Weekly summary of patient admissions and discharges.");
        report.setGeneratedAt(LocalDateTime.now());
        reportRepo.save(report);
        log.info("✅ Patient Admission Report generated.");
        return report;
    }

    public Report generateStaffPerformanceReport() {
        Report report = new Report();
        report.setTitle("Staff Performance Report");
        report.setDescription("Monthly evaluation of staff performance metrics.");
        report.setGeneratedAt(LocalDateTime.now());
        reportRepo.save(report);
        log.info("✅ Staff Performance Report generated.");
        return report;
    }}
