package com.aditya_ecommerce.exceptions;

public class InvalidUserNameOrPasswordException extends RuntimeException {
  public InvalidUserNameOrPasswordException(String message) {
    super(message);
  }
}
