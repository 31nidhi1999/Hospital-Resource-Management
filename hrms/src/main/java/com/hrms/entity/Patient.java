package com.hrms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends User {
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private List<Doctor> doctors;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Admission> admissions;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Treatment> treatments;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Resource> resources;
}
