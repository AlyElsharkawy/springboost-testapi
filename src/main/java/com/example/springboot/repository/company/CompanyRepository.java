package com.example.springboot.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.entity.company.Company;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
