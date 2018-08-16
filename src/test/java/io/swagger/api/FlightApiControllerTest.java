package io.swagger.api;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class FlightApiControllerTest {
    @Test
    public void getFlight() {
    }

    @Test
    public void dateTest() {
        String date = "20180901";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
    }
}
