package com.github.lotashinski.service.res;

import com.github.lotashinski.entity.UserEntity;

public interface UserService {
    UserEntity registration(UserEntity user);
    UserEntity login(String login, char[] password);
}
