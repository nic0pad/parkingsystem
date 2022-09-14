package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

public class ParkingSpotTest {
	
	private static ParkingSpot parkingSpot;

	@BeforeEach
	private void setUpPerTest() {
		parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
	}
	
	@Test
	public void getIdTest() {
		assertEquals(1, parkingSpot.getId());
	}
	
	@Test
	public void setIdTest() {
		parkingSpot.setId(5);
		assertEquals(5, parkingSpot.getId());
	}
	
	@Test
	public void getParkingTypeTest() {
		assertEquals(ParkingType.CAR, parkingSpot.getParkingType());
	}
	
	@Test
	public void setParkingTypeTest() {
		parkingSpot.setParkingType(ParkingType.BIKE);
		assertEquals(ParkingType.BIKE, parkingSpot.getParkingType());
	}
	
	@Test
	public void getAvailableTest() {
		assertTrue(parkingSpot.isAvailable());
	}
	
	@Test
	public void setAvailableTest() {
		parkingSpot.setAvailable(false);
		assertTrue(!parkingSpot.isAvailable());
	}
	
	@Test
	public void equalsTest() {
		ParkingSpot parkingSpot1 = new ParkingSpot(1, ParkingType.CAR, true);
		ParkingSpot parkingSpot2 = new ParkingSpot(20, ParkingType.BIKE, false);
		Ticket ticket = new Ticket();
		
		assertTrue(parkingSpot.equals(parkingSpot1));
		assertFalse(parkingSpot.equals(parkingSpot2));
		assertFalse(parkingSpot.equals(null));
		assertFalse(parkingSpot.equals(ticket));
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(1, parkingSpot.hashCode());
	}
}
