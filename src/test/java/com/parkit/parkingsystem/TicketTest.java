package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

public class TicketTest {
	
	private static Ticket ticket;
	
	@BeforeEach
	private void setUpPerTest() {
		ticket = new Ticket();
	}
	
	@Test
	public void setAngGetIdTest() {
		ticket.setId(1);
		assertEquals(1, ticket.getId());
	}
	
	@Test
	public void setAngGetParkingSpotTest() {
		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
		ticket.setParkingSpot(parkingSpot);
		assertEquals(parkingSpot, ticket.getParkingSpot());
	}
	
	@Test
	public void setAngGetVehiculeNumberTest() {
		ticket.setVehicleRegNumber("AA");
		assertEquals("AA", ticket.getVehicleRegNumber());
	}
	
	@Test
	public void setAngGetPriceTest() {
		ticket.setPrice(10.5);
		assertEquals(10.5, ticket.getPrice());
	}
	
	@Test
	public void setAngGetInTimeTest() {
		Date inDate = new Date();
		ticket.setInTime(inDate);
		assertEquals(inDate, ticket.getInTime());
	}
	
	@Test
	public void setAngGetoutTimeTest() {
		Date outDate = new Date();
		ticket.setOutTime(outDate);
		assertEquals(outDate, ticket.getOutTime());
	}
	
	@Test
	public void equalsTest() {
		ticket.setId(1);
		Ticket ticket1 = new Ticket();
		ticket1.setId(1);
		Ticket ticket2 = new Ticket();
		ticket2.setId(2);
		Date inDate = new Date();
		
		assertTrue(ticket.equals(ticket));
		assertTrue(ticket.equals(ticket1));
		assertFalse(ticket.equals(ticket2));
		assertFalse(ticket.equals(null));
		assertFalse(ticket.equals(inDate));
	}
	
	@Test
	public void hashCodeTest() {
		ticket.setId(1);
		assertEquals(1, ticket.hashCode());
	}
}
