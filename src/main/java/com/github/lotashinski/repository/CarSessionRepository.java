package com.github.lotashinski.repository;

import com.github.lotashinski.entity.CarSessionEntity;
import com.github.lotashinski.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarSessionRepository {
    Optional<CarSessionEntity> getById(Long id);

    CarSessionEntity save(CarSessionEntity carSession);

    CarSessionEntity delete(CarSessionEntity carSession);

    List<CarSessionEntity> loadByUser(UserEntity user);

    List<CarSessionEntity> loadByPeriod(LocalDate startAt, LocalDate endAt);
}
