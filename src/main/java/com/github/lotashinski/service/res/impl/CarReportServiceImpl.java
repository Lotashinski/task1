package com.github.lotashinski.service.res.impl;

import com.github.lotashinski.entity.CarEntity;
import com.github.lotashinski.entity.CarSessionEntity;
import com.github.lotashinski.repository.CarRepository;
import com.github.lotashinski.repository.CarSessionRepository;
import com.github.lotashinski.repository.RepositoryFactory;
import com.github.lotashinski.service.res.CarRepostService;
import com.github.lotashinski.service.res.CarsMonthReport;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CarReportServiceImpl implements CarRepostService {
    private final RepositoryFactory repositoryFactory;
    private CarSessionRepository carSessionRepository;
    private CarRepository carRepository;


    public CarReportServiceImpl(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    public CarsMonthReport configureReportByAll(LocalDate startAt, LocalDate endAt) {
        List<CarSessionEntity> sessions = getCarSessionRepository().loadByPeriod(startAt, endAt);
        Map<String, Double> carReport = new HashMap<>();

        for (CarSessionEntity session : sessions) {
            CarEntity car = session.getCar();
            String registrationNumber = car.getRegistrationNumber();
            double days = daysInPeriod(session, startAt, endAt);

            if (carReport.containsKey(registrationNumber)) {
                double currentValue = carReport.get(registrationNumber);
                currentValue += days;
                carReport.put(registrationNumber, currentValue);
            } else {
                carReport.put(registrationNumber, days);
            }
        }

        long allCarsCount = getCarRepository().count();
        int periodDays = (int) ChronoUnit.DAYS.between(startAt, endAt);

        long availablePeriodWorkDays = allCarsCount * periodDays;
        Double allWorkDays = carReport.values()
                .stream()
                .reduce(.0, Double::sum);

        CarsMonthReportImpl report = new CarsMonthReportImpl();
        report.setWorkDaysPercentByCar(carReport);
        report.setWorkload(allWorkDays / availablePeriodWorkDays);

        double days = ChronoUnit.DAYS.between(startAt, endAt);
        carReport = carReport.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() / days));
        report.setWorkDaysPercentByCar(carReport);

        return report;
    }


    private CarRepository getCarRepository() {
        if (null == carRepository) {
            carRepository = repositoryFactory.getCarRepository();
        }
        return carRepository;
    }

    private CarSessionRepository getCarSessionRepository() {
        if (null == carSessionRepository) {
            carSessionRepository = repositoryFactory.getCarServiceRepository();
        }
        return carSessionRepository;
    }

    private static double daysInPeriod(CarSessionEntity session, LocalDate startAt, LocalDate endAt) {
        LocalDate sessionStartAt = session.getStartAt();
        LocalDate sessionEndAt = session.getEndAt();

        LocalDate startPeriod;
        int compareStartPeriod = sessionStartAt.compareTo(startAt);
        if (compareStartPeriod > 0) {
            startPeriod = startAt;
        } else {
            startPeriod = sessionStartAt;
        }

        LocalDate endPeriod;
        int compareEndPeriod = sessionEndAt.compareTo(endAt);
        if (compareEndPeriod >= 0) {
            endPeriod = endAt;
        } else {
            endPeriod = sessionEndAt;
        }

        return ChronoUnit.DAYS.between(startPeriod, endPeriod);
    }
}
