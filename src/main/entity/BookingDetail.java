package main.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import main.dal.BookingDataDAL;

public class BookingDetail implements Serializable {
	
	private int bookingId;
	private boolean isRoundTrip;
	private String flightID, flightID_Return;
	private int classType;
	private int[] noOfPassengers;
	private double ticketPrice, ticketPrice_Return;
	private Passenger[] passengers;
	
	public BookingDetail(boolean isRoundTrip, String flightID, int classType, int[] noOfPassengers, double ticketPrice,
			Passenger[] passengers) {
		this.bookingId = BookingDataDAL.bookingId;
		this.isRoundTrip = isRoundTrip;
		this.flightID = flightID;
		this.classType = classType;
		this.noOfPassengers = noOfPassengers;
		this.ticketPrice = ticketPrice;
		this.passengers = passengers;
	}

	public BookingDetail(boolean isRoundTrip, String flightID, String flightID_Return, int classType,
			int[] noOfPassengers, double ticketPrice, double ticketPrice_Return, Passenger[] passengers) {
		this.bookingId = BookingDataDAL.bookingId;
		this.isRoundTrip = isRoundTrip;
		this.flightID = flightID;
		this.flightID_Return = flightID_Return;
		this.classType = classType;
		this.noOfPassengers = noOfPassengers;
		this.ticketPrice = ticketPrice;
		this.ticketPrice_Return = ticketPrice_Return;
		this.passengers = passengers;
	}
	
	public String getFlightId(){
		return flightID;
	}

	public int getBookingId() {
		return bookingId;
	}

	@Override
	public String toString() {
		return "BookingDetail [bookingId=" + bookingId + ", isRoundTrip=" + isRoundTrip + ", flightID=" + flightID
				+ ", flightID_Return=" + flightID_Return + ", classType=" + classType + ", noOfPassengers="
				+ Arrays.toString(noOfPassengers) + ", ticketPrice=" + ticketPrice + ", ticketPrice_Return="
				+ ticketPrice_Return + ", passengers=" + Arrays.toString(passengers) + "]";
	}
}
