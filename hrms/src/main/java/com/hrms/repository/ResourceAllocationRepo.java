package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.ResourceAllocation;

@Repository
public interface ResourceAllocationRepo extends JpaRepository<ResourceAllocation, Long> {
	List<ResourceAllocation> findByAdmissionIdAndIsActive(Long admissionId, boolean isActive);
}
