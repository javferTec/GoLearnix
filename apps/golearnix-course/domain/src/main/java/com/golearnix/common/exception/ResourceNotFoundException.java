package com.golearnix.common.exception;

public class ResourceNotFoundException extends RuntimeException {

  private static final String DESCRIPTION = "RESOURCE NOT FOUND. ";

  public ResourceNotFoundException(String message) {
    super(DESCRIPTION + ". " + message);
  }
}
