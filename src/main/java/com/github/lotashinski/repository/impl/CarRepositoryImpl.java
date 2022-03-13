package com.github.lotashinski.repository.impl;

import com.github.lotashinski.entity.CarEntity;
import com.github.lotashinski.repository.CarRepository;
import org.hibernate.Session;

import java.time.LocalDate;
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
    public List<CarEntity> paginated(int limit, int offset) {
        return session.createQuery(
                        "SELECT c FROM CarEntity c ORDER BY c.id",
                        CarEntity.class
                )
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }

    @Override
    public List<CarEntity> getAvailablePerDateTime(LocalDate startAt, LocalDate endAt, int limit, int offset) {
        return session.createQuery(
                        "SELECT c " +
                                "FROM CarEntity c " +
                                "WHERE NOT EXISTS (" +
                                "   SELECT cs1 " +
                                "   FROM CarSessionEntity cs1 " +
                                "   WHERE cs1.car = c " +
                                "       AND (" +
                                "               (:startAt <= cs1.startAt AND cs1.startAt <= :endAt) " +
                                "           OR " +
                                "               (:startAt <= cs1.serviceEnd AND cs1.serviceEnd <= :endAt)" +
                                "           OR " +
                                "               (cs1.startAt <= :startAt AND :startAt <= cs1.serviceEnd) " +
                                "           OR " +
                                "               (cs1.startAt <= :endAt AND :endAt <= cs1.serviceEnd) " +
                                "       ) " +
                                ")"
                        , CarEntity.class
                )
                .setParameter("startAt", startAt)
                .setParameter("endAt", endAt)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }

    @Override
    public Long availableCount(LocalDate startAt, LocalDate endAt) {
        return session.createQuery(
                        "SELECT count(c.id) " +
                                "FROM CarEntity c " +
                                "WHERE NOT EXISTS (" +
                                "   SELECT cs1 " +
                                "   FROM CarSessionEntity cs1 " +
                                "   WHERE cs1.car = c " +
                                "       AND (" +
                                "               (:startAt <= cs1.startAt AND cs1.startAt <= :endAt) " +
                                "           OR " +
                                "               (:startAt <= cs1.serviceEnd AND cs1.serviceEnd <= :endAt)" +
                                "           OR " +
                                "               (cs1.startAt <= :startAt AND :startAt <= cs1.serviceEnd) " +
                                "           OR " +
                                "               (cs1.startAt <= :endAt AND :endAt <= cs1.serviceEnd) " +
                                "       ) " +
                                ")"
                        , Long.class
                )
                .setParameter("startAt", startAt)
                .setParameter("endAt", endAt)
                .uniqueResult();
    }

    @Override
    public Boolean isAvailable(CarEntity car, LocalDate startAt, LocalDate endAt) {
        long isAvailable = session.createQuery(
                        "SELECT count(c.id) " +
                                "FROM CarEntity c " +
                                "WHERE c = :car " +
                                "   AND NOT EXISTS (" +
                                "   SELECT cs1 " +
                                "   FROM CarSessionEntity cs1 " +
                                "   WHERE cs1.car = c " +
                                "       AND (" +
                                "               (:startAt <= cs1.startAt AND cs1.startAt <= :endAt) " +
                                "           OR " +
                                "               (:startAt <= cs1.serviceEnd AND cs1.serviceEnd <= :endAt)" +
                                "           OR " +
                                "               (cs1.startAt <= :startAt AND :startAt <= cs1.serviceEnd) " +
                                "           OR " +
                                "               (cs1.startAt <= :endAt AND :endAt <= cs1.serviceEnd) " +
                                "       ) " +
                                ")"
                        , Long.class
                )
                .setParameter("startAt", startAt)
                .setParameter("endAt", endAt)
                .setParameter("car", car)
                .uniqueResult();
        return isAvailable == 1;
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

    @Override
    public Long count() {
        return session.createQuery("SELECT count(c.id) FROM CarEntity c", Long.class).uniqueResult();
    }
}
