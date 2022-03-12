package com.github.lotashinski.repository;

import com.github.lotashinski.entity.CarEntity;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    Optional<CarEntity> getById(Long id);

    CarEntity save(CarEntity car);

    CarEntity delete(CarEntity car);

    List<CarEntity> all();
}
