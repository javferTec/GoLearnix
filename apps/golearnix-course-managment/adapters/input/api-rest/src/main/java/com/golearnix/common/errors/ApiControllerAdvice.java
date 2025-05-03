package com.golearnix.common.errors;

import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.common.exceptions.UserAlreadyEnrolledInCourse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiControllerAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseBody
  public ErrorMessage notFoundRequest(ResourceNotFoundException exception) {
    return new ErrorMessage(exception);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ErrorMessage handleGeneralException(Exception exception) {
    return new ErrorMessage(exception);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ErrorMessage handleValidationException(MethodArgumentNotValidException exception) {

    List<String> validationErrors = exception.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> String.format("Campo '%s': %s", fieldError.getField(),
            fieldError.getDefaultMessage()))
        .collect(Collectors.toList());

    return new ErrorMessage(validationErrors);
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(UserAlreadyEnrolledInCourse.class)
  @ResponseBody
  public ErrorMessage handleUserAlreadyEnrolledInCourseException(UserAlreadyEnrolledInCourse exception) {
    return new ErrorMessage(exception);
  }

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler({AuthorizationDeniedException.class, AccessDeniedException.class})
  @ResponseBody
  public ErrorMessage handleAuthorizationDeniedException(AuthorizationDeniedException exception) {
    return new ErrorMessage(exception);
  }

}
