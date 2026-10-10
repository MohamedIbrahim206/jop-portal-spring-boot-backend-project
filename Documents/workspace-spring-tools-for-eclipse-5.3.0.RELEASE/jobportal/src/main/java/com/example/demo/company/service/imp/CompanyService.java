package com.example.demo.company.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.CompanyDTO;
import com.example.demo.company.service.ICompanyService;
import com.example.demo.entity.Company;
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
		return result.stream().map(this::transformToDto).collect(Collectors.toList());
		 
	}

	private CompanyDTO transformToDto(Company company) {
		return new CompanyDTO(company.getId(), company.getName(), company.getLogo(), company.getIndustry(),
				company.getSize(), company.getRating(), company.getLocations(), company.getFounded(),
				company.getDescription(), company.getEmployees(), company.getWebsite(), company.getCreatedAt());
	}

}
