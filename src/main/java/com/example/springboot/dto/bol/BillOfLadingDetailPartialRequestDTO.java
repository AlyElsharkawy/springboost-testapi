package com.example.springboot.dto.bol;

import java.math.BigDecimal;

public class BillOfLadingDetailPartialRequestDTO {
    BigDecimal weight;
    Long count;
    BigDecimal volume;

    // Getters
    private BigDecimal getWeight() {
        return this.weight;
    }

    private BigDecimal getVolume() {
        return this.volume;
    }

    private Long getCount() {
        return this.count;
    }

    // Setters
    private void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    private void setCount(Long count) {
        this.count = count;
    }

    private void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
