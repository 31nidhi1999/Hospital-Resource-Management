package com.hrms.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="doctors")
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends User {
	
	private String specialization;
	
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<Patient> patients = new ArrayList<>();
	
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<ResourceRequest> requests = new ArrayList<>();
	
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<Schedule> schedules = new ArrayList<>();
}
