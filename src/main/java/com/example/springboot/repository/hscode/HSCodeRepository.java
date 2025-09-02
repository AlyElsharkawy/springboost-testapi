package com.example.springboot.repository.hscode;

import com.example.springboot.entity.hscode.HSCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HSCodeRepository extends JpaRepository<HSCode, Long> {
  Optional<HSCode> findByCode(String code);

  Optional<HSCode> findByName(String name);
}
