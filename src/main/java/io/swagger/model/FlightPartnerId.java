package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * FlightPartnerId
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-08T14:54:06.952Z")

public class FlightPartnerId   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("provider")
  private String provider = null;

  public FlightPartnerId id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Id used by data provider to identify this flight
   * @return id
  **/
  @ApiModelProperty(example = "222dfe8b-5cd2-4613-88e5-8aa37400e836", value = "Id used by data provider to identify this flight")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public FlightPartnerId provide(String provide) {
    this.provider = provide;
    return this;
  }

  /**
   * Name of data provider for the Id
   * @return provider
  **/
  @ApiModelProperty(example = "FlightStats", value = "Name of data provider for the Id")


  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FlightPartnerId flightPartnerId = (FlightPartnerId) o;
    return Objects.equals(this.id, flightPartnerId.id) &&
        Objects.equals(this.provider, flightPartnerId.provider);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, provider);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FlightPartnerId {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

