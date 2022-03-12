package com.github.lotashinski.servlet.cars;

import com.github.lotashinski.dto.AvailableCarDto;
import com.github.lotashinski.dto.AvailableCarSetDto;
import com.github.lotashinski.dto.CarDto;
import com.github.lotashinski.dto.CarSetDto;
import com.github.lotashinski.entity.CarEntity;
import com.github.lotashinski.service.res.AvailableCar;
import com.github.lotashinski.service.res.AvailableCarSet;
import com.github.lotashinski.service.res.CarSet;

import java.util.List;
import java.util.stream.Collectors;

public final class CarSetConverter {
    public static AvailableCarSetDto convertAvailableCarSetToDto(AvailableCarSet carSet) {
        AvailableCarSetDto dto = new AvailableCarSetDto();

        dto.setAllCount(carSet.getAllCount());
        dto.setCount(carSet.getCount());
        dto.setLimit(carSet.getLimit());
        dto.setOffset(carSet.getOffset());

        List<AvailableCar> carList = carSet.getCars();
        List<AvailableCarDto> carDtoList = carList.stream()
                .map(CarSetConverter::convertAvailableCarToDto)
                .collect(Collectors.toList());
        dto.setCars(carDtoList);

        return dto;
    }

    public static AvailableCarDto convertAvailableCarToDto(AvailableCar car) {
        AvailableCarDto dto = new AvailableCarDto();

        dto.setId(car.getId());
        dto.setModel(car.getModel());
        dto.setRegistrationNumber(car.getRegistrationNumber());
        dto.setCostPerDay(car.getCostPerDay());
        dto.setCostPerPeriod(car.getCostPerPeriod());

        return dto;
    }

    public static CarSetDto convertCarSetToDto(CarSet carSet) {
        CarSetDto carSetDto = new CarSetDto();

        carSetDto.setAllCount(carSet.getAllCount());
        carSetDto.setCount(carSet.getCount());
        carSetDto.setLimit(carSet.getLimit());
        carSetDto.setOffset(carSet.getOffset());

        List<CarEntity> carEntityList = carSet.getCars();
        List<CarDto> carDtoList = carEntityList.stream()
                .map(CarSetConverter::convertCarEntityToDto)
                .collect(Collectors.toList());

        carSetDto.setCars(carDtoList);

        return carSetDto;
    }

    public static CarDto convertCarEntityToDto(CarEntity car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setModel(car.getModel());
        carDto.setRegistrationNumber(car.getRegistrationNumber());
        carDto.setCostPerDay(car.getCostPerDay());

        return carDto;
    }
}
