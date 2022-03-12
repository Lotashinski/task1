package com.github.lotashinski.servlet.cars;

import com.github.lotashinski.dto.CarSetDto;
import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.service.paramconverter.Pagination;
import com.github.lotashinski.service.res.CarService;
import com.github.lotashinski.service.res.CarSet;
import com.github.lotashinski.service.res.ServiceFactory;
import com.github.lotashinski.util.ServletConstants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/cars", name = "CarServlet")
public final class CarsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServiceFactory serviceFactory = (ServiceFactory) req.getAttribute(ServletConstants.SERVICE_FACTORY);
        CarService carService = serviceFactory.getCarService();

        Pagination pagination = Pagination.configureFromRequest(req);
        int limit = pagination.getLimit();
        int offset = pagination.getOffset();

        CarSet carSet = carService.getPaginated(limit, offset);
        CarSetDto carSetDto = CarSetConverter.convertCarSetToDto(carSet);

        JsonConverter.toJsonResponse(carSetDto, resp);
    }
}
