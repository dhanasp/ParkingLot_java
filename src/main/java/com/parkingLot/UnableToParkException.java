package com.parkingLot;

public class UnableToParkException extends Throwable {
  public UnableToParkException(String msg) {
    super(msg);
  }
}
