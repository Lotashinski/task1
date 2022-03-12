package com.github.lotashinski.repository.impl;

import com.github.lotashinski.entity.CarSessionEntity;
import com.github.lotashinski.repository.CarSessionRepository;
import org.hibernate.Session;

import java.util.Optional;

public final class CarSessionRepositoryImpl implements CarSessionRepository {

    private Session session;


    public CarSessionRepositoryImpl(Session session) {
        this.session = session;
    }


    @Override
    public Optional<CarSessionEntity> getById(Long id) {
        return session.byId(CarSessionEntity.class).loadOptional(id);
    }

    @Override
    public CarSessionEntity save(CarSessionEntity carSession) {
        if (null == carSession.getId()) {
            session.persist(carSession);
        } else {
            session.merge(carSession);
        }

        return carSession;
    }

    @Override
    public CarSessionEntity delete(CarSessionEntity carSession) {
        if (session.contains(carSession)) {
            session.remove(carSession);
        } else {
            session.merge(carSession);
            session.delete(carSession);
        }

        return carSession;
    }
}
