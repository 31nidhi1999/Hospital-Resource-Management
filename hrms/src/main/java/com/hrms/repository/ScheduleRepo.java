package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Schedule;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long>{
	List<Schedule> findByDoctor_Id(Long id);
}
