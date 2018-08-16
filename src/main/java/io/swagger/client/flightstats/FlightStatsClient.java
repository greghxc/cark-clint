package io.swagger.client.flightstats;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.client.FlightInfoClient;
import io.swagger.model.Flight;
import io.swagger.model.FlightPartnerId;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FlightStatsClient implements FlightInfoClient {
    private String appId;
    private String appKey;
    
    public FlightStatsClient(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    @Override
    public Flight lookupFlight(String airline, String flightNumber, String arrivalAirport, LocalDate arrivalDate) {
        LocalDate statusCutoff = LocalDate.now(ZoneId.of("America/Los_Angeles")).plusDays(2);
        if (arrivalDate.isBefore(statusCutoff)) {
            return lookupFlightStatus(airline, flightNumber, arrivalAirport, arrivalDate);
        } else {
            return lookupFlightSchedule(airline, flightNumber, arrivalAirport, arrivalDate);
        }
    }

    private Flight lookupFlightStatus(String airline, String flightNumber, String arrivalAirport, LocalDate arrivalDate) {
        String year = Integer.toString(arrivalDate.getYear());
        String month = Integer.toString(arrivalDate.getMonth().getValue());
        String day = Integer.toString(arrivalDate.getDayOfMonth());

        String path = String.format(
                "/flex/flightstatus/rest/v2/json/flight/status/%s/%s/arr/%s/%s/%s",
                airline, flightNumber, year, month, day
        );

        URI uri = UriBuilder
                .fromPath(path)
                .scheme("https")
                .host("api.flightstats.com")
                .queryParam("appId", appId)
                .queryParam("appKey", appKey)
                .queryParam("airport", arrivalAirport)
                .build();

        JsonObject json = fetchJson(uri);

        Flight flight = new Flight();

        JsonObject flightStatus = json.getAsJsonArray("flightStatuses").get(0).getAsJsonObject();

        FlightPartnerId partnerId = new FlightPartnerId();
        partnerId.setId(flightStatus.get("flightId").getAsString());
        partnerId.setProvider("FlightStats");
        flight.setPartnerId(partnerId);

        flight.setOrigin(flightStatus.get("departureAirportFsCode").getAsString());
        flight.setDestination(flightStatus.get("arrivalAirportFsCode").getAsString());

        JsonObject opertationalTimes = flightStatus.get("operationalTimes").getAsJsonObject();

        flight.setScheduledArrival(getOperationalTime(opertationalTimes, "scheduledGateArrival"));
        flight.setEstimatedArrival(getOperationalTime(opertationalTimes, "estimatedGateArrival"));
        flight.setActualArrival(getOperationalTime(opertationalTimes, "actualGateArrival"));

        flight.setStatus(FlightStatsStatus.valueOf(flightStatus.get("status").getAsString()).toFlightStatus());

        return flight;
    }

    private Flight lookupFlightSchedule(String airline, String flightNumber, String arrivalAirport, LocalDate arrivalDate) {
        String year = Integer.toString(arrivalDate.getYear());
        String month = Integer.toString(arrivalDate.getMonth().getValue());
        String day = Integer.toString(arrivalDate.getDayOfMonth());

        String path = String.format(
                "/flex/schedules/rest/v1/json/flight/%s/%s/arriving/%s/%s/%s",
                airline, flightNumber, year, month, day
        );

        URI uri = UriBuilder
                .fromPath(path)
                .scheme("https")
                .host("api.flightstats.com")
                .queryParam("appId", appId)
                .queryParam("appKey", appKey)
                .queryParam("airport", arrivalAirport)
                .build();

        JsonObject json = fetchJson(uri);

        Flight flight = new Flight();

        FlightPartnerId flightPartnerId = new FlightPartnerId();
        flightPartnerId.setId(null);
        flightPartnerId.setProvider("FlightStats");

        flight.setPartnerId(flightPartnerId);

        JsonObject flightSchedule = json.getAsJsonArray("scheduledFlights").get(0).getAsJsonObject();

        flight.setOrigin(flightSchedule.get("departureAirportFsCode").getAsString());
        flight.setDestination(flightSchedule.get("arrivalAirportFsCode").getAsString());

        flight.setScheduledArrival(getScheduledTime(flightSchedule, "arrivalTime"));

        flight.setStatus(Flight.StatusEnum.SCHEDULED);

        return flight;
    }

    private String getScheduledTime(JsonObject schedule, String name) {
        if (schedule.has(name)) {
            String origString = schedule.get(name).getAsString();
            LocalDateTime localDateTime = LocalDateTime.parse(origString);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
            return  localDateTime.format(formatter);
        } else {
            return null;
        }
    }
    private String getOperationalTime(JsonObject object, String name) {
        if (object.has(name)) {
            String origString = object.getAsJsonObject(name).get("dateLocal").getAsString();
            LocalDateTime localDateTime = LocalDateTime.parse(origString);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
            return  localDateTime.format(formatter);
        } else {
            return null;
        }
    }

    private JsonObject fetchJson(URI uri) {
        Gson gson = new Gson();
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        return gson.fromJson(responseEntity.getBody(), JsonObject.class);
    }
}
