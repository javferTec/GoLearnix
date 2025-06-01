package com.golearnix.common.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

  private final String error;
  private final String message;
  private final List<String> validationErrors;

  public ErrorMessage(Exception exception) {
    this.error = exception.getClass().getSimpleName();
    this.message = exception.getMessage();
    this.validationErrors = null;
  }

  public ErrorMessage(List<String> validationErrors) {
    this.error = "ValidationException";
    this.message = "VALIDATION EXCEPTION";
    this.validationErrors = validationErrors.isEmpty() ? null : validationErrors;
  }
}