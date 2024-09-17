package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

public class TicketDAOTest {
	private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
	private static DataBasePrepareService dataBasePrepareService;
	private static TicketDAO ticketDAO;
	
	Ticket ticket = new Ticket();
	
	@BeforeAll
	private static void setUp() throws Exception {
		ticketDAO = new TicketDAO();
		ticketDAO.dataBaseConfig = dataBaseTestConfig;
		dataBasePrepareService = new DataBasePrepareService();
	}
	
	@BeforeEach
	private void setUpPerTest() throws Exception {
		dataBasePrepareService.clearDataBaseEntries();
		
		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);		
		Date inTime = new Date();
		inTime.setTime(System.currentTimeMillis());
		
		ticket.setId(1);
		ticket.setParkingSpot(parkingSpot);
		ticket.setVehicleRegNumber("ABCD");
		ticket.setPrice(5.5);
		ticket.setInTime(inTime);
	}
	
	@AfterAll
	private static void tearDown() {
		dataBasePrepareService.clearDataBaseEntries();
	}
	
	@Test
	public void saveTicketTest() {		
		ticketDAO.saveTicket(ticket);		
		assertTrue(ticket.equals(ticketDAO.getTicket("ABCD")));
	}
	
	@Test
	public void updateTicketTest() {
		saveTicketTest();
		ticket.setPrice(10);
		ticket.setOutTime(new Date());
		
		ticketDAO.updateTicket(ticket);
		
		assertEquals(10, ticketDAO.getTicket("ABCD").getPrice());
	}
	
	@Test
	public void isNotRecurringClientTest() {
		boolean isRecurringClient = ticketDAO.isRecurringClient("ABCD");
		assertFalse(isRecurringClient);
	}
	
	@Test
	public void isRecurringClientTest() {
		updateTicketTest();
		saveTicketTest();
		boolean isRecurringClient = ticketDAO.isRecurringClient("ABCD");
		assertTrue(isRecurringClient);
	}
}
