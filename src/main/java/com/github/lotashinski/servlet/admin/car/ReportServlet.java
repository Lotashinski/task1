package com.github.lotashinski.servlet.admin.car;

import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.service.paramconverter.LocalDataConverter;
import com.github.lotashinski.service.res.CarRepostService;
import com.github.lotashinski.service.res.CarsMonthReport;
import com.github.lotashinski.service.res.ServiceFactory;
import com.github.lotashinski.util.ServletConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
    @Operation(summary = "Generate a report",
            tags = {"report"},
            description = "Generate a report on the workload of machines and services for the period.",
            responses = {
                    @ApiResponse(
                            description = "The report",
                            content = @Content(
                                    schema = @Schema(implementation = CarsMonthReport.class)
                            )
                    )
            }
    )
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
