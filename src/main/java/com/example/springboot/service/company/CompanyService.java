package com.example.springboot.service.company;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

import com.example.springboot.entity.company.Company;
import com.example.springboot.dto.company.CompanyDTOResponse;
import com.example.springboot.mapper.company.CompanyDTOResponseMapper;
import com.example.springboot.repository.company.CompanyRepository;
import com.example.springboot.exception.BusinessLogicException;

import java.net.URI;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Configuration
@PropertySource("classpath:error-messages.properties")
@Service
public class CompanyService {
    private final CompanyRepository repo;
    @Autowired
    private CompanyDTOResponseMapper dtoResponseMapper;

    // Usually, this would be in its own separate class
    // However, I am on a deadline...so none of that now
    @Value("${error.database.general-error}")
    private String databaseErrorMessage;

    @Value("${error.database.not-found}")
    private String notFoundErrorMessage;

    @Value("${error.business-logic.uniqueness}")
    private String businessLogicErrorMessage;

    public CompanyService(CompanyRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<List<CompanyDTOResponse>> getAllCompanies(String endpoint) {
        try {
            List<Company> companiesList = repo.findAll();
            List<CompanyDTOResponse> result = dtoResponseMapper.toDTOList(companiesList);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            String tempMessage = MessageFormat.format(databaseErrorMessage, endpoint, e.toString());
            System.err.println(tempMessage);
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<CompanyDTOResponse> getCompanyById(Long id, String endpoint) {
        try {
            Optional<Company> tempCompany = repo.findById(id);
            if (tempCompany.isPresent()) {
                CompanyDTOResponse result = dtoResponseMapper.toDTO(tempCompany.get());
                return ResponseEntity.ok(result);
            } else {
                String errorMessage = MessageFormat.format(notFoundErrorMessage, id, endpoint);
                System.err.println(errorMessage);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            String tempMessage = MessageFormat.format(databaseErrorMessage, endpoint, e.toString());
            System.err.println(tempMessage);
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> insertCompany(Company input, String endpoint) {
        try {
            // First make sure we are not violating any business Logic
            Optional<Company> tempCompanyName = repo.findByName(input.getName());
            Optional<Company> tempCompanyTaxNumber = repo.findByTaxNumber(input.getTaxNumber());
            if (tempCompanyName.isPresent() || tempCompanyTaxNumber.isPresent()) {
                String errorMessage = MessageFormat.format(businessLogicErrorMessage, endpoint);
                System.err.println(errorMessage);
                throw new BusinessLogicException(errorMessage);
            }
            repo.save(input);
            return ResponseEntity.created(URI.create("/Company" + input.getId())).body(input.getId());
        } catch (BusinessLogicException ble) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ble.getMessage());
        } catch (Exception e) {
            String tempMessage = MessageFormat.format(databaseErrorMessage, endpoint, e.toString());
            System.err.println(tempMessage);
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Company> deleteCompanyByID(Long id, String endpoint) {
        try {
            Optional<Company> temp = repo.findById(id);
            if (temp.isPresent()) {
                repo.deleteById(temp.get().getId());
                return ResponseEntity.noContent().build();
            } else {
                String errorMessage = MessageFormat.format(notFoundErrorMessage, id, endpoint);
                System.err.println(errorMessage);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            String tempMessage = MessageFormat.format(databaseErrorMessage, endpoint, e.toString());
            System.err.println(tempMessage);
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<CompanyDTOResponse> updateCompany(Long id, Company newCompany, String endpoint) {
        try {
            Optional<Company> temp = repo.findById(id);
            if (temp.isPresent()) {
                temp = temp.map(existingCompany -> {
                    existingCompany.setName(newCompany.getName());
                    existingCompany.setTaxNumber(newCompany.getTaxNumber());
                    existingCompany.setUpdateTimestamp(LocalDateTime.now());
                    return repo.save(existingCompany);
                });
                CompanyDTOResponse result = dtoResponseMapper.toDTO(temp.get());
                return ResponseEntity.ok(result);
            } else {
                String errorMessage = MessageFormat.format(notFoundErrorMessage, id, endpoint);
                System.err.println(errorMessage);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            String tempMessage = MessageFormat.format(databaseErrorMessage, endpoint, e.toString());
            System.err.println(tempMessage);
            return ResponseEntity.internalServerError().build();
        }
    }
}
