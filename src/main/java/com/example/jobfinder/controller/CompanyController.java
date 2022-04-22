package com.example.jobfinder.controller;

import com.example.jobfinder.exception.ResourceNotFoundException;
import com.example.jobfinder.model.Company;
import com.example.jobfinder.model.User;
import com.example.jobfinder.payload.company.CompanyCreateReq;
import com.example.jobfinder.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/company")
public class CompanyController {
    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @GetMapping()
    public List<Company> getAllCompanies() {
        return companyServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(companyServiceImpl.findCompanyById(id));
    }

    @PostMapping()
    public Company createCompany(@RequestBody CompanyCreateReq company) {
        return companyServiceImpl.save(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable(value = "id") Long id, @RequestBody Company company) throws ResourceNotFoundException {
        return ResponseEntity.ok(companyServiceImpl.update(id, company));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        companyServiceImpl.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // add company to user
    @PostMapping("/addtouser")
    public ResponseEntity<?> addCompanyToUser(Map<String, Object> req) throws ResourceNotFoundException {
        companyServiceImpl.addCompanyToUser((Long) req.get("user_id"), (Long) req.get("company_id"));
        Map<String, Boolean> response = new HashMap<>();
        response.put("added", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add/admin")
    public ResponseEntity<?> addAdminToCompany(@RequestBody Map<String, Long> req) throws ResourceNotFoundException {
//        companyServiceImpl.addCompanyToUser((Long) req.get("user_id"), (Long) req.get("company_id"));

        companyServiceImpl.addAdminToCompany(req.get("user_id"), req.get("company_id"));

        Map<String, Boolean> response = new HashMap<>();
        response.put("added", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
