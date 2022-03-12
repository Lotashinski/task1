package com.github.lotashinski.service.res;

import com.github.lotashinski.repository.RepositoryFactory;
import com.github.lotashinski.service.res.impl.CarReportServiceImpl;
import com.github.lotashinski.service.res.impl.CarServiceImpl;
import com.github.lotashinski.service.res.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServiceFactory {
    private final Logger logger = LogManager.getRootLogger();
    private final RepositoryFactory repositoryFactory;

    private UserService userService;
    private CarService carService;
    private CarRepostService carRepostService;

    public ServiceFactory(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    public UserService getUserService() {
        if (null == userService) {
            logger.debug("Create UserServiceImpl");
            userService = new UserServiceImpl(repositoryFactory);
        }
        return userService;
    }

    public CarService getCarService() {
        if (null == carService) {
            logger.debug("Create CarServiceImpl");
            carService = new CarServiceImpl(repositoryFactory);
        }
        return carService;
    }

    public CarRepostService getCarRepostService(){
        if (null == carRepostService){
            logger.debug("Create CarReportService");
            carRepostService = new CarReportServiceImpl(repositoryFactory);
        }
        return carRepostService;
    }
}
