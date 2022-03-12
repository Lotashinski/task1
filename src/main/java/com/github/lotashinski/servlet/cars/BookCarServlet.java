package com.github.lotashinski.servlet.cars;

import com.github.lotashinski.dto.CarDto;
import com.github.lotashinski.dto.CarSessionDto;
import com.github.lotashinski.entity.CarEntity;
import com.github.lotashinski.entity.CarSessionEntity;
import com.github.lotashinski.entity.UserEntity;
import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.service.paramconverter.LocalDataConverter;
import com.github.lotashinski.service.paramconverter.Pagination;
import com.github.lotashinski.service.res.CarService;
import com.github.lotashinski.service.res.ServiceFactory;
import com.github.lotashinski.util.ServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "BookCarServlet", urlPatterns = "/cars/book")
public final class BookCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactory serviceFactory = (ServiceFactory) req.getAttribute(ServletConstants.SERVICE_FACTORY);
        UserEntity user = (UserEntity) req.getAttribute(ServletConstants.USER);
        List<CarSessionEntity> sessions = user.getCarSessions();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactory serviceFactory = (ServiceFactory) req.getAttribute(ServletConstants.SERVICE_FACTORY);
        UserEntity user = (UserEntity) req.getAttribute(ServletConstants.USER);
        CarService carService = serviceFactory.getCarService();

        String startAtString = req.getParameter("start");
        String endAtString = req.getParameter("end");
        LocalDate startAt = LocalDataConverter.parseData(startAtString);
        LocalDate endAt = LocalDataConverter.parseData(endAtString);

        String carIdStr = req.getParameter("car");
        int carId = Integer.parseInt(carIdStr);

        CarEntity car = carService.getById(carId);

        CarSessionEntity carSession = new CarSessionEntity();
        carSession.setCar(car);
        carSession.setUser(user);
        carSession.setStartAt(startAt);
        carSession.setEndAt(endAt);

        CarSessionEntity saved = carService.bookCar(carSession);
        CarSessionDto dto = convertSessionToDto(saved);
        JsonConverter.toJsonResponse(dto, resp);
    }

    private static CarSessionDto convertSessionToDto(CarSessionEntity carSession) {
        CarEntity car = carSession.getCar();
        CarDto carDto = CarSetConverter.convertCarEntityToDto(car);

        CarSessionDto carSessionDto = new CarSessionDto();
        carSessionDto.setId(carSession.getId());
        carSessionDto.setStartAt(carSession.getStartAt());
        carSessionDto.setEndAt(carSession.getEndAt());
        carSessionDto.setCar(carDto);
        carSessionDto.setPrice(carSession.getPrice());

        return carSessionDto;
    }
}
