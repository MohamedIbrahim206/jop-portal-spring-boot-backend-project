package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.JobPortalUser;

public interface JobPortalUserRepository extends JpaRepository<JobPortalUser, Long>{

	Optional<JobPortalUser> findByEmailOrMobileNumber(String email, String mobileNumber);
	
	Optional<JobPortalUser> findByEmail(String email);

}
