package com.github.lotashinski.repository;

import com.github.lotashinski.repository.impl.CarRepositoryImpl;
import com.github.lotashinski.repository.impl.CarSessionRepositoryImpl;
import com.github.lotashinski.repository.impl.UserRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Closeable;

public final class RepositoryFactory implements Closeable {

    private final Logger logger = LogManager.getRootLogger();
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }


    private Session session;
    private UserRepository userRepository;
    private CarRepository carRepository;
    private CarSessionRepository carSessionRepository;

    public Session getSession() {
        if (null == session) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public UserRepository getUserRepository() {
        if (null == userRepository) {
            Session session = getSession();
            userRepository = new UserRepositoryImpl(session);
        }
        return userRepository;
    }

    public CarRepository getCarRepository() {
        if (null == carRepository) {
            Session session = getSession();
            carRepository = new CarRepositoryImpl(session);
        }
        return carRepository;
    }

    public CarSessionRepository getCarServiceRepository() {
        if (null == carSessionRepository) {
            Session session = getSession();
            carSessionRepository = new CarSessionRepositoryImpl(session);
        }
        return carSessionRepository;
    }

    @Override
    public void close() {
        logger.debug("Close repository factory");
        if (null != session) {
            session.close();
        }
    }
}
