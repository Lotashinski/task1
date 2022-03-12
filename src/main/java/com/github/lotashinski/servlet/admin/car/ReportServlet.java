package com.github.lotashinski.servlet.admin.car;

import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.service.paramconverter.LocalDataConverter;
import com.github.lotashinski.service.res.CarRepostService;
import com.github.lotashinski.service.res.CarsMonthReport;
import com.github.lotashinski.service.res.ServiceFactory;
import com.github.lotashinski.util.ServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ReportServlet", urlPatterns = "/admin/cars/report")
public final class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactory serviceFactory = (ServiceFactory) req.getAttribute(ServletConstants.SERVICE_FACTORY);
        CarRepostService carRepostService = serviceFactory.getCarRepostService();

        String startAtString = req.getParameter("start");
        String endAtString = req.getParameter("end");

        LocalDate startAt = LocalDataConverter.parseData(startAtString);
        LocalDate endAt = LocalDataConverter.parseData(endAtString);

        CarsMonthReport report = carRepostService.configureReportByAll(startAt, endAt);

        JsonConverter.toJsonResponse(report, resp);
    }
}
