package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Resource;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Long> {

}
