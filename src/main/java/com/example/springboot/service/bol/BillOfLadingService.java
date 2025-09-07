package com.example.springboot.service.bol;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

import com.example.springboot.entity.bol.BillOfLading;
import com.example.springboot.entity.company.Company;
import com.example.springboot.exception.BusinessLogicException;
import com.example.springboot.dto.bol.BillOfLadingDTOResponse;
import com.example.springboot.dto.bol.BillOfLadingDTORequest;
import com.example.springboot.mapper.bol.BillOfLadingResponseDTOMapper;
import com.example.springboot.repository.company.CompanyRepository;
import com.example.springboot.repository.bol.BillOfLadingRepository;
import com.example.springboot.repository.bol.BillOfLadingDetailRepository;

import java.net.URI;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillOfLadingService {
    private final BillOfLadingRepository bolRepo;
    private final CompanyRepository companyRepo;

    // Yes, this section was copied from CompanyService
    // Good job on noticing! You have sharp eyes!
    // Usually, this would be in its own separate class
    // However, I am on a deadline...so none of that now
    @Value("${error.database.general-error}")
    private String databaseErrorMessage;

    @Value("${error.database.not-found}")
    private String notFoundErrorMessage;

    @Value("${error.business-logic.uniqueness}")
    private String businessLogicErrorMessage;

    @Autowired
    private BillOfLadingResponseDTOMapper bolDtoResponseMapper;

    public BillOfLadingService(BillOfLadingRepository bolRepo,
            BillOfLadingDetailRepository bolDetailRepo,
            CompanyRepository companyRepo) {
        this.bolRepo = bolRepo;
        this.companyRepo = companyRepo;
    }

    public ResponseEntity<List<BillOfLadingDTOResponse>> getAllBills(String endpoint) {
        try {
            List<BillOfLading> temp = bolRepo.findAll();
            List<BillOfLadingDTOResponse> result = bolDtoResponseMapper.toDTOList(temp);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            String tempMessage = MessageFormat.format(databaseErrorMessage, endpoint, e.toString());
            System.err.println(tempMessage);
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<BillOfLadingDTOResponse> getBillById(Long id, String endpoint) {
        try {
            Optional<BillOfLading> temp = bolRepo.findById(id);
            if (temp.isPresent()) {
                BillOfLadingDTOResponse result = bolDtoResponseMapper.toDto(temp.get());
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

    public ResponseEntity<?> insertBill(BillOfLadingDTORequest bol, String endpoint) {
        try {
            Optional<BillOfLading> tempNbr = bolRepo.findByNbr(bol.getNbr());
            Optional<Company> tempCompany = companyRepo.findById(bol.getCompanyId());
            if (tempNbr.isPresent()) {
                String errorMessage = MessageFormat.format(businessLogicErrorMessage, endpoint);
                throw new BusinessLogicException(errorMessage);
            } else if (!tempCompany.isPresent()) {
                String errorMessage = MessageFormat
                        .format(notFoundErrorMessage, bol.getCompanyId(), endpoint);
                return ResponseEntity.badRequest().body(errorMessage);
            } else {
                BillOfLading result = new BillOfLading(bol.getNbr(), tempCompany.get());
                bolRepo.save(result);
                return ResponseEntity.created(URI.create("/Bills" +
                        result.getId())).body(result.getId());
            }
        } catch (BusinessLogicException ble) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ble.getMessage());
        } catch (Exception e) {
            String tempMessage = MessageFormat.format(databaseErrorMessage, endpoint, e.toString());
            System.err.println(tempMessage);
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<BillOfLading> deleteBillById(Long id, String endpoint) {
        try {
            Optional<BillOfLading> temp = bolRepo.findById(id);
            if (temp.isPresent()) {
                bolRepo.deleteById(id);
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

    public ResponseEntity<BillOfLadingDTOResponse> updateBillbyId(Long id, BillOfLadingDTORequest req,
            String endpoint) {
        try {
            Optional<BillOfLading> temp = bolRepo.findById(id);
            Optional<Company> tempCompany = companyRepo.findById(req.getCompanyId());
            System.out.println("bol id: " + Long.toString(id));
            System.out.println("company id: " + req.getCompanyId());
            if (temp.isPresent() && tempCompany.isPresent()) {
                temp = temp.map(existingBol -> {
                    existingBol.setNbr(req.getNbr());
                    existingBol.setCompany(tempCompany.get());
                    existingBol.setUpdateTimestamp(LocalDateTime.now());
                    return bolRepo.save(existingBol);
                });
                BillOfLadingDTOResponse result = bolDtoResponseMapper.toDto(temp.get());
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
