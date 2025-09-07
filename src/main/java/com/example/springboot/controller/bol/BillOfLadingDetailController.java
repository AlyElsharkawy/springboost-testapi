package com.example.springboot.controller.bol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.springboot.service.bol.BillOfLadingDetailService;
import com.example.springboot.entity.bol.BillOfLadingDetail;
import com.example.springboot.dto.bol.BillOfLadingDetailDTOResponse;
import com.example.springboot.dto.bol.BillOfLadingDetailFullRequestDTO;
import com.example.springboot.dto.bol.BillOfLadingDetailPartialRequestDTO;

import java.util.List;

@RestController
@RequestMapping("/Bills/Detail")
public class BillOfLadingDetailController {

    @Autowired
    private BillOfLadingDetailService bolDetailService;

    @GetMapping
    public ResponseEntity<List<BillOfLadingDetailDTOResponse>> getAllBillDetails() {
        return bolDetailService.getAllBillDetails("GET /Bills/Detail");
    }

    @GetMapping("{id}")
    public ResponseEntity<BillOfLadingDetailDTOResponse> getBillDetail(@PathVariable Long id,
            String endpoint) {
        return bolDetailService.getBillDetail(id, "GET /Bills/Detail/{id}");
    }

    @PostMapping
    public ResponseEntity<?> insertBillDetail(@RequestBody BillOfLadingDetailFullRequestDTO req) {
        return bolDetailService.createBillDetail(req, "POST /Bills/Detail/{id}");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BillOfLadingDetail> deleteBillDetail(@PathVariable Long id) {
        return bolDetailService.deleteBillDetail(id, "DELETE /Bills/Detail/{id}");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBillDetail(
            @PathVariable Long id,
            @RequestBody BillOfLadingDetailFullRequestDTO req) {
        return bolDetailService.updateBillDetail(id, req, "DELETE /Bills/Detail/{id}");
    }

    @PatchMapping("{id}")
    public ResponseEntity<BillOfLadingDetailDTOResponse> changeWeightInfo(
            @PathVariable Long id,
            @RequestBody BillOfLadingDetailPartialRequestDTO req) {
        return bolDetailService.updateBillInfo(id, req, "PATCH /Bills/Detail/{id}");
    }

}
