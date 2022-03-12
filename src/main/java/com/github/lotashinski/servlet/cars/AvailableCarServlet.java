package com.github.lotashinski.servlet.cars;

import com.github.lotashinski.dto.AvailableCarSetDto;
import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.service.paramconverter.LocalDataConverter;
import com.github.lotashinski.service.paramconverter.Pagination;
import com.github.lotashinski.service.res.AvailableCarSet;
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

@WebServlet(name = "AvailableCarServlet", urlPatterns = "/cars/available")
public final class AvailableCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactory serviceFactory = (ServiceFactory) req.getAttribute(ServletConstants.SERVICE_FACTORY);
        CarService carService = serviceFactory.getCarService();

        String startAtString = req.getParameter("start");
        String endAtString = req.getParameter("end");

        LocalDate startAt = LocalDataConverter.parseData(startAtString);
        LocalDate endAt = LocalDataConverter.parseData(endAtString);
        Pagination pagination = Pagination.configureFromRequest(req);

        AvailableCarSet carSet = carService.getAvailablePaginated(startAt, endAt,
                pagination.getLimit(), pagination.getOffset());

        AvailableCarSetDto dto = CarSetConverter.convertAvailableCarSetToDto(carSet);
        JsonConverter.toJsonResponse(dto, resp);
    }


}
