package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flight   {
  @JsonProperty("partnerId")
  private FlightPartnerId partnerId = null;

  @JsonProperty("origin")
  private String origin = null;

  @JsonProperty("destination")
  private String destination = null;

  @JsonProperty("scheduledArrival")
  private String scheduledArrival = null;

  @JsonProperty("estimatedArrival")
  private String estimatedArrival = null;

  @JsonProperty("actualArrival")
  private String actualArrival = null;

  public enum StatusEnum {
    SCHEDULED("Scheduled"),
    IN_FLIGHT("In Flight"),
    LANDED("Landed"),
    CANCELED_DIVERTED("Canceled / Diverted"),
    UNKNOWN("Unknown");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  public Flight partnerId(FlightPartnerId partnerId) {
    this.partnerId = partnerId;
    return this;
  }

  @ApiModelProperty(value = "ABC123")
  @Valid
  public FlightPartnerId getPartnerId() {
    return partnerId;
  }

  public void setPartnerId(FlightPartnerId partnerId) {
    this.partnerId = partnerId;
  }

  public Flight origin(String origin) {
    this.origin = origin;
    return this;
  }

  @ApiModelProperty(example = "SFO", value = "Originating airport")
  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public Flight destination(String destination) {
    this.destination = destination;
    return this;
  }

  @ApiModelProperty(example = "SEA", value = "Destination airport")
  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public Flight scheduledArrival(String scheduledArrival) {
    this.scheduledArrival = scheduledArrival;
    return this;
  }

  @ApiModelProperty(example = "20180215T155300", value = "Scheduled arrival time (ISO8601 UTC)")
  public String getScheduledArrival() {
    return scheduledArrival;
  }

  public void setScheduledArrival(String scheduledArrival) {
    this.scheduledArrival = scheduledArrival;
  }

  public Flight estimatedArrival(String estimatedArrival) {
    this.estimatedArrival = estimatedArrival;
    return this;
  }

  @ApiModelProperty(example = "20180215T155300", value = "Estimated arrival time (ISO8601 UTC)")
  public String getEstimatedArrival() {
    return estimatedArrival;
  }

  public void setEstimatedArrival(String estimatedArrival) {
    this.estimatedArrival = estimatedArrival;
  }

  public Flight actualArrival(String actualArrival) {
    this.actualArrival = actualArrival;
    return this;
  }

  @ApiModelProperty(example = "20180215T155300", value = "Actual arrival time (ISO8601 UTC)")
  public String getActualArrival() {
    return actualArrival;
  }

  public void setActualArrival(String actualArrival) {
    this.actualArrival = actualArrival;
  }

  public Flight status(StatusEnum status) {
    this.status = status;
    return this;
  }

  @ApiModelProperty(example = "Landed", value = "Current status of flight.")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Flight flight = (Flight) o;
    return Objects.equals(this.partnerId, flight.partnerId) &&
        Objects.equals(this.origin, flight.origin) &&
        Objects.equals(this.destination, flight.destination) &&
        Objects.equals(this.scheduledArrival, flight.scheduledArrival) &&
        Objects.equals(this.estimatedArrival, flight.estimatedArrival) &&
        Objects.equals(this.actualArrival, flight.actualArrival) &&
        Objects.equals(this.status, flight.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partnerId, origin, destination, scheduledArrival, estimatedArrival, actualArrival, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Flight {\n");
    sb.append("    partnerId: ").append(toIndentedString(partnerId)).append("\n");
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
    sb.append("    destination: ").append(toIndentedString(destination)).append("\n");
    sb.append("    scheduledArrival: ").append(toIndentedString(scheduledArrival)).append("\n");
    sb.append("    estimatedArrival: ").append(toIndentedString(estimatedArrival)).append("\n");
    sb.append("    actualArrival: ").append(toIndentedString(actualArrival)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
