package main.dal;

import java.util.*;

import main.entity.FlightDetail;

import java.io.*;

class MyObjectOutputStream extends ObjectOutputStream {

	MyObjectOutputStream() throws IOException {
		super();
	}

	MyObjectOutputStream(OutputStream o) throws IOException {
		super(o);
	}

	public void writeStreamHeader() throws IOException {
		return;
	}
}

public class FlightDataDAL {
	private static String filename = "data\\flightData.txt";
	private static File f = new File(filename);

	public static List<FlightDetail> getFlightList() {
		List<FlightDetail> flights = new ArrayList<>();
		try {
			f.createNewFile();
		} catch (Exception e) {
		}
		if (f.length() != 0) {

			try {
				FileInputStream fis = null;

				fis = new FileInputStream(filename);
				ObjectInputStream ois = new ObjectInputStream(fis);

				FlightDetail c = null;

				while (fis.available() != 0) {
					c = (FlightDetail) ois.readObject();
					flights.add(c);

				}
				ois.close();
				fis.close();
			} catch (Exception e) {
				System.out.println("Error Occurred" + e);
				e.printStackTrace();
			}
		}
		return flights;
	}

	public static boolean addNewFlight(FlightDetail c) {
		List<FlightDetail> flights = getFlightList();
		for (FlightDetail f : flights) {
			if (f.getFlightID().equals(c.getFlightID()))
				return false;
		}
		boolean status = false;
		if (c != null) {
			try {
				FileOutputStream fos = null;
				fos = new FileOutputStream(filename, true);
				if (f.length() == 0) {
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(c);
					oos.close();
				} else {

					MyObjectOutputStream oos = null;
					oos = new MyObjectOutputStream(fos);
					oos.writeObject(c);
					oos.close();
				}
				fos.close();
			} catch (Exception e) {
				System.out.println("Error Occurred" + e);
			}
			status = true;
		}

		return status;
	}

	public static void emptyFile() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.print("");
		writer.close();
	}

	public static boolean contains(String flightId) {
		List<FlightDetail> flights = getFlightList();
		boolean contains = false;
		for (FlightDetail f : flights) {
			if (f.getFlightID().equals(flightId))
				contains = true;
		}
		return contains;
	}

	public static boolean removeFlight(String flightID) {
		List<FlightDetail> flights = getFlightList();
		if (!contains(flightID))
			return false;

		emptyFile();

		for (FlightDetail f : flights) {
			if (f.getFlightID().equals(flightID))
				continue;
			else
				addNewFlight(f);
		}
		return true;
	}

	public static FlightDetail getFlightDetail(String flightId) {
		List<FlightDetail> flights = getFlightList();
		for (FlightDetail f : flights) {
			if (f.getFlightID().equals(flightId))
				return f;
		}
		return null;
	}

	public static boolean UpdateFlightData(FlightDetail flight) {
		if (!contains(flight.getFlightID()))
			return false;
		removeFlight(flight.getFlightID());
		addNewFlight(flight);
		return true;
	}

	public static List<FlightDetail> getFlightListByDate(String departureLoc, String arrivalLoc, Date departureDate) {
		List<FlightDetail> flights = getFlightList();
		List<FlightDetail> flightsByDate = new ArrayList<>();
		for (FlightDetail f : flights) {
			
			Date date = f.getDepartureDate();
			
			String date0 = date.getDate() + "/" + date.getMonth() + "/" + date.getYear();
			String date1 = departureDate.getDate() + "/" + departureDate.getMonth() + "/" + departureDate.getYear();

			if (f.getDepartureLoc().equals(departureLoc) && f.getArrivalLoc().equals(arrivalLoc) && date0.equals(date1))
				flightsByDate.add(f);
		}
		return flightsByDate;
	}
}
