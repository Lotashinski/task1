package com.github.lotashinski.service.res;

import java.util.Map;

public interface CarsMonthReport {
    double getWorkload();

    Map<String, Double> workDaysPercentByCar();
}
