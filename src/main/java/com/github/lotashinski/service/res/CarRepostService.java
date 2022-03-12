package com.github.lotashinski.service.res;

import java.time.LocalDate;

public interface CarRepostService {
    CarsMonthReport configureReportByAll(LocalDate startAt, LocalDate endAt);
}
