package main;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class FlightDetail implements Serializable {
	private String flightID, airlineName;
	private String departureLoc, arrivalLoc;
	private double distance;
	//ticketPriceFirstClass, ticketPriceBusinessClass, ticketPricePremEconomyClass, ticketPriceRegEconomyClass;
	private double[] ticketPrice = new double[4];
	//seatsFirstClass, seatsBusinessClass, seatsPremEconomyClass, seatsRegEconomyClass;
	private int[] seats = new int[4];
	private int[] allotedSeats = new int[4];
	private Date departureDate, arrivalDate;
	
	public FlightDetail(String flightID, String airlineName, String departureLoc, String arrivalLoc, double distance,
			double[] ticketPrice, int[] seats, Date departureDate, Date arrivalDate) {
		this.flightID = flightID;
		this.airlineName = airlineName;
		this.departureLoc = departureLoc;
		this.arrivalLoc = arrivalLoc;
		this.distance = distance;
		this.ticketPrice = ticketPrice;
		this.seats = seats;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public double[] getTicketPrice() {
		return ticketPrice;
	}

	@Override
	public String toString() {
		return "FlightDetail [flightID=" + flightID + ", airlineName=" + airlineName + ", departureLoc=" + departureLoc
				+ ", arrivalLoc=" + arrivalLoc + ", distance=" + distance + ", ticketPrice="
				+ Arrays.toString(ticketPrice) + ", seats=" + Arrays.toString(seats) + ", allotedSeats="
				+ Arrays.toString(allotedSeats) + ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate
				+ "]";
	}

	public String getFlightID() {
		
		return flightID;
	}

	public String getArrivalLoc() {
		
		return arrivalLoc;
	}

	public String getDepartureLoc() {
		
		return departureLoc;
	}

	public Date getArrivalDate() {
		
		return arrivalDate;
	}	
}
