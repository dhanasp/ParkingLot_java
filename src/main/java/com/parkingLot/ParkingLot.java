package com.parkingLot;

import java.util.HashMap;

public class ParkingLot {
  private HashMap<Object, Parkable> vehicles;
  private int capacity;

  public ParkingLot(int capacity) {
    this.capacity = capacity;
    this.vehicles =  new HashMap<>();
  }

  public Object park(Parkable vehicle) throws UnableToParkException {
    canPark(vehicle);
    Object token=new Object();
    vehicles.put(token,vehicle);
    return token;
  }

  private void canPark(Parkable vehicle) throws UnableToParkException {
    if (isAlreadyParked(vehicle)){
      throw new UnableToParkException("Vehicle is already parked");
    }
    if(isFull()) {
      throw new UnableToParkException("Lot is full");
    }
  }

  private boolean isAlreadyParked(Parkable vehicle) {
    return vehicles.containsValue(vehicle);
  }

  public Object checkout(Object token) throws VehicleNotFoundException {
    if (!has(token)){
      throw new VehicleNotFoundException();
    }
    return vehicles.remove(token);
  }

  private boolean has(Object token) {
    return vehicles.containsKey(token);
  }


  public boolean isFull() {
    return vehicles.size() == capacity;
  }
}
