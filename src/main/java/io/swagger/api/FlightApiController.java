package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.client.FlightInfoClient;
import io.swagger.model.ErrorResponse;
import io.swagger.model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-08T14:54:06.952Z")

@Controller
public class FlightApiController implements FlightApi {
    private static final Logger log = LoggerFactory.getLogger(FlightApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    private final FlightInfoClient flightInfoClient;

    @org.springframework.beans.factory.annotation.Autowired
    public FlightApiController(ObjectMapper objectMapper, HttpServletRequest request, FlightInfoClient flightInfoClient) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.flightInfoClient = flightInfoClient;
    }

    public ResponseEntity<Flight> getFlight(
            @NotNull @ApiParam(value = "IATA code for airline (e.g. AA, AL)", required = true)
            @Valid @RequestParam(value = "airline", required = true) String airline,
            @NotNull @ApiParam(value = "Flight number (e.g. 400)", required = true)
            @Valid @RequestParam(value = "flightno", required = true) String flightno,
            @NotNull @ApiParam(value = "Arrival airport (e.g. SEA, SFO)", required = true)
            @Valid @RequestParam(value = "airport", required = true) String airport,
            @NotNull @ApiParam(value = "Arrival date ISO8601 in local time (e.g. 20180214)", required = true)
            @Valid @RequestParam(value = "date", required = true) String date) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                LocalDate arrivalDate = LocalDate.parse(date, dateTimeFormatter);
                Flight flight = flightInfoClient.lookupFlight(airline, flightno, airport, arrivalDate);
                return new ResponseEntity<Flight>(flight, HttpStatus.OK);
            } catch (NullPointerException e) {
                log.error("NullPointerException at controller", e);
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setHttpstatus("200");
                errorResponse.setMessage("Not found.");
                return new ResponseEntity<Flight>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<Flight>(HttpStatus.NOT_IMPLEMENTED);
    }

}
