package io.swagger.client.flightstats;

import io.swagger.model.Flight;

import java.util.HashMap;
import java.util.Map;

public enum FlightStatsStatus {
    A, C, D, DN, L, NO, R, S, U;

    public Flight.StatusEnum toFlightStatus(FlightStatsStatus status) {
        Map<FlightStatsStatus, Flight.StatusEnum> map = new HashMap<>();
        map.put(A, Flight.StatusEnum.IN_FLIGHT); // ACTIVE
        map.put(C, Flight.StatusEnum.CANCELED_DIVERTED); // CANCELED
        map.put(D, Flight.StatusEnum.CANCELED_DIVERTED); // DIVERTED
        map.put(DN, Flight.StatusEnum.UNKNOWN); // DATE SOURCE NEEDED
        map.put(L, Flight.StatusEnum.LANDED); // LANDED
        map.put(NO, Flight.StatusEnum.CANCELED_DIVERTED); // NOT OPERATIONAL
        map.put(R, Flight.StatusEnum.CANCELED_DIVERTED); // REDIRECTED
        map.put(S, Flight.StatusEnum.SCHEDULED); // SCHEDULED
        map.put(U, Flight.StatusEnum.UNKNOWN); // UNKNOWN

        return map.get(status);
    }

    public Flight.StatusEnum toFlightStatus() {
        return toFlightStatus(this);
    }
}
