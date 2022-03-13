package com.github.lotashinski.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Basic
    @Column(nullable = false)
    private String model;

    @Basic
    @Column(nullable = false)
    private String registrationNumber;

    @Basic
    @Column(nullable = false)
    private Integer serviceDays = 1;

    @Basic
    private Long costPerDay;


    @OneToMany(mappedBy = "car")
    private List<CarSessionEntity> carsSessions = new ArrayList<>();


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

    public Integer getServiceDays() {
        return serviceDays;
    }

    public void setServiceDays(Integer serviceDays) {
        this.serviceDays = serviceDays;
    }

    public List<CarSessionEntity> getCarsSessions() {
        return carsSessions;
    }

    public void setCarsSessions(List<CarSessionEntity> cars) {
        this.carsSessions = cars;
    }

    public Long getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(Long costPerDay) {
        this.costPerDay = costPerDay;
    }
}
