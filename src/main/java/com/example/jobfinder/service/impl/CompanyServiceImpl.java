package com.example.jobfinder.service.impl;

import com.example.jobfinder.exception.ResourceNotFoundException;
import com.example.jobfinder.model.Company;
import com.example.jobfinder.model.User;
import com.example.jobfinder.payload.company.CompanyCreateReq;
import com.example.jobfinder.repository.CompanyRepository;
import com.example.jobfinder.repository.UserRepository;
import com.example.jobfinder.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long id) throws ResourceNotFoundException {
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company Not Found"));
    }

    @Override
    public Company save(CompanyCreateReq company) {
        Company newCompany = new Company();
        newCompany.setName(company.getName());
        newCompany.setDescription(company.getDescription());
        newCompany.setEmail(company.getEmail());
        newCompany.setPhone(company.getPhone());
        newCompany.setWebsite(company.getWebsite());

//        newCompany.setOwner(user);

        Company companyToAdd = companyRepository.save(newCompany);
        User user = userRepository.findById(company.getUserId()).get();
        user.setCompany(companyToAdd);
        userRepository.save(user);

        return companyToAdd;
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

    @Override
    public void addCompanyToUser(Long userId, Long companyId) throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company Not Found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        user.setCompany(company);
        companyRepository.save(company);
    }

    @Override
    public void addAdminToCompany(Long userId, Long companyId) throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company Not Found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        user.setCompany(company);

        Set admins = company.getAdmin();
        admins.add(user);

        company.setAdmin(admins);
        companyRepository.save(company);
    }
}
