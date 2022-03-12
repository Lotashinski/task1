package com.github.lotashinski.servlet.cars;

import com.github.lotashinski.dto.CarDto;
import com.github.lotashinski.entity.CarEntity;
import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.service.res.CarService;
import com.github.lotashinski.service.res.ServiceFactory;
import com.github.lotashinski.util.ServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/cars", name = "CarServlet")
public final class CarsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactory serviceFactory = (ServiceFactory) req.getAttribute(ServletConstants.SERVICE_FACTORY);
        CarService carService = serviceFactory.getCarService();

        List<CarEntity> cars = carService.getAll();
        List<CarDto> carDtoList = cars.stream()
                .map(CarsServlet::convertCarEntityToDto)
                .collect(Collectors.toList());

        JsonConverter.toJsonResponse(carDtoList, resp);
    }

    private static CarDto convertCarEntityToDto(CarEntity car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setModel(car.getModel());
        carDto.setRegistrationNumber(car.getRegistrationNumber());
        carDto.setCostPerDay(car.getCostPerDay());

        return carDto;
    }
}
