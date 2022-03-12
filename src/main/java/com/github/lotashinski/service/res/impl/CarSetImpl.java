package com.github.lotashinski.service.res.impl;

import com.github.lotashinski.entity.CarEntity;

import java.util.List;

public final class CarSetImpl implements com.github.lotashinski.service.res.CarSet {
    private int limit;
    private int offset;
    private long allCount;
    private int count;

    private List<CarEntity> cars;


    @Override
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public long getAllCount() {
        return allCount;
    }

    public void setAllCount(long allCount) {
        this.allCount = allCount;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }
}
