package com.hrms.entity;

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
	
	private Integer age;
	
	private String address;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@OneToOne
	private Admission admission;
	
	@OneToOne
	private Treatment treatments;
	
	@OneToOne
	private Resource resources;
}
