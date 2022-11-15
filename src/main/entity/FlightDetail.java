package main.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class FlightDetail implements Serializable {
	private String flightID, airlineName;
	private String departureLoc, arrivalLoc;
	private double distance;
	//ticketPriceRegEconomyClass, ticketPricePremEconomyClass, ticketPriceBusinessClass, ticketPriceFirstClass;
	private double[] ticketPrice = new double[4];
	//seatsRegEconomyClass, seatsPremEconomyClass, seatsBusinessClass, seatsFirstClass;
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

//	@Override
//	public String toString() {
//		return "FlightDetail [flightID=" + flightID + ", airlineName=" + airlineName + ", departureLoc=" + departureLoc
//				+ ", arrivalLoc=" + arrivalLoc + ", distance=" + distance + ", ticketPrice="
//				+ Arrays.toString(ticketPrice) + ", seats=" + Arrays.toString(seats) + ", allotedSeats="
//				+ Arrays.toString(allotedSeats) + ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate
//				+ "]";
//	}
	
	@Override
	public String toString() {
		return " FlightID=" + flightID + "\n Airline Name = " + airlineName + "\n Departure Location = " + departureLoc
				+ "\n Arrival Location = " + arrivalLoc + "\n Distance = " + distance + "\n Ticket Prices = "
				+ Arrays.toString(ticketPrice) + "\n seats = " + Arrays.toString(seats) + "\n Alloted Seats = "
				+ Arrays.toString(allotedSeats) + "\n Departure Date = " + departureDate + "\n Arrival Date = " + arrivalDate;
				//+ "\n Depature Time = " + depatureTime + "\n Arrival Time = " + ArrivalTime ;
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

	public Date getDepartureDate() {
		
		return departureDate;
	}
	
	public Date getArrivalDate() {
		
		return arrivalDate;
	}
	
	public synchronized boolean bookSeats(int noOfPassengers, int flightClass){
		
		int availableSeats = seats[flightClass] - allotedSeats[flightClass];
		
		if(availableSeats >= noOfPassengers){
			allotedSeats[flightClass] += noOfPassengers;
			return true;
		}
		
		return false;
	}
	
	public synchronized void cancelSeats(int noOfPassengers, int flightClass){
		allotedSeats[flightClass] -= noOfPassengers;
	}
}
