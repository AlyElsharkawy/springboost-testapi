package com.example.springboot.entity.company;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String taxNumber; // Don't forget the dashes 123-456-789

    // In real life, there are a lot of companies with identical names, but I don't
    // think that makes sense in this case
    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @Column
    private LocalDateTime updateTimestamp;

    @PrePersist
    protected void onCreate() {
        this.creationTimestamp = LocalDateTime.now();
    }

    public Company(Long id, String taxNumber, String name) {
        this.id = id;
        this.taxNumber = taxNumber;
        this.name = name;
    }

    public Company(String taxNumber, String name) {
        this.taxNumber = taxNumber;
        this.name = name;
    }

    // These getters were added so that my LSP could shut up
    // I use neovim as my IDE and I am too lazy to configure the LSP manually
    // So yeah...I added these getters and setters instead
    public String getName() {
        return this.name;
    }

    public String getTaxNumber() {
        return this.taxNumber;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public void setUpdateTimestamp(LocalDateTime timestamp) {
        this.updateTimestamp = timestamp;
    }
}
