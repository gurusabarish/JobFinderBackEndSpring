package com.example.jobfinder.service;

import com.example.jobfinder.exception.ResourceNotFoundException;
import com.example.jobfinder.model.Company;
import com.example.jobfinder.payload.company.CompanyCreateReq;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company findCompanyById(Long id) throws ResourceNotFoundException;
    Company save(CompanyCreateReq company);
    Company update(Long id, Company company) throws ResourceNotFoundException;
    void delete(Long id) throws ResourceNotFoundException;
    List<Company> findByNameStartsWith(String company);
    void addCompanyToUser(Long userId, Long companyId) throws ResourceNotFoundException;
//    void addAdminToCompany(Long userId, Long companyId) throws ResourceNotFoundException;
}
