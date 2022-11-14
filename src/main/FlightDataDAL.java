package main;

import java.util.*;
import java.io.*;

public class FlightDataDAL {
	
	private static final long serialVersionUID = 6529685098267757690L;

	private static String filename = "data\\flightData.txt";

	public static List<FlightDetail> getFlightList() throws IOException, ClassNotFoundException {
		
		List<FlightDetail> flightList = new ArrayList<>();
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename));
		boolean keepReading = true;
		
		while (keepReading) {

			try {
				// read in serialized account
				FlightDetail read = (FlightDetail) input.readObject();
				// add read in serialized account to the list
				flightList.add(read);

			} catch (EOFException eofe) {
				// we are at the end of file, so stop reading
				keepReading = false;
				input.close();
			} catch (IOException ioe) {
				// input stream error other than EOF, not sure what to do
				input.close();
				throw ioe;
			} catch (ClassNotFoundException cnfe) {
				// not sure what to do in this case, so close input
				input.close();
				throw cnfe;
			}
		}
		
		return flightList;
	}

	public static int writeFlightData(FlightDetail flight) throws ClassNotFoundException, IOException {
//		List<FlightDetail> flights = getFlightList();
//		
//		for (FlightDetail f : flights) {
//			if (f.getFlightID() == flight.getFlightID())
//				return 0;
//		}
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename, true));
		out.writeObject(flight);
		
		return 1;
	}

	public static void removeFlight(String flightId) throws ClassNotFoundException, IOException {
		List<FlightDetail> flights = getFlightList();
		new FileOutputStream(filename).close();
		for (FlightDetail f : flights) {
			if (f.getFlightID() == flightId) {
				continue;
			} else {
				writeFlightData(f);
			}
		}
	}

	public static FlightDetail getFlightDetail(String flightId) throws ClassNotFoundException, IOException {
		
		List<FlightDetail> allFlights = getFlightList();
		
		for (FlightDetail f : allFlights) {
			if (f.getFlightID() == flightId)
				return f;
		}
		
		return null;
	}

	public static List<FlightDetail> getFlightListByDepartureDate(String departureLoc, String arrivalLoc,
			Date departureDate) throws ClassNotFoundException, IOException {
		
		List<FlightDetail> flightList = new ArrayList<>();
		List<FlightDetail> allFlights = getFlightList();
		
		for (FlightDetail f : allFlights) {
			if (f.getArrivalLoc() == arrivalLoc && f.getDepartureLoc() == departureLoc 
					&& f.getArrivalDate() == departureDate)
				flightList.add(f);
		}
		return flightList;
	}
}