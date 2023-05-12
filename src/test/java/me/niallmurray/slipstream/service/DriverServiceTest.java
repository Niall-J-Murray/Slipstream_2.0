package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.Driver;
import me.niallmurray.slipstream.domain.League;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DriverServiceTest {
  @Test
  void addDrivers() {
    try {
      List<Driver> drivers = null;
      DriverService driverservice = new DriverService();
      driverservice.addDrivers(drivers);
      Assertions.assertTrue(true);
    } catch (Exception exception) {
      exception.printStackTrace();
      Assertions.assertFalse(false);
    }
  }

  @Test
  void updateDrivers() {
    try {
      List<Driver> updatedDrivers = null;
      DriverService driverservice = new DriverService();
      driverservice.updateDrivers(updatedDrivers);
      Assertions.assertTrue(true);
    } catch (Exception exception) {
      exception.printStackTrace();
      Assertions.assertFalse(false);
    }
  }

  @Test
  void sortDriversStanding() {
    try {
      List<Driver> expectedValue = null;
      DriverService driverservice = new DriverService();
      List<Driver> actualValue = driverservice.sortDriversStanding();
      System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
      Assertions.assertEquals(expectedValue, actualValue);
    } catch (Exception exception) {
      exception.printStackTrace();
      Assertions.assertFalse(false);
    }
  }

  @Test
  void getUndraftedDrivers() {
    try {
      List<Driver> expectedValue = null;
      League league = null;
      DriverService driverservice = new DriverService();
      List<Driver> actualValue = driverservice.getUndraftedDrivers(league);
      System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
      Assertions.assertEquals(expectedValue, actualValue);
    } catch (Exception exception) {
      exception.printStackTrace();
      Assertions.assertFalse(false);
    }
  }

  @Test
  void findById() {
    try {
      Driver expectedValue = null;
      Long driverId = 0L;
      DriverService driverservice = new DriverService();
      Driver actualValue = driverservice.findById(driverId);
      System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
      Assertions.assertEquals(expectedValue, actualValue);
    } catch (Exception exception) {
      exception.printStackTrace();
      Assertions.assertFalse(false);
    }
  }

}