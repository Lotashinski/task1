package com.github.lotashinski.service.res;

import com.github.lotashinski.entity.CarEntity;

import java.util.List;

public interface CarSet {
    int getLimit();

    int getOffset();

    long getAllCount();

    int getCount();

    List<CarEntity> getCars();
}
