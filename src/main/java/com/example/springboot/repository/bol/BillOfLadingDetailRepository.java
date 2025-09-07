package com.example.springboot.repository.bol;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.entity.bol.BillOfLadingDetail;

import java.util.List;

public interface BillOfLadingDetailRepository extends JpaRepository<BillOfLadingDetail, Long> {
    List<BillOfLadingDetail> findByBolIdAndSerial(Long bolId, Long serial);

    List<BillOfLadingDetail> findByBolId(Long bolId);
}
