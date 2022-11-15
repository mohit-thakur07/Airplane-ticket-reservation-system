package main.dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import main.entity.BookingDetail;

public class BookingDataDAL {
	private static String filename = "data\\bookingData.txt";
	private static File f = new File(filename);
	public static int bookingId = generateID();

	public static List<BookingDetail> getBookingList() {
		List<BookingDetail> bookings = new ArrayList<>();
		try {
			f.createNewFile();
		} catch (Exception e) {
		}
		if (f.length() != 0) {

			try {
				FileInputStream fis = null;

				fis = new FileInputStream(filename);
				ObjectInputStream ois = new ObjectInputStream(fis);

				BookingDetail c = null;

				while (fis.available() != 0) {
					c = (BookingDetail) ois.readObject();
					bookings.add(c);

				}
				ois.close();
				fis.close();
			} catch (Exception e) {
				System.out.println("Error Occurred" + e);
				e.printStackTrace();
			}
		}
		return bookings;
	}

	public static boolean AddNewBooking(BookingDetail c) {
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

	public static void EmptyFile() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.print("");
		writer.close();
	}

	public static boolean Contains(int bookingId) {
		List<BookingDetail> bookings = getBookingList();
		boolean contains = false;
		for (BookingDetail f : bookings) {
			if (f.getBookingId() == bookingId)
				contains = true;
		}
		return contains;
	}

	public static boolean Removebooking(int bookingID) {
		List<BookingDetail> bookings = getBookingList();
		if (!Contains(bookingID))
			return false;

		EmptyFile();
		for (BookingDetail f : bookings) {
			if (f.getBookingId() == bookingID)
				continue;
			else
				AddNewBooking(f);
		}
		return true;
	}

	public static BookingDetail getBookingDetail(int bookingId) {
		List<BookingDetail> bookings = getBookingList();
		for (BookingDetail b : bookings) {
			if (b.getBookingId() == bookingId)
				return b;
		}
		return null;
	}

	public static int generateID(){
		
		List<BookingDetail> bookings = BookingDataDAL.getBookingList();
		
		if(bookings == null || bookings.size() == 0){
			return 1010010;
		}

		return (bookings.get(bookings.size() - 1).getBookingId()) + 1;
	}
}
