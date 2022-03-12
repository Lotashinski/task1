package com.github.lotashinski.repository;

import com.github.lotashinski.entity.CarEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarRepository {
    Optional<CarEntity> getById(Long id);

    CarEntity save(CarEntity car);

    CarEntity delete(CarEntity car);

    List<CarEntity> paginated(int limit, int offset);

    List<CarEntity> getAvailablePerDateTime(LocalDate startAt, LocalDate endAt, int limit, int offset);

    Long count();

    Long availableCount(LocalDate startAt, LocalDate endAt);

    Boolean isAvailable(CarEntity car, LocalDate startAt, LocalDate endAt);
}
