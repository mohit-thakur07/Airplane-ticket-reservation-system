package main.util;

import java.util.List;

import main.dal.BookingDataDAL;
import main.dal.FlightDataDAL;
import main.entity.BookingDetail;
import main.entity.FlightDetail;

public class Test {

	public static void main(String[] args) {
		List<FlightDetail> flights = FlightDataDAL.getFlightList();
		
		for(FlightDetail f : flights){
			System.out.println(f);
			System.out.println();
		}
		
		List<BookingDetail> bookings = BookingDataDAL.getBookingList();
		
		for(BookingDetail b : bookings){
			System.out.println(b);
		}
	}

}
