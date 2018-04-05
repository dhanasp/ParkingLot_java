package com.parkingLot;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AttendantTest {

  private ArrayList<ParkingLot> parkingLots;

  private class Vehicle implements Parkable {}

  @Before
  public void setUp() throws Exception {
    parkingLots = new ArrayList<>();
  }

  @Test
  public void shouldParkInParkingLot() throws UnableToParkException {
    ParkingLot parkingLot = new ParkingLot(10);
    parkingLots.add(parkingLot);
    Attendant attendant = new Attendant(parkingLots);
    Object token = attendant.park(new Vehicle());
    assertNotNull(token);
  }

  @Test
  public void shouldBeAbleToParkThoughFirstLotIsFull() throws UnableToParkException {
    parkingLots.add(new ParkingLot(0));
    parkingLots.add(new ParkingLot(2));
    Attendant attendant = new Attendant(parkingLots);
    Object token = attendant.park(new Vehicle());
    assertNotNull(token);
  }

  @Test(expected = UnableToParkException.class)
  public void shouldNotBeAbleToParkIfAllLotsAreFull() throws UnableToParkException {
    parkingLots.add(new ParkingLot(0));
    parkingLots.add(new ParkingLot(1));
    Attendant attendant = new Attendant(parkingLots);
    attendant.park(new Vehicle());
    attendant.park(new Vehicle());
  }

  @Test
  public void shouldBeAbleToCheckoutAParkedVehicle() throws UnableToParkException, VehicleNotFoundException {
    parkingLots.add(new ParkingLot(1));
    Attendant attendant = new Attendant(parkingLots);
    Vehicle vehicle = new Vehicle();
    Object token = attendant.park(vehicle);
    Object checkoutCar = attendant.checkout(token);
    assertEquals(checkoutCar,vehicle);

  }

  @Test(expected = VehicleNotFoundException.class)
  public void shouldNotBeAbleToCheckoutACarThatIsNotPresent() throws VehicleNotFoundException {
    parkingLots.add(new ParkingLot(1));
    parkingLots.add(new ParkingLot(1));
    Attendant attendant = new Attendant(parkingLots);
    Object fakeToken = new Object();
    attendant.checkout(fakeToken);
  }


}
