package com.parkit.parkingsystem.service;

import java.util.concurrent.TimeUnit;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.TicketReduction;
import com.parkit.parkingsystem.model.Ticket;

/**
 * Service to calculate the price of the ticket
 *
 */
public class FareCalculatorService {
	/**
	 * Calculate the price ticket
	 * @param ticket
	 * @param isRecuringClient
	 */
    public void calculateFare(Ticket ticket, boolean isRecuringClient){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }
        
        float duration = getDuration(ticket);
        
        if (isFreeTime(duration) == true) {
        	ticket.setPrice(0);
        } else {
        	double coefficientReduction = getCoefficientReduction(isRecuringClient);
        	switch (ticket.getParkingSpot().getParkingType()){
	            case CAR: {
	                ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR * coefficientReduction);
	                break;
	            }
	            case BIKE: {
	                ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR * coefficientReduction);
	                break;
	            }
	            default: throw new IllegalArgumentException("Unkown Parking Type");
	        }
        }
    }

    /**
     * Get the duration of the parking
     * 
     * @param ticket
     * @return float
     */
    private float getDuration(Ticket ticket) {
    	long inTime = ticket.getInTime().getTime();
        long outTime = ticket.getOutTime().getTime();

        float durationInMilliseconds = (TimeUnit.MINUTES.convert(outTime - inTime, TimeUnit.MILLISECONDS));
        float duration = durationInMilliseconds / 60;
        
        return duration;
    }
    
    /**
     * Get if the duration is free
     * @param duration
     * @return boolean
     */
    private boolean isFreeTime(float duration) {
        if (duration < TicketReduction.FREE_TIME) {
        	return true;
        }
        return false;
    }
    
    /**
     * Get the coefficient of reduction
     * 
     * @param isRecuringClient
     * @return double
     */
    private double getCoefficientReduction(boolean isRecuringClient) {
    	if (isRecuringClient == true) {
    		return TicketReduction.COEFFICIENT_REDUCTION_FOR_RECURING_CLIENT;
    	}
    	return 1;
    }
}