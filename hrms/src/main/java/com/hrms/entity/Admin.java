package com.hrms.entity;

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
@Table(name="admins")
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User {
	
	@OneToMany(mappedBy = "admin",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Resource> resources;
	
	@OneToMany(mappedBy = "admin",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Report> reports;
}
