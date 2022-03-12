package com.github.lotashinski.service.res.impl;

import com.github.lotashinski.entity.CarEntity;

public final class AvailableCarImpl implements com.github.lotashinski.service.res.AvailableCar {
    private final CarEntity car;
    private final Long costPerPeriod;


    public AvailableCarImpl(CarEntity car, Long costPerPeriod) {
        this.car = car;
        this.costPerPeriod = costPerPeriod;
    }


    @Override
    public long getId() {
        return car.getId();
    }

    @Override
    public String getModel() {
        return car.getModel();
    }

    @Override
    public String getRegistrationNumber() {
        return car.getRegistrationNumber();
    }

    @Override
    public long getCostPerDay() {
        return car.getCostPerDay();
    }

    @Override
    public long getCostPerPeriod() {
        return costPerPeriod;
    }
}
