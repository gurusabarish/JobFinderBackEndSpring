package com.example.jobfinder.service.impl;

import com.example.jobfinder.exception.ResourceNotFoundException;
import com.example.jobfinder.model.Company;
import com.example.jobfinder.repository.CompanyRepository;
import com.example.jobfinder.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long id) throws ResourceNotFoundException {
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company Not Found"));
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company update(Long id, Company company) throws ResourceNotFoundException {
        Company companyToUpdate = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company Not Found"));

        companyToUpdate.setName(company.getName()!=null?company.getName():companyToUpdate.getName());
        companyToUpdate.setDescription(company.getDescription()!=null?company.getDescription():companyToUpdate.getDescription());
        companyToUpdate.setEmail(company.getEmail()!=null?company.getEmail():companyToUpdate.getEmail());
        companyToUpdate.setPhone(company.getPhone()!=null?company.getPhone():companyToUpdate.getPhone());
        companyToUpdate.setWebsite(company.getWebsite()!=null?company.getWebsite():companyToUpdate.getWebsite());

        return companyRepository.save(companyToUpdate);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company Not Found"));
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> findByNameStartsWith(String company) {
        return companyRepository.findAllByNameStartsWith(company);
    }
}
