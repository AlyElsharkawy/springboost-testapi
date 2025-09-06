package com.example.springboot.repository.bol;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.entity.bol.BillOfLading;
import java.util.Optional;

public interface BillOfLadingDetailRepository extends JpaRepository<BillOfLading, Long> {

}
