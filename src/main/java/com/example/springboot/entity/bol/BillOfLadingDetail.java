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

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private int count;

    @Column
    private int volume;

    @PrePersist
    protected void onCreate() {
        this.creationTimestamp = LocalDateTime.now();
    }

    @Column(unique = true, nullable = false)
    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @Column
    private LocalDateTime updateTimestamp;
}
