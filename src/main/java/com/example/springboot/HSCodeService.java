package com.example.springboot;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class HSCodeService {
  private final HSCodeRepository repo;

  public HSCodeService(HSCodeRepository repo) {
    this.repo = repo;
  }

  public List<HSCode> getAllHSCodes() {
    return repo.findAll();
  }

  public HSCode getHSCodeById(Long id) {
    return repo.findById(id).orElseThrow(() -> new RuntimeException("HSCode not found"));
  }

  public void insertHSCode(HSCode input) {
    if (repo.findByCode(input.getCode()).isPresent()) {
      throw new IllegalArgumentException("HSCode with Code " + input.getId() +
          " already exists.");
    }
    if (repo.findByName(input.getCode()).isPresent()) {
      throw new IllegalArgumentException("HSCode with Name " + input.getId() +
          " already exists.");
    }
    repo.save(input);
  }

  // Delete in a transaction
  @Transactional
  public void deleteHSCodeById(Long id) {
    repo.deleteById(id);
  }

  public HSCode updateHSCode(Long id, HSCode newHSCode) {
    HSCode temp = repo.findById(id)
        .map(existingHSCode -> {
          existingHSCode.setCode(newHSCode.getCode());
          existingHSCode.setName(newHSCode.getName());
          existingHSCode.setTimeStamp(LocalDateTime.now()); // optional update
          return repo.save(existingHSCode);
        })
        .orElseThrow(() -> new RuntimeException("HSCode with ID " + id + " not found"));
    return temp;
  }
}
