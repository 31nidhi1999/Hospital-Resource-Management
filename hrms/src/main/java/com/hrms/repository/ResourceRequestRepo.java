package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.RequestStatus;
import com.hrms.entity.ResourceRequest;

@Repository
public interface ResourceRequestRepo extends JpaRepository<ResourceRequest, Long> {
	List<ResourceRequest> findByAdmission_Id(long id);
	List<ResourceRequest> findByStatus(RequestStatus status);
}
