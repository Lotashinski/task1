package com.github.lotashinski.service.res.impl;

import com.github.lotashinski.entity.CarEntity;
import com.github.lotashinski.entity.CarSessionEntity;
import com.github.lotashinski.repository.CarRepository;
import com.github.lotashinski.repository.CarSessionRepository;
import com.github.lotashinski.repository.RepositoryFactory;
import com.github.lotashinski.repository.exception.BookException;
import com.github.lotashinski.repository.exception.NotFoundException;
import com.github.lotashinski.service.exception.LogicException;
import com.github.lotashinski.service.res.AvailableCar;
import com.github.lotashinski.service.res.AvailableCarSet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public final class CarServiceImpl implements com.github.lotashinski.service.res.CarService {
    private final RepositoryFactory repositoryFactory;
    private CarRepository carRepository;
    private CarSessionRepository carSessionRepository;


    public CarServiceImpl(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }


    @Override
    public CarSetImpl getPaginated(int limit, int offset) {
        CarRepository carRepository = getCarRepository();
        List<CarEntity> cars = carRepository.paginated(limit, offset);
        long allCarsCount = carRepository.count();

        CarSetImpl paginatedCarSet = new CarSetImpl();
        paginatedCarSet.setLimit(limit);
        paginatedCarSet.setOffset(offset);
        paginatedCarSet.setAllCount(allCarsCount);
        paginatedCarSet.setCars(cars);
        paginatedCarSet.setCount(cars.size());

        return paginatedCarSet;
    }

    @Override
    public AvailableCarSet getAvailablePaginated(LocalDate startAt, LocalDate endAt, int limit, int offset) {
        CarRepository carRepository = getCarRepository();
        List<CarEntity> cars = carRepository.getAvailablePerDateTime(startAt, endAt, limit, offset);
        int days = (int) ChronoUnit.DAYS.between(startAt, endAt) + 1;
        if (days < 1) {
            throw new LogicException("End date is less than start date");
        }

        long availableCarsCount = carRepository.availableCount(startAt, endAt);
        List<AvailableCar> availableCars = cars.stream()
                .map(car -> calculatePeriodPrice(car, days))
                .collect(Collectors.toList());

        return new AvailableCarSetImpl(limit, offset, availableCarsCount, availableCars);
    }

    @Override
    public CarEntity getById(long id) throws NotFoundException {
        CarRepository carRepository = getCarRepository();
        return carRepository.getById(id).orElseThrow(() -> new NotFoundException("Car not found."));
    }

    @Override
    public CarSessionEntity bookCar(CarSessionEntity carSession) {
        CarRepository carRepository = getCarRepository();

        CarEntity car = carSession.getCar();
        LocalDate sessionEndAt = carSession.getEndAt();
        LocalDate sessionStartAt = carSession.getStartAt();
        boolean isAvailable = carRepository.isAvailable(car, sessionStartAt, sessionEndAt);
        if (!isAvailable){
            throw new BookException("Car is not available");
        }

        int serviceDays = car.getServiceDays();
        LocalDate serviceEnd = sessionEndAt.plusDays(serviceDays);
        carSession.setServiceEnd(serviceEnd);

        int days = (int) ChronoUnit.DAYS.between(carSession.getStartAt(), carSession.getEndAt()) + 1;
        long price = calculateCost(car.getCostPerDay(), days);
        carSession.setPrice(price);

        CarSessionRepository carSessionRepository = getCarSessionRepository();
        Session session = repositoryFactory.getSession();
        Transaction transaction = session.beginTransaction();
        carSessionRepository.save(carSession);
        session.flush();
        transaction.commit();

        return carSession;
    }

    private CarSessionRepository getCarSessionRepository() {
        if (null == carSessionRepository) {
            carSessionRepository = repositoryFactory.getCarServiceRepository();
        }
        return carSessionRepository;
    }

    private CarRepository getCarRepository() {
        if (null == carRepository) {
            carRepository = repositoryFactory.getCarRepository();
        }
        return carRepository;
    }


    private static AvailableCar calculatePeriodPrice(CarEntity car, int days) {
        long costPerDay = car.getCostPerDay();
        long costForPeriod = calculateCost(costPerDay, days);
        return new AvailableCarImpl(car, costForPeriod);
    }

    private static long calculateCost(long price, int days) {
        int from18Days = 0;
        int from10Days = 0;
        int from5Days = 0;
        int baseRateDays = 0;

        if (days > 17) {
            from18Days = days - 17;
            days = 17;
        }
        if (days > 9) {
            from10Days = days - 9;
            days = 9;
        }
        if (days > 4) {
            from5Days = days - 4;
            days = 4;
        }
        baseRateDays = days;

        return (long) (price * (from18Days * .85 + from10Days * .90 + from5Days * .95 + baseRateDays));
    }
}
