package com.example.springboot.service.hscode;

import com.example.springboot.repository.hscode.HSCodeRepository;
import com.example.springboot.entity.hscode.HSCode;
import com.example.springboot.dto.hscode.HSCodeMinimal;
import com.example.springboot.mapper.hscode.HSCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class HSCodeService {
  private final HSCodeRepository repo;
  @Autowired
  private HSCodeMapper hsCodeMapper;

  public HSCodeService(HSCodeRepository repo) {
    this.repo = repo;
  }

  public List<HSCode> getAllHSCodes() {
    return repo.findAll();
  }

  public ResponseEntity<HSCode> getHSCodeById(Long id) {
    // return repo.findById(id).orElseThrow(() -> new RuntimeException("HSCode not
    // found"));
    Optional<HSCode> temp;
    try {
      temp = repo.findById(id);
    } catch (Exception e) {
      System.out.println("DATABASE ERROR");
      return ResponseEntity.internalServerError().build();
    }
    if (temp.isPresent())
      return ResponseEntity.ok(temp.get());
    else
      return ResponseEntity.notFound().build();
  }

  public ResponseEntity<HSCodeMinimal> getHSCodeByIdMinimal(Long id) {
    // return repo.findById(id).orElseThrow(() -> new RuntimeException("HSCode not
    // found"));
    Optional<HSCode> temp;
    try {
      temp = repo.findById(id);
    } catch (Exception e) {
      System.out.println("DATABASE ERROR");
      return ResponseEntity.internalServerError().build();
    }
    if (temp.isPresent()) {
      HSCodeMinimal tempDto = hsCodeMapper.toDTO(temp.get());
      System.out.println("Moment of truth: ");
      // System.out.println("Name: " + tempDto.getName());
      return ResponseEntity.ok(tempDto);
    } else
      return ResponseEntity.notFound().build();
  }

  public ResponseEntity<Long> insertHSCode(HSCode input) {
    try {
      if (repo.findByCode(input.getCode()).isPresent() || repo.findByName(input.getName()).isPresent()) {
        // Magic numbers are bad...is there another way that doesn't involve
        // ResponseEntity<?>...?
        return ResponseEntity.status(HttpStatus.CONFLICT).body(-1L);
      }
      repo.save(input);
      return ResponseEntity.created(URI.create("/HSCodes" + input.getId())).body(input.getId());
    } catch (Exception e) {
      System.out.println("DATABASE ERROR");
      return ResponseEntity.internalServerError().build();
    }
  }

  // Delete in a transaction
  @Transactional
  public ResponseEntity<HSCode> deleteHSCodeById(Long id) {
    try {
      repo.deleteById(id);
    } catch (Exception e) {
      System.out.println("DATABASE ERROR");
      return ResponseEntity.internalServerError().build();
    }
    return ResponseEntity.noContent().build();
  }

  public ResponseEntity<HSCode> updateHSCode(Long id, HSCode newHSCode) {
    Optional<HSCode> temp;
    try {
      temp = repo.findById(id)
          .map(existingHSCode -> {
            existingHSCode.setCode(newHSCode.getCode());
            existingHSCode.setName(newHSCode.getName());
            existingHSCode.setTimeStamp(LocalDateTime.now()); // optional update
            return repo.save(existingHSCode);
          });
    } catch (Exception e) {
      System.out.println("DATABASE ERROR");
      return ResponseEntity.internalServerError().build();
    }
    // Lets do a DTO mapping to remove the ID and timestamp attributes
    return ResponseEntity.ok().body(temp.get());
  }
}
