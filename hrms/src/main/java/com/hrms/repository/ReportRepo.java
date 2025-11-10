package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Report;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {

}
