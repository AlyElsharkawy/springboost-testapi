package com.example.springboot.entity.hscode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

//@Data
//@NoArgsConstructor // Default constructor (required for JPA)
//@AllArgsConstructor // Constructor with all fields
public class HSCodeMinimal {
  private String name;
  private String code;

  // Add these manually to be sure
  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
