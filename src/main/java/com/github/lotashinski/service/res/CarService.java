package com.github.lotashinski.service.res;

import com.github.lotashinski.entity.CarEntity;
import com.github.lotashinski.entity.CarSessionEntity;
import com.github.lotashinski.repository.exception.NotFoundException;

import java.time.LocalDate;

public interface CarService {
    CarSet getPaginated(int limit, int offset);

    AvailableCarSet getAvailablePaginated(LocalDate startAt, LocalDate endAt, int limit, int offset);

    CarEntity getById(long id) throws NotFoundException;

    CarSessionEntity bookCar(CarSessionEntity carSession);
}
