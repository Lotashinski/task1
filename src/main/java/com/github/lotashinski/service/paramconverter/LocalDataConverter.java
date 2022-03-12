package com.github.lotashinski.service.paramconverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class LocalDataConverter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");


    public static LocalDate parseData(String dataString) {
        if (null == dataString) {
            return LocalDate.now();
        }
        return LocalDate.parse(dataString, formatter);
    }
}