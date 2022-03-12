package com.github.lotashinski.dto;

import java.time.LocalDate;

public final class CarSessionDto {
    private Long id;
    private LocalDate startAt;
    private LocalDate endAt;
    private long price;
    private CarDto car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDate endAt) {
        this.endAt = endAt;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto carDto) {
        this.car = carDto;
    }
}
