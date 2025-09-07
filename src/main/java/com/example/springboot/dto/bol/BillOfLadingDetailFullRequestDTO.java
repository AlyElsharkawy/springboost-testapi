package com.example.springboot.dto.bol;

import java.math.BigDecimal;

public class BillOfLadingDetailFullRequestDTO {
    Long serial;
    Long hscodeId;
    Long bolId;
    BigDecimal weight;
    Long count;
    BigDecimal volume;

    public BillOfLadingDetailFullRequestDTO(Long serial, Long hscodeId, Long bolId,
            BigDecimal weight, Long count, BigDecimal volume) {
        this.serial = serial;
        this.hscodeId = hscodeId;
        this.bolId = bolId;
        this.weight = weight;
        this.count = count;
        this.volume = volume;
    }

    // Getters
    public Long getSerial() {
        return this.serial;
    }

    public Long getHscodeId() {
        return this.hscodeId;
    }

    public Long getBolId() {
        return this.bolId;
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

    // Setters
    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public void setHscode(Long hscodeId) {
        this.hscodeId = hscodeId;
    }

    public void setBol(Long bolId) {
        this.bolId = bolId;
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
