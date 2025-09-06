package com.example.springboot.service.company;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.entity.company.Company;
import com.example.springboot.dto.company.CompanyDTOResponse;
import com.example.springboot.mapper.company.CompanyDTOResponseMapper;
import com.example.springboot.repository.company.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository repo;
    @Autowired
    private CompanyDTOResponseMapper dtoResponseMapper;

    public CompanyService(CompanyRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<List<CompanyDTOResponse>> getAllCompanies() {
        List<Company> companiesList = repo.findAll();
        List<CompanyDTOResponse> result = dtoResponseMapper.toDTOList(companiesList);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Long> insertCompany(Company input) {
        repo.save(input);
        return ResponseEntity.created(URI.create("/Company" + input.getId())).body(input.getId());
    }
}
