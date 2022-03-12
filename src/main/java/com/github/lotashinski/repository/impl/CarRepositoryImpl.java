package com.github.lotashinski.repository.impl;

import com.github.lotashinski.entity.CarEntity;
import com.github.lotashinski.entity.CarSessionEntity;
import com.github.lotashinski.repository.CarRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public final class CarRepositoryImpl implements CarRepository {

    private final Session session;


    public CarRepositoryImpl(Session session) {
        this.session = session;
    }


    @Override
    public Optional<CarEntity> getById(Long id) {
        return session.byId(CarEntity.class).loadOptional(id);
    }

    @Override
    public List<CarEntity> all() {
        return session.createQuery(
                "SELECT c FROM CarEntity c",
                CarEntity.class
        ).getResultList();
    }

    @Override
    public CarEntity save(CarEntity car) {
        if (null == car.getId()) {
            session.persist(car);
        } else {
            session.merge(car);
        }

        return car;
    }

    @Override
    public CarEntity delete(CarEntity car) {
        if (session.contains(car)) {
            session.remove(car);
        } else {
            session.merge(car);
            session.delete(car);
        }

        return car;
    }
}
