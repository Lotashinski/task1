package com.github.lotashinski.dto;

public final class CarDto {
    private Long id;
    private String model;
    private String registrationNumber;
    private Long costPerDay;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(Long costPerDay) {
        this.costPerDay = costPerDay;
    }
}
