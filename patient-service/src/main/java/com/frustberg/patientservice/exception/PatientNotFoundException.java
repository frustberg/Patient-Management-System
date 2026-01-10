package com.frustberg.patientservice.exception;

public class PatientNotFoundException extends RuntimeException {

  public PatientNotFoundException(String message) {
    super(message);
  }
}
