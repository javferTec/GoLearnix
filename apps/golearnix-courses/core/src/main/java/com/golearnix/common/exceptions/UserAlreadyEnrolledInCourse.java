package com.golearnix.common.exceptions;

public class UserAlreadyEnrolledInCourse extends RuntimeException {

  private static final String DESCRIPTION = "USER ALREADY ENROLLED IN COURSE";

  public UserAlreadyEnrolledInCourse(String message) {
    super(DESCRIPTION + ". " + message);
  }

}
