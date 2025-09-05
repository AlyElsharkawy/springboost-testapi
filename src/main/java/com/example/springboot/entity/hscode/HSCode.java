package com.example.springboot.entity.hscode;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;

@Entity
public class HSCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime timeStamp;

    @PrePersist
    protected void onCreate() {
        this.timeStamp = LocalDateTime.now();
    }

    public HSCode() {
    }

    public HSCode(Long Id, String code, String name, LocalDateTime timeStamp) {
        this.id = Id;
        this.code = code;
        this.name = name;
        this.timeStamp = timeStamp;
    }

    public HSCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // Getters
    public Long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    // Setters
    public void setId(Long Id) {
        this.id = Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.code, this.name, this.timeStamp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        HSCode that = (HSCode) o;
        return Objects.equals(that.id, this.id) &&
                Objects.equals(that.name, this.name) &&
                Objects.equals(that.code, this.code) &&
                Objects.equals(that.timeStamp, this.timeStamp);
    }
}
