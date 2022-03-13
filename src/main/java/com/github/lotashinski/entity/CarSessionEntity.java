package com.github.lotashinski.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CarSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne
    @JoinColumn(nullable = false)
    private CarEntity car;

    @ManyToOne
    private UserEntity user;


    @Basic
    private LocalDate startAt;

    @Basic
    private LocalDate endAt;

    @Basic
    private LocalDate serviceEnd;

    @Basic
    private Long price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getServiceEnd() {
        return serviceEnd;
    }

    public void setServiceEnd(LocalDate serviceEnd) {
        this.serviceEnd = serviceEnd;
    }
}
