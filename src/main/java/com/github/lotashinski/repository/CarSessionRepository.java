package com.github.lotashinski.repository;

import com.github.lotashinski.entity.CarSessionEntity;

import java.util.Optional;

public interface CarSessionRepository {
    Optional<CarSessionEntity> getById(Long id);

    CarSessionEntity save(CarSessionEntity carSession);

    CarSessionEntity delete(CarSessionEntity carSession);
}
