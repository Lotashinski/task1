package com.github.lotashinski.service.res.impl;

import com.github.lotashinski.service.res.AvailableCar;

import java.util.List;

public final class AvailableCarSetImpl implements com.github.lotashinski.service.res.AvailableCarSet {
    private final int limit;
    private final int offset;
    private final long allCount;
    private final int count;

    private final List<AvailableCar> cars;


    public AvailableCarSetImpl(int limit, int offset, long allCount, List<AvailableCar> cars) {
        this.limit = limit;
        this.offset = offset;
        this.allCount = allCount;
        this.cars = cars;
        count = cars.size();
    }


    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public long getAllCount() {
        return allCount;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public List<AvailableCar> getCars() {
        return cars;
    }
}
