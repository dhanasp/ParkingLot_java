package com.parkingLot;

import java.util.ArrayList;

public class Attendant {

  private ArrayList<ParkingLot> parkingLots;

  public Attendant(ArrayList<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Object park(Parkable vehicle) throws UnableToParkException {
    for (ParkingLot parkingLot : parkingLots) {
      if (!parkingLot.isFull()) {
        return  parkingLot.park(vehicle);
      }
    }
    throw new UnableToParkException("all lots all full");
  }

  public Object checkout(Object token) throws VehicleNotFoundException {
    for (ParkingLot parkingLot : parkingLots) {
      return  parkingLot.checkout(token);
    }
    throw new VehicleNotFoundException();
  }
}
