package com.github.lotashinski.service.res.impl;

import com.github.lotashinski.entity.CarEntity;
import com.github.lotashinski.repository.CarRepository;
import com.github.lotashinski.repository.RepositoryFactory;

import java.util.List;

public final class CarServiceImpl implements com.github.lotashinski.service.res.CarService {
    private final RepositoryFactory repositoryFactory;
    private CarRepository carRepository;


    public CarServiceImpl(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }


    @Override
    public List<CarEntity> getAll() {
        return getCarRepository().all();
    }

    private CarRepository getCarRepository() {
        if (null == carRepository) {
            carRepository = repositoryFactory.getCarRepository();
        }
        return carRepository;
    }
}
