package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.ResourceRequest;

@Repository
public interface ResourceRequestRepo extends JpaRepository<ResourceRequest, Long> {

}
