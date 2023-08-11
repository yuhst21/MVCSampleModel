package com.mvc.sample.javacore.mvcmodel.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    public static Date convertStringToDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return Date.valueOf(localDate);
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toLocalDate();
    }

    public static String convertLocalDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return localDate.format(formatter);
    }
}
