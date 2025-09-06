package com.example.springboot.controller.bol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.springboot.dto.bol.BillOfLadingDTOResponse;
import com.example.springboot.dto.bol.BillOfLadingDTORequest;
import com.example.springboot.entity.bol.BillOfLading;
import com.example.springboot.entity.bol.BillOfLadingDetail;
import com.example.springboot.entity.company.Company;
import com.example.springboot.mapper.bol.BillOfLadingResponseDTOMapper;
import com.example.springboot.service.bol.BillOfLadingService;

import java.util.List;

@RestController
@RequestMapping("/Bills")
public class BillOfLadingController {

    @Autowired
    private BillOfLadingResponseDTOMapper bolResponseDTOMapper;

    @Autowired
    private BillOfLadingService serv;

    @GetMapping
    public ResponseEntity<List<BillOfLadingDTOResponse>> getAllBills() {
        return serv.getAllBills("GET /Bills");
    }

    @GetMapping("{id}")
    public ResponseEntity<BillOfLadingDTOResponse> getBillById(@PathVariable Long id) {
        return serv.getBillById(id, "GET /Bills/{id}");
    }

    @PostMapping
    public ResponseEntity<?> insertBill(@RequestBody BillOfLadingDTORequest bol) {
        return serv.insertBill(bol, "POST /Bills");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BillOfLading> deleteBill(@PathVariable Long id) {
        return serv.deleteBillById(id, "DELETE /Bills/{id}");
    }

    @PutMapping("{id}")
    public ResponseEntity<BillOfLadingDTOResponse> updateBill(@PathVariable Long id,
            @RequestBody BillOfLadingDTORequest req) {
        return serv.updateBillbyId(id, req, "PUT /Bills/{id}");
    }
}
