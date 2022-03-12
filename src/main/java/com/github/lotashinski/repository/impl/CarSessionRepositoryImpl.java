package com.github.lotashinski.repository.impl;

import com.github.lotashinski.entity.CarSessionEntity;
import com.github.lotashinski.entity.UserEntity;
import com.github.lotashinski.repository.CarSessionRepository;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;
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

    @Override
    public List<CarSessionEntity> loadByUser(UserEntity user) {
        return session
                .createQuery("SELECT cs " +
                                "FROM CarSessionEntity cs " +
                                "WHERE cs.user = :user",
                        CarSessionEntity.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<CarSessionEntity> loadByPeriod(LocalDate startAt, LocalDate endAt) {
        return session
                .createQuery("SELECT cs " +
                                "FROM CarSessionEntity cs " +
                                "INNER JOIN cs.car c " +
                                "WHERE (:startAt <= cs.startAt AND cs.startAt < :endAt) " +
                                "   OR (:startAt <= cs.endAt AND cs.endAt < :endAt)",
                        CarSessionEntity.class)
                .setParameter("startAt", startAt)
                .setParameter("endAt", endAt)
                .getResultList();
    }
}
