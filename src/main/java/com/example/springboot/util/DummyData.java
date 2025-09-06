package com.example.springboot.util;

import lombok.NoArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.example.springboot.service.bol.BillOfLadingService;
import com.example.springboot.service.hscode.HSCodeService;
import com.example.springboot.service.company.CompanyService;
import com.example.springboot.entity.company.Company;
import com.example.springboot.entity.hscode.HSCode;
import com.example.springboot.dto.bol.BillOfLadingDTORequest;
import com.example.springboot.dto.bol.BillOfLadingDTOResponse;
import com.example.springboot.entity.bol.BillOfLading;
import com.example.springboot.entity.bol.BillOfLadingDetail;

import java.util.Random;

/*
 * The dummy data class is going to add dummy data through the service as opposed to the service\
 * This is because we want it to go through the validation logic in the service layer as a test
 */
@NoArgsConstructor
@Configuration
@PropertySource("classpath:config.properties")
public class DummyData {

    @Value("${dummy-data.company}")
    private String companyDataString;

    @Value("${dummy-data.bol}")
    private String bolDataString;

    @Value("${dummy-data.hscode}")
    private String hscodeDataString;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private BillOfLadingService bolService;

    @Autowired
    private HSCodeService hscodeService;

    public void InsertAllData() {
        System.out.println("Inserting dummy data...");
        if ("yes".equalsIgnoreCase(companyDataString)) {
            InsertCompanyData();
        } else {
            System.out.println("Not inserting company dummy data...");
        }

        if ("yes".equalsIgnoreCase(bolDataString)) {
            InsertBolData();
        } else {
            System.out.println("Not inserting bol dummy data...");
        }

        if ("yes".equalsIgnoreCase(hscodeDataString)) {
            InsertHscodeData();
        } else {
            System.out.println("Not inserting hscode dummy data...");
        }
    }

    private void InsertCompanyData() {
        companyService.insertCompany(new Company("111-111-111", "First Company"), "POST /Company DUMMY_DATA");
        companyService.insertCompany(new Company("222-222-222", "Second Company"), "POST /Company DUMMY_DATA");
        companyService.insertCompany(new Company("333-333-333", "Third Company"), "POST /Company DUMMY_DATA");
        companyService.insertCompany(new Company("444-444-444", "Hambola United"), "POST /Company DUMMY_DATA");
        companyService.insertCompany(new Company("555-555-555", "Foobar Company"), "POST /Company DUMMY_DATA");
    }

    private void InsertHscodeData() {
        hscodeService.insertHSCode(new HSCode("Fruits", "0001"), "POST /HSCode DUMMY_DATA");
        hscodeService.insertHSCode(new HSCode("Medicine", "0010"), "POST /HSCode DUMMY_DATA");
        hscodeService.insertHSCode(new HSCode("Electronics", "0011"), "POST /HSCode DUMMY_DATA");
        hscodeService.insertHSCode(new HSCode("Machinery", "0100"), "POST /HSCode DUMMY_DATA");
        hscodeService.insertHSCode(new HSCode("Cars", "0101"), "POST /HSCode DUMMY_DATA");
        hscodeService.insertHSCode(new HSCode("Construction Materials", "0110"), "POST /HSCode DUMMY_DATA");
    }

    private void InsertBolData() {
        Random random = new Random();
        bolService.insertBill(new BillOfLadingDTORequest(Integer.toString(random.nextInt(100000000)), 1L),
                "POST /Bills DUMMY_DATA");
        bolService.insertBill(new BillOfLadingDTORequest(Integer.toString(random.nextInt(100000000)), 2L),
                "POST /Bills DUMMY_DATA");
        bolService.insertBill(new BillOfLadingDTORequest(Integer.toString(random.nextInt(100000000)), 3L),
                "POST /Bills DUMMY_DATA");
        bolService.insertBill(new BillOfLadingDTORequest(Integer.toString(random.nextInt(100000000)), 4L),
                "POST /Bills DUMMY_DATA");
        bolService.insertBill(new BillOfLadingDTORequest(Integer.toString(random.nextInt(100000000)), 5L),
                "POST /Bills DUMMY_DATA");
    }

}
