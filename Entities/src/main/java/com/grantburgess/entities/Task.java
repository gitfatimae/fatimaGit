package com.grantburgess.entities;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Task {
    private  UUID id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancelDate;

    public Task() {

    }


    public enum Status {
        ACTIVE, EXPIRED, CANCELLED
    }

    public Task(String name, String description, LocalDate startDate, LocalDate endDate) {
        this(UUID.randomUUID(), name, description, startDate, endDate,null);
    }

    public Task(UUID id, String name, String description, LocalDate startDate, LocalDate endDate,LocalDate cancelDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancelDate = cancelDate;

    }

    public void cancel(LocalDate cancelDate) {
        this.cancelDate = cancelDate;
    }

    public void update(String name, String description, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
