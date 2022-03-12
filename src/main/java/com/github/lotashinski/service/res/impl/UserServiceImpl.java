package com.github.lotashinski.service.res.impl;

import com.github.lotashinski.entity.RolesEnum;
import com.github.lotashinski.entity.UserEntity;
import com.github.lotashinski.repository.RepositoryFactory;
import com.github.lotashinski.repository.UserRepository;
import com.github.lotashinski.repository.exception.LoginAlreadyExistException;
import com.github.lotashinski.repository.exception.NotFoundException;
import com.github.lotashinski.repository.exception.WrongPasswordException;
import com.github.lotashinski.service.password.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public final class UserServiceImpl implements com.github.lotashinski.service.res.UserService {
    private final Logger logger = LogManager.getRootLogger();
    private final RepositoryFactory factory;
    private UserRepository userRepository;


    public UserServiceImpl(RepositoryFactory factory) {
        this.factory = factory;
    }


    @Override
    public UserEntity registration(UserEntity user) {
        UserRepository userRepository = getUserRepository();
        String loginCandidate = user.getLogin();
        logger.debug("Check login");
        if (userRepository.getByLogin(loginCandidate).isPresent()) {
            throw new LoginAlreadyExistException("Login " + "\"" + loginCandidate + "\" already exist");
        }

        logger.debug("Hash password");
        char[] rawPassword = user.getPassword().toCharArray();
        user.setPassword(PasswordEncoder.hash(rawPassword));

        logger.debug("Set roles");
        user.addRole(RolesEnum.ROLE_USER);

        logger.debug("Save user");
        UserEntity savedUser = userRepository.save(user);

        logger.debug("Commit transaction");
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        session.flush();
        transaction.commit();

        return savedUser;
    }

    @Override
    public UserEntity login(String login, char[] password) {
        UserRepository userRepository = getUserRepository();
        logger.debug("Load user by login");
        Optional<UserEntity> optionalUserEntity = userRepository.getByLogin(login);

        String exceptionMessage = "User \"" + login + "\" not found or wrong password";
        UserEntity user = optionalUserEntity.orElseThrow(() -> new NotFoundException(exceptionMessage));

        logger.debug("Check password");
        boolean isVerified = PasswordEncoder.verify(user.getPassword(), password);
        if (!isVerified) {
            throw new WrongPasswordException(exceptionMessage);
        }
        return user;
    }


    private UserRepository getUserRepository() {
        if (null == userRepository) {
            userRepository = factory.getUserRepository();
        }
        return userRepository;
    }
}
