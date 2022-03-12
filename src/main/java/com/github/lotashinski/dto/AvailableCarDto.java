package com.github.lotashinski.dto;

public final class AvailableCarDto {
    private long id;
    private String model;
    private String registrationNumber;
    private long costPerDay;
    private long costPerPeriod;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public long getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(long costPerDay) {
        this.costPerDay = costPerDay;
    }

    public long getCostPerPeriod() {
        return costPerPeriod;
    }

    public void setCostPerPeriod(long costPerPeriod) {
        this.costPerPeriod = costPerPeriod;
    }
}
