package com.parkingLot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {

  private ParkingLot parkingLot;
  private Vehicle car;


  private class Vehicle implements Parkable {}

  @Before
  public void setUp() {
    parkingLot = new ParkingLot(10);
    car = new Vehicle();
  }

  @Test
  public void shouldParkCarInParkingLot() throws UnableToParkException {
    Object parkingId = parkingLot.park(car);
    assertNotNull(parkingId);
  }

  @Test
  public void shouldParkTwoCars() throws UnableToParkException {
    Object parkingId = parkingLot.park(car);
    Vehicle car2 = new Vehicle();
    Object parkingIdForSecondCar = parkingLot.park(car2);
    assertNotEquals(parkingId,parkingIdForSecondCar);
  }

  @Test
  public void shouldCheckoutParkedCar() throws UnableToParkException, VehicleNotFoundException {
    Vehicle myCar = new Vehicle();
    Object parkingId = parkingLot.park(myCar);
    assertEquals(parkingLot.checkout(parkingId),myCar);

  }

  @Test(expected = VehicleNotFoundException.class)
  public void shouldNotCheckoutUnParkedCar() throws VehicleNotFoundException {
    Object fakeID = new Object();
    parkingLot.checkout(fakeID);
  }


  @Test
  public void shouldCheckoutSpecificCar() throws UnableToParkException, VehicleNotFoundException {
    Vehicle car1 = new Vehicle();
    Vehicle car2 = new Vehicle();
    Object parkingId = parkingLot.park(car1);
    parkingLot.park(car2);
    Object checkoutCar = parkingLot.checkout(parkingId);
    assertEquals(checkoutCar,car1);
  }

  @Test(expected = UnableToParkException.class)
  public void shouldNotParkSameCarWithoutCheckout() throws UnableToParkException {
    parkingLot.park(car);
    parkingLot.park(car);
  }

  @Test(expected = VehicleNotFoundException.class)
  public void shouldNotCheckoutSameCarTwice() throws UnableToParkException,  VehicleNotFoundException {
    Object token = parkingLot.park(car);
    parkingLot.checkout(token);
    parkingLot.checkout(token);
  }

  @Test
  public void shouldAssertFalseForEmptyLot() {
    assertFalse(parkingLot.isFull());
  }

  @Test
  public void shouldAssertTrueWhenLotIsFull() throws UnableToParkException {
    ParkingLot parkingLotWithCapacity1 = new ParkingLot(1);
    parkingLotWithCapacity1.park(car);
    assertTrue(parkingLotWithCapacity1.isFull());
  }

  @Test(expected = UnableToParkException.class)
  public void shouldNotParkAVehicleWhenParkingLotIsFull() throws UnableToParkException {
    ParkingLot parkingLotWithCapacity0 = new ParkingLot(0);
    parkingLotWithCapacity0.park(car);
  }

  @Test
  public void shouldAssertFalseWhenParkedCarIsCheckout() throws UnableToParkException,  VehicleNotFoundException {
    ParkingLot parkingLotWithCapacity2 = new ParkingLot(2);
    Vehicle car1 = new Vehicle();
    Object token = parkingLotWithCapacity2.park(car1);
    parkingLotWithCapacity2.park(new Vehicle());
    parkingLotWithCapacity2.checkout(token);
    assertFalse(parkingLotWithCapacity2.isFull());
  }
}
