package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}
