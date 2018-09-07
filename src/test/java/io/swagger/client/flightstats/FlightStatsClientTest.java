package io.swagger.client.flightstats;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Flight;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FlightStatsClientTest {
    @Test
    public void lookupFlight() throws Exception {
        String apiKey = "";
        String apiId = "";
        FlightStatsClient client = new FlightStatsClient(apiId, apiKey);
        LocalDate arrivalDate = LocalDate.of(2018, 8, 17);

        Flight flight = client.lookupFlight("AS", "15", "SEA", arrivalDate);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(flight);
        assertThat(json, is("blah"));
    }
}
