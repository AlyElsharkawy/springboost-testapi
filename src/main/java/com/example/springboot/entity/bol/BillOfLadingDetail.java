package com.example.springboot.entity.bol;

import com.example.springboot.entity.hscode.HSCode;

import java.math.BigDecimal;
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

@Entity
public class BillOfLadingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This is the partial identifier of the number of the item in the shipment
    @Column
    private Long serial;

    @ManyToOne
    @JoinColumn(name = "hscode_id")
    private HSCode hscode;

    @ManyToOne
    @JoinColumn(name = "billoflanding_id")
    private BillOfLading bol;

    @Column
    private BigDecimal weight;

    @Column
    private Long count;

    @Column
    private BigDecimal volume;

    @PrePersist
    protected void onCreate() {
        this.creationTimestamp = LocalDateTime.now();
    }

    @Column(unique = true, nullable = false)
    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @Column
    private LocalDateTime updateTimestamp;

    // Constructor
    public BillOfLadingDetail(Long serial, HSCode hscode, BillOfLading bol,
            BigDecimal weight, Long count, BigDecimal volume) {
        this.serial = serial;
        this.hscode = hscode;
        this.bol = bol;
        this.count = count;
        this.volume = volume;
        this.weight = weight;
    }

    public BillOfLadingDetail() {
    }

    // Getters
    public Long getId() {
        return this.id;
    }

    public Long getSerial() {
        return this.serial;
    }

    public LocalDateTime getCreationTimestamp() {
        return this.creationTimestamp;
    }

    public HSCode getHscode() {
        return this.hscode;
    }

    public BigDecimal getWeight() {
        return this.weight;
    }

    public BigDecimal getVolume() {
        return this.volume;
    }

    public Long getCount() {
        return this.count;
    }

    public BillOfLading getBol() {
        return this.bol;
    }

    // Setters
    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public void setUpdateTimestamp(LocalDateTime timestamp) {
        this.updateTimestamp = timestamp;
    }

    public void setHscode(HSCode hscode) {
        this.hscode = hscode;
    }

    public void setBol(BillOfLading bol) {
        this.bol = bol;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
