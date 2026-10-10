package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "COMPANIES")
@Setter
@Getter
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Column(name = "LOGO", length = 500)
	private String logo;

	@Column(name = "INDUSTRY", nullable = false, length = 100)
	private String industry;

	@Column(name = "SIZE", nullable = false, length = 50)
	private String size;

	@Column(name = "RATING", nullable = false, precision = 3, scale = 2)
	private BigDecimal rating;

	@Column(name = "LOCATIONS", length = 1000)
	private String locations;

	@Column(name = "FOUNDED", nullable = false)
	private Integer founded;

	@Lob
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "EMPLOYEES")
	private Integer employees;

	@Column(name = "WEBSITE", length = 500)
	private String website;

	@CreationTimestamp
	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "CREATED_BY", nullable = false, length = 20)
	private String createdBy;

	@Column(name = "UPDATED_AT")
	private Instant updatedAt;

	@Column(name = "UPDATED_BY", length = 20)
	private String updatedBy;

}
