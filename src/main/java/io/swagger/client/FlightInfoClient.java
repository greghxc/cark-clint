package io.swagger.client;

import io.swagger.model.Flight;

import java.time.LocalDate;

public interface FlightInfoClient {
    public Flight lookupFlight(String airline, String flightNumber, String arrivalAirport, LocalDate arrivalDate);
}
