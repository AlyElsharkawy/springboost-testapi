package com.example.springboot.controller.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entity.company.Company;
import com.example.springboot.dto.company.CompanyDTOResponse;
import com.example.springboot.service.company.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/Company")
public class CompanyController {
    @Autowired
    private CompanyService serv;

    @GetMapping
    public ResponseEntity<List<CompanyDTOResponse>> getAllCompanies() {
        return serv.getAllCompanies("GET /Company");
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyDTOResponse> getCompanyById(@PathVariable Long id) {
        return serv.getCompanyById(id, "GET /Company/{id}");
    }

    @PostMapping
    public ResponseEntity<?> insertCompany(@RequestBody Company company) {
        return serv.insertCompany(company, "POST /Company");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable Long id) {
        return serv.deleteCompanyByID(id, "DELETE /Company/{id}");
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyDTOResponse> updateCompanyById(@PathVariable Long id, @RequestBody Company company) {
        return serv.updateCompany(id, company, "PUT /Company/{id}");
    }
}
