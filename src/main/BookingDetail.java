package main;

public class BookingDetail {
	
	private boolean isRoundTrip;
	private String flightID, flightID_Return;
	private int classType;
	private int[] noOfPassengers;
	private double ticketPrice, ticketPrice_Return;
	private Passenger[] passengers;
	
	public BookingDetail(boolean isRoundTrip, String flightID, int classType, int[] noOfPassengers, double ticketPrice,
			Passenger[] passengers) {
		this.isRoundTrip = isRoundTrip;
		this.flightID = flightID;
		this.classType = classType;
		this.noOfPassengers = noOfPassengers;
		this.ticketPrice = ticketPrice;
		this.passengers = passengers;
	}

	public BookingDetail(boolean isRoundTrip, String flightID, String flightID_Return, int classType,
			int[] noOfPassengers, double ticketPrice, double ticketPrice_Return, Passenger[] passengers) {
		this.isRoundTrip = isRoundTrip;
		this.flightID = flightID;
		this.flightID_Return = flightID_Return;
		this.classType = classType;
		this.noOfPassengers = noOfPassengers;
		this.ticketPrice = ticketPrice;
		this.ticketPrice_Return = ticketPrice_Return;
		this.passengers = passengers;
	}
}
