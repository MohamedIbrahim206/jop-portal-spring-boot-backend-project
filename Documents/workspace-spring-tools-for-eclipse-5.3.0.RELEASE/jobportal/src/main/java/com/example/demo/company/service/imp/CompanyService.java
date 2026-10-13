package com.example.demo.company.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.CompanyDTO;
import com.example.demo.DTO.JobDto;
import com.example.demo.company.service.ICompanyService;
import com.example.demo.entity.Company;
import com.example.demo.entity.Job;
import com.example.demo.repository.CompanyRepository;

@Service
public class CompanyService implements ICompanyService {

	private final CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	public List<CompanyDTO> getAllCompanies() {
		List<Company> result =  companyRepository.findAll();
		return result.stream().map(this::transformCompanyToDto).collect(Collectors.toList());
		 
	}

	private CompanyDTO transformCompanyToDto(Company company) {
		
		 List<JobDto> jobDtos = company.getJobs().stream()
	                .map(this::transformJobToDto)
	                .collect(Collectors.toList());
	                
		 return new CompanyDTO(company.getId(), company.getName(), company.getLogo(),
	                company.getIndustry(), company.getSize(), company.getRating(),
	                company.getLocations(), company.getFounded(), company.getDescription(),
	                company.getEmployees(), company.getWebsite(), company.getCreatedAt(),jobDtos);
	}
	
	
	private JobDto transformJobToDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getCompany().getId(),
                job.getCompany().getName(),
                job.getCompany().getLogo(),
                job.getLocation(),
                job.getWorkType(),
                job.getJobType(),
                job.getCategory(),
                job.getExperienceLevel(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getSalaryCurrency(),
                job.getSalaryPeriod(),
                job.getDescription(),
                job.getRequirements(),
                job.getBenefits(),
                job.getPostedDate(),
                job.getApplicationDeadline(),
                job.getApplicationsCount(),
                job.getFeatured(),
                job.getUrgent(),
                job.getRemote(),
                job.getStatus()
        );
    }
	
	



}
