package com.golearnix.common.exceptions;

public class ResourceNullException extends RuntimeException {

  private static final String DESCRIPTION = "THE RESOURCE IS NULL. ";

  public ResourceNullException(String message) {
    super(DESCRIPTION + ". " + message);
  }
}
