package com.github.lotashinski.dto;

import java.util.List;

public final class AvailableCarSetDto {
    private int limit;
    private int offset;
    private long allCount;
    private int count;
    private List<AvailableCarDto> cars;


    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public long getAllCount() {
        return allCount;
    }

    public void setAllCount(long allCount) {
        this.allCount = allCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AvailableCarDto> getCars() {
        return cars;
    }

    public void setCars(List<AvailableCarDto> cars) {
        this.cars = cars;
    }
}
