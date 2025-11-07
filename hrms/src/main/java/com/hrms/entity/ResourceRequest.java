package com.hrms.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime requestDate;
	
	@Enumerated(EnumType.STRING)
	private RequestStatus status;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="resource_id")
	private Resource resource;
}
