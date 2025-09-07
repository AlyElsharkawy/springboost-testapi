package com.example.springboot.service.bol;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.springboot.repository.bol.BillOfLadingDetailRepository;
import com.example.springboot.repository.bol.BillOfLadingRepository;
import com.example.springboot.repository.hscode.HSCodeRepository;
import com.example.springboot.entity.bol.BillOfLadingDetail;
import com.example.springboot.entity.hscode.HSCode;
import com.example.springboot.entity.bol.BillOfLading;
import com.example.springboot.dto.bol.BillOfLadingDetailDTOResponse;
import com.example.springboot.mapper.bol.BillOfLadingDetailResponseMapper;
import com.example.springboot.dto.bol.BillOfLadingDetailFullRequestDTO;
import com.example.springboot.exception.BusinessLogicException;

import java.net.URI;
import java.util.List;
import java.text.MessageFormat;
import java.util.Optional;

@Configuration
@PropertySource("classpath:error-messages.properties")
@Service
public class BillOfLadingDetailService {
    private final BillOfLadingDetailRepository bolDetailRepo;
    private final BillOfLadingRepository bolRepo;
    private final HSCodeRepository hscodeRepo;

    @Autowired
    BillOfLadingDetailResponseMapper bolDetailMapper;

    // Yes, this section was copied from BillOfLadingService
    // Good job on noticing! You have sharp eyes!
    // Usually, this would be in its own separate class
    // However, I am on a deadline...so none of that now
    @Value("${error.database.general-error}")
    private String databaseErrorMessage;

    @Value("${error.database.not-found}")
    private String notFoundErrorMessage;

    @Value("${error.business-logic.uniqueness}")
    private String businessLogicErrorMessage;

    public BillOfLadingDetailService(BillOfLadingDetailRepository bolDetailRepo,
            BillOfLadingRepository bolRepo,
            HSCodeRepository hscodeRepo) {
        this.bolDetailRepo = bolDetailRepo;
        this.hscodeRepo = hscodeRepo;
        this.bolRepo = bolRepo;
    }

    private BillOfLadingDetail ConvertResponseToFullObject(BillOfLadingDetailFullRequestDTO input) {
        BillOfLadingDetail result = new BillOfLadingDetail();
        result.setCount(input.getCount());
        result.setVolume(input.getVolume());
        result.setWeight(input.getWeight());
        result.setSerial(input.getSerial());
        try {
            Optional<HSCode> tempHscode = hscodeRepo.findById(input.getHscodeId());
            Optional<BillOfLading> tempBol = bolRepo.findById(input.getBolId());
            if (tempHscode.isEmpty()) {
                String errorMessage = MessageFormat
                        .format(notFoundErrorMessage, input.getHscodeId(), "HELPER_FUNCTION_HSCODE");
                System.err.println(errorMessage);
                return null;
            } else if (tempBol.isEmpty()) {
                String errorMessage = MessageFormat
                        .format(notFoundErrorMessage, input.getBolId(), "HELPER_FUNCTION_BOL");
                System.err.println(errorMessage);
                return null;
            } else {
                result.setHscode(tempHscode.get());
                result.setBol(tempBol.get());
                return result;
            }
        } catch (Exception e) {
            String tempMessage = MessageFormat.format(databaseErrorMessage, "HELPER_FUNCTION", e.toString());
            System.err.println(tempMessage);
            return null;
        }
    }

    public ResponseEntity<List<BillOfLadingDetailDTOResponse>> getAllBillDetails(String endpoint) {
        try {
            List<BillOfLadingDetail> temp = bolDetailRepo.findAll();
            List<BillOfLadingDetailDTOResponse> result = bolDetailMapper.toDTOList(temp);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            String tempMessage = MessageFormat.format(databaseErrorMessage, endpoint,
                    e.toString());
            System.err.println(tempMessage);
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Long> createBillDetail(BillOfLadingDetailFullRequestDTO req,
            String endpoint) {
        try {
            // TODO: SPECIFICATION VALIDATION TO ENSURE UNIQUENESS WITHIN SERIAL
            BillOfLadingDetail temp = ConvertResponseToFullObject(req);
            if (temp != null) {
                bolDetailRepo.save(temp);
                return ResponseEntity.created(URI.create("/Bills/Details" + temp.getId())).body(temp.getId());
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            String tempMessage = MessageFormat.format(databaseErrorMessage, endpoint, e.toString());
            System.err.println(tempMessage);
            return ResponseEntity.internalServerError().build();
        }
    }
}
