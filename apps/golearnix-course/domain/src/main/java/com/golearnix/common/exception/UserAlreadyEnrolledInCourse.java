package com.golearnix.common.exception;

public class UserAlreadyEnrolledInCourse extends RuntimeException {

  private static final String DESCRIPTION = "USER ALREADY ENROLLED IN COURSE. ";

  public UserAlreadyEnrolledInCourse(String message) {
    super(DESCRIPTION + ". " + message);
  }

}
