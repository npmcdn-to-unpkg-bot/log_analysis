package com.log.config;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class Constants
{


    public static final DateTimeFormatter LocalDateTimeFormatter=
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static final DateTimeFormatter LocalTimeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public static final DateTimeFormatter LocalDateFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");


    private Constants()
    {
    }
}
