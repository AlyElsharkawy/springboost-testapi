package com.example.springboot.controller.bol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping
    public ResponseEntity<Long> insertBillDetail(@RequestBody BillOfLadingDetailFullRequestDTO req) {
        return bolDetailService.createBillDetail(req, "POST /Bills/Detail/{id}");
    }

}
