# Cark Clint
It's a bird! No, it's a plane! Yes, it is a plane!

## Overview
An abstract service to normalize flight data retrieved from multiple API endpoints or, potentially, services. Currently the only implmentation is agains the FlightStats flex API.

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
