package com.example.demo.company.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CompanyDTO;
import com.example.demo.entity.Company;
import com.example.demo.service.ICompanyService;

@RestController
@RequestMapping("/companies")
@CrossOrigin(origins = {"http://localhost:5173"})
public class CompanyController {
	
	private final ICompanyService companyService;
	
	
	public CompanyController(ICompanyService companyService) {
		super();
		this.companyService = companyService;
	}
 

	@GetMapping(version = "1.0")
	public ResponseEntity<List<CompanyDTO>> getAllCompanies(){
		
		List<CompanyDTO> result = companyService.getAllCompanies();
		return ResponseEntity.ok().body(result);
	} 
	
	
	

}
