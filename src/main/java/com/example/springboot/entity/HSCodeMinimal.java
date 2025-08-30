package com.example.springboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor // Default constructor (required for JPA)
@AllArgsConstructor // Constructor with all fields
public class HSCodeMinimal {
  String name;
  String code;
}
