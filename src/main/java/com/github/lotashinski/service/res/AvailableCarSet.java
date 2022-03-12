package com.github.lotashinski.service.res;

import java.util.List;

public interface AvailableCarSet {
    int getLimit();

    int getOffset();

    long getAllCount();

    int getCount();

    List<AvailableCar> getCars();
}
