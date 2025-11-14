package com.hrms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
public class ResourceAllocation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admission_id")
    private Admission admission;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    private LocalDateTime allocatedAt;

    private LocalDateTime deallocatedAt;

    private boolean isActive = true;
}
