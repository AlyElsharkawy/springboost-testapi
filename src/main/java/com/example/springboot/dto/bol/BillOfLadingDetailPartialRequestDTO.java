package com.example.springboot.dto.bol;

import java.math.BigDecimal;

public class BillOfLadingDetailPartialRequestDTO {
    BigDecimal weight;
    Long count;
    BigDecimal volume;

    public BillOfLadingDetailPartialRequestDTO() {
    }

    public BillOfLadingDetailPartialRequestDTO(BigDecimal weight, Long count, BigDecimal volume) {
        this.weight = weight;
        this.count = count;
        this.volume = volume;
    }

    // Getters
    public BigDecimal getWeight() {
        return this.weight;
    }

    public BigDecimal getVolume() {
        return this.volume;
    }

    public Long getCount() {
        return this.count;
    }

    // Setters
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
