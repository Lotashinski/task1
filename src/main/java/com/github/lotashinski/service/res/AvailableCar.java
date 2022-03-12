package com.github.lotashinski.service.res;

public interface AvailableCar {
    long getId();

    String getModel();

    String getRegistrationNumber();

    long getCostPerDay();

    long getCostPerPeriod();
}
