package com.github.lotashinski.repository;

import com.github.lotashinski.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> getById(Long id);

    Optional<UserEntity> getByLogin(String login);

    UserEntity save(UserEntity user);

    UserEntity delete(UserEntity user);
}
