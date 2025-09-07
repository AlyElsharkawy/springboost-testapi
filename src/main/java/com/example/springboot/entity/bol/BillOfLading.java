package com.example.springboot.entity.bol;

import com.example.springboot.entity.company.Company;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
public class BillOfLading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nbr;

    @Column(unique = true, nullable = false)
    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @Column
    private LocalDateTime updateTimestamp;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @PrePersist
    protected void onCreate() {
        this.creationTimestamp = LocalDateTime.now();
    }

    public BillOfLading() {
    }

    // These getters were added so that my LSP could shut up
    // I use neovim as my IDE and I am too lazy to configure the LSP manually
    // So yeah...I added these getters instead
    public Long getId() {
        return this.id;
    }

    public String getNbr() {
        return this.nbr;
    }

    public Company getCompany() {
        return this.company;
    }

    public LocalDateTime getCreationTimestamp() {
        return this.creationTimestamp;
    }

    public LocalDateTime getUpdateTimestamp() {
        return this.updateTimestamp;
    }

    public void setNbr(String nbr) {
        this.nbr = nbr;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setUpdateTimestamp(LocalDateTime timestamp) {
        this.updateTimestamp = timestamp;
    }

    public BillOfLading(String nbr, Company company) {
        this.nbr = nbr;
        this.company = company;
    }

}
