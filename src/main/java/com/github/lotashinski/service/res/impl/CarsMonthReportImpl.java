package com.github.lotashinski.service.res.impl;

import com.github.lotashinski.service.res.CarsMonthReport;

import java.util.Map;

public class CarsMonthReportImpl implements CarsMonthReport {
    private double workload;
    private Map<String, Double>  workDaysPercentByCar;


    public void setWorkload(double workload) {
        this.workload = workload;
    }

    public void setWorkDaysPercentByCar(Map<String, Double> workDaysPercentByCar) {
        this.workDaysPercentByCar = workDaysPercentByCar;
    }

    @Override
    public double getWorkload() {
        return workload;
    }

    @Override
    public Map<String, Double> workDaysPercentByCar() {
        return workDaysPercentByCar;
    }
}
