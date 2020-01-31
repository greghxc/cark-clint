# Cark Clint
It's a bird! No, it's a plane! Yes, it is a plane!

## Overview
An abstract service to standardize flight data retrieved from multiple API endpoints or, potentially, services. Currently the only implmentation is against the FlightStats flex API.

This server was initially generated as a stub by the [swagger-codegen](https://github.com/swagger-api/swagger-codegen) project from an [OpenAPI specification](https://github.com/swagger-api/swagger-core).

## Example

### Request

```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/v1/flight?airline=AS&flightno=2801&airport=SEA&date=20180907'
```

### Response

```
{
  "partnerId": {
    "id": "972208080",
    "provider": "FlightStats"
  },
  "origin": "ABQ",
  "destination": "SEA",
  "scheduledArrival": "20180905T222200",
  "estimatedArrival": "20180905T220200",
  "actualArrival": "20180905T220200",
  "status": "Landed"
}
```
## Provider Implementation Notes

### Flight Stats
Flight stats provides multiple API's endpoints for flight lookups: one for looking up scheduled flights more than three days in advance, and another for flights less than three days in advance that includes tracking and flight status.

This application chooses which API to call based on the flight date, and returns a standard payload with a single schema, regardless of date.

## Requirements
* Java 8
* Maven 3.5.x
## Startup
Create an account (or test account) with FlightStats. Create an AppKey and AppId.

Start the application by navigating to the project folder and running:
```
mvn spring-boot:run -Drun.arguments=--flightStats.appId=your_app_id,--flightStats.appKey=your_app_key
```

Once running, visit the Swagger docs at: [http://localhost:8080/v1](http://localhost:8080/v1)
