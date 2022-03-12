package com.github.lotashinski.repository.impl;

import com.github.lotashinski.entity.UserEntity;
import com.github.lotashinski.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public final class UserRepositoryImpl implements UserRepository {

    private final Session session;


    public UserRepositoryImpl(Session session) {
        this.session = session;
    }


    @Override
    public Optional<UserEntity> getById(Long id) {
        return session.byId(UserEntity.class).loadOptional(id);
    }

    @Override
    public Optional<UserEntity> getByLogin(String login) {
        Query<UserEntity> query = session.createQuery(
                "SELECT u " +
                        "FROM UserEntity u " +
                        "WHERE u.login = :login",
                UserEntity.class);

        query.setParameter("login", login);

        return query.uniqueResultOptional();
    }

    @Override
    public UserEntity save(UserEntity user) {
        if (null == user.getId()) {
            session.persist(user);
        } else {
            session.merge(user);
        }

        return user;
    }

    @Override
    public UserEntity delete(UserEntity user) {
        if (session.contains(user)) {
            session.remove(user);
        } else {
            session.merge(user);
            session.delete(user);
        }

        return user;
    }
}
