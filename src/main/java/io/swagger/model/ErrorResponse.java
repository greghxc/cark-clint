package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ErrorResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-08T14:54:06.952Z")

public class ErrorResponse   {
  @JsonProperty("httpstatus")
  private String httpstatus = null;

  @JsonProperty("message")
  private String message = null;

  public ErrorResponse httpstatus(String httpstatus) {
    this.httpstatus = httpstatus;
    return this;
  }

  /**
   * HTTP status code
   * @return httpstatus
  **/
  @ApiModelProperty(example = "404", value = "HTTP status code")


  public String getHttpstatus() {
    return httpstatus;
  }

  public void setHttpstatus(String httpstatus) {
    this.httpstatus = httpstatus;
  }

  public ErrorResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Additional information
   * @return message
  **/
  @ApiModelProperty(example = "Flight not found", value = "Additional information")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.httpstatus, errorResponse.httpstatus) &&
        Objects.equals(this.message, errorResponse.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(httpstatus, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResponse {\n");
    
    sb.append("    httpstatus: ").append(toIndentedString(httpstatus)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

