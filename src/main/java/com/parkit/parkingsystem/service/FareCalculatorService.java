package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

	public void calculateFare(Ticket ticket) {
		if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
			throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
		}
		double oneHour = (double) (60 * 60 * 1000);
		double inHour = ticket.getInTime().getTime() / oneHour;
		double outHour = ticket.getOutTime().getTime() / oneHour;

		double duration = outHour - inHour;

		switch (ticket.getParkingSpot().getParkingType()) {
			case CAR: {
				if (duration <= 0.5) {
					System.out.println("You needn't pay the money! Have a nice days!");
					ticket.setPrice(0);
				} else {
					ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
				}
				break;
			}
			case BIKE: {
				if (duration <= 0.5) {
					System.out.println("You needn't pay the money! Have a nice days!");
					ticket.setPrice(0);
				} else {
					ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unkown Parking Type");
			}
	}

	public void calculateFare(Ticket ticket, double discount) {
		calculateFare(ticket);
		ticket.setPrice(ticket.getPrice() * 0.95);
	}

}