package main.bookingData;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import main.dal.BookingDataDAL;
import main.dal.FlightDataDAL;
import main.entity.BookingDetail;
import main.entity.FlightDetail;
import main.entity.Passenger;
import main.util.JListComponent;
import main.util.JListComponent1;
import main.util.Values;

public class BookFlightGUI extends JFrame {

	private JPanel mainPane, panel, panel1, panel2;

	private JLabel lblDataValidation;
	private JButton btnSubmit;

	private String departure_location;
	private String arrival_location;
	private int flightClass;
	private int numberOfInfants;
	private int numberOfChildren;
	private int numberOfAdults;
	private double ticketPrice, ticketPrice_return;
	// private String specialCategory;
	private boolean isRoundTrip;
	private Date departureDate;
	private Date returnDate;
	private String flightID, flightID_return;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BookFlightGUI frame = new BookFlightGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public BookFlightGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 700, 600);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		panel2 = new JPanel();
		panel2.setBounds(0, 0, 700, 560);
		mainPane.add(panel2);
		panel2.setVisible(false);
		// getPassengerDetails();

		panel1 = new JPanel();
		panel1.setBounds(0, 0, 700, 560);
		mainPane.add(panel1);
		panel1.setLayout(null);
		panel1.setVisible(false);
		// addflightListInPanel();

		panel = new JPanel();
		panel.setBounds(0, 0, 700, 560);
		mainPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(true);

		addSelectFlightDetailsPanel();
	}

	private void getPassengerDetails() {
		panel2.setLayout(null);
		JLabel label_4 = new JLabel("Add Passenger Details:");
		label_4.setBounds(15, 16, 173, 20);
		panel2.add(label_4);

		JPanel nestedPanel = new JPanel();
		nestedPanel.setBackground(Color.WHITE);
		nestedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		int totalPassengers = numberOfInfants + numberOfChildren + numberOfAdults;

		Passenger[] passengers = new Passenger[totalPassengers];

		JListComponent1[] passengerData = new JListComponent1[totalPassengers];

		for (int i = 0; i < totalPassengers; i++) {
			passengerData[i] = new JListComponent1();
			nestedPanel.add(passengerData[i]);
		}

		JScrollPane scrollPane = new JScrollPane(nestedPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(44, 62, 550, 250);
		panel2.add(scrollPane);

		JLabel lblDatavalidation = new JLabel("DataValidation");
		lblDatavalidation.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatavalidation.setBounds(44, 417, 550, 20);
		lblDatavalidation.setVisible(false);
		panel2.add(lblDatavalidation);

		JButton btnBookTicket = new JButton("Book Ticket");
		btnBookTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (int i = 0; i < totalPassengers; i++) {

						String name = passengerData[i].getName();
						String gender = passengerData[i].getGender();
						long mobileNo = passengerData[i].getMobileNo();

						if (name.equals("")) {
							throw new Exception("Name cant be null");
						}

						if (mobileNo == 0) {
							throw new Exception("mobile no cant be null");
						}

						passengers[i] = new Passenger(name, gender, mobileNo);
					}

					if (isRoundTrip) {
						boolean bookingStatus, bookingStatus_return;
						
						FlightDetail flight = FlightDataDAL.getFlightDetail(flightID);
						FlightDetail flight_return = FlightDataDAL.getFlightDetail(flightID_return);
						
						bookingStatus = flight.bookSeats(totalPassengers, flightClass);
						bookingStatus_return = flight_return.bookSeats(totalPassengers, flightClass);
						
						if(bookingStatus ^ bookingStatus_return){
							if(!bookingStatus){
								flight.cancelSeats(totalPassengers, flightClass);
							}
							else if(!bookingStatus_return){
								flight_return.cancelSeats(totalPassengers, flightClass);
							}
						}
						else if(bookingStatus && bookingStatus_return){
							BookingDetail booking = new BookingDetail(isRoundTrip, flightID, flightID_return, flightClass,
								new int[] { numberOfInfants, numberOfChildren, numberOfAdults }, ticketPrice,
								ticketPrice_return, passengers);
							
							if(BookingDataDAL.AddNewBooking(booking)){
								
								FlightDataDAL.UpdateFlightData(flight);
								FlightDataDAL.UpdateFlightData(flight_return);
								
								setVisible(false);
								JFrame frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Booking Added Successfully.\nPlease Note your booking id\nBooking ID: " + booking.getBookingId());
								
								System.out.println(booking.getBookingId());
							}
							else{
								System.out.println("Flight cant be booked");
							}
							
						} else{
							System.out.println();
						}
					} else {

						FlightDetail flight = FlightDataDAL.getFlightDetail(flightID);
						
						if (flight.bookSeats(totalPassengers, flightClass)) {

							BookingDetail booking = new BookingDetail(isRoundTrip, flightID, flightClass,
									new int[] { numberOfInfants, numberOfChildren, numberOfAdults }, ticketPrice,
									passengers);
							if(BookingDataDAL.AddNewBooking(booking)){
								
								FlightDataDAL.UpdateFlightData(flight);
								setVisible(false);
								JFrame frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Booking Added Successfully.\nPlease Note your booking id\nBooking ID: " + booking.getBookingId());
								//showDialogBox("Booking Added Successfully.\nPlease Note your booking id\nBooking ID: " + booking.getBookingId());
								
								
								System.out.println(booking.getBookingId());
							}
							else{
								System.out.println("Flight cant be booked");
							}
						} else{
							System.out.println();
						}
					}
				} catch (Exception e) {
					lblDatavalidation.setVisible(true);
					lblDatavalidation.setText(e.getMessage());
				}
			}
		});
		btnBookTicket.setBounds(250, 340, 115, 29);
		panel2.add(btnBookTicket);
	}

	private void addflightListInPanel() {

		JLabel label_4 = new JLabel("Available flights:");
		label_4.setBounds(15, 16, 173, 20);
		panel1.add(label_4);

		JPanel nestedPanel = new JPanel();
		nestedPanel.setBackground(Color.WHITE);
		nestedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblSelectionDetails = new JLabel("Selection Details:");
		lblSelectionDetails.setBounds(43, 406, 121, 20);
		panel1.add(lblSelectionDetails);

		JLabel lblDepartureFlight = new JLabel("Departure Flight:");
		lblDepartureFlight.setBounds(106, 436, 126, 20);
		panel1.add(lblDepartureFlight);

		JTextPane txtpnDepartureFlightDetails = new JTextPane();
		txtpnDepartureFlightDetails.setEditable(false);
		txtpnDepartureFlightDetails.setText("");
		txtpnDepartureFlightDetails.setBounds(266, 430, 300, 26);
		panel1.add(txtpnDepartureFlightDetails);

		List<FlightDetail> flights = null;
		List<JListComponent> flightComponents = new ArrayList<>();

		try {
			flights = FlightDataDAL.getFlightListByDate(this.departure_location, this.arrival_location,
					this.departureDate);

			for (FlightDetail f : flights) {

				JListComponent c = new JListComponent(f.getFlightID(), f.getAirlineName(),
						f.getDepartureDate().toLocaleString(), f.getArrivalDate().toLocaleString(),
						f.getTicketPrice()[flightClass]);
				flightComponents.add(c);

				c.btnSelectFlight.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						for (JListComponent fc : flightComponents) {
							fc.setBackground(Color.LIGHT_GRAY);
						}

						flightID = f.getFlightID();
						ticketPrice = f.getTicketPrice()[flightClass];

						txtpnDepartureFlightDetails.setText("ID: " + flightID + " Airline: " + f.getAirlineName()
								+ " Price: " + f.getTicketPrice()[flightClass]);
						c.setBackground(Color.GREEN);
					}
				});

				nestedPanel.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error while fetching data!!!");
		}

		JScrollPane scrollPane = new JScrollPane(nestedPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(44, 62, 551, 150);
		panel1.add(scrollPane);

		if (isRoundTrip) {
			JPanel nestedPanel1 = new JPanel();
			nestedPanel1.setBackground(Color.WHITE);
			nestedPanel1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

			JLabel lblReturnFlight = new JLabel("Return Flight:");
			lblReturnFlight.setBounds(106, 472, 126, 20);
			panel1.add(lblReturnFlight);

			JTextPane txtpnReturnFlightDetails = new JTextPane();
			txtpnReturnFlightDetails.setEditable(false);
			txtpnReturnFlightDetails.setText("");
			txtpnReturnFlightDetails.setBounds(266, 466, 300, 26);
			panel1.add(txtpnReturnFlightDetails);

			List<FlightDetail> return_flights = null;
			List<JListComponent> returnFlightComponents = new ArrayList<>();

			try {
				return_flights = flights = FlightDataDAL.getFlightListByDate(this.arrival_location,
						this.departure_location, this.returnDate);

				for (FlightDetail f : return_flights) {

					JListComponent c = new JListComponent(f.getFlightID(), f.getAirlineName(),
							f.getDepartureDate().toLocaleString(), f.getArrivalDate().toLocaleString(),
							ticketPrice_return);
					returnFlightComponents.add(c);

					c.btnSelectFlight.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							for (JListComponent fc : returnFlightComponents) {
								fc.setBackground(Color.LIGHT_GRAY);
							}

							flightID_return = f.getFlightID();
							ticketPrice_return = f.getTicketPrice()[flightClass];

							txtpnReturnFlightDetails.setText("ID: " + flightID_return + " Airline: "
									+ f.getAirlineName() + " Price: " + f.getTicketPrice()[flightClass]);
							c.setBackground(Color.GREEN);
						}
					});

					nestedPanel1.add(c);
				}
			} catch (Exception e) {
				System.out.println("Error while fetching data!!!");
			}

			JScrollPane scrollPane1 = new JScrollPane(nestedPanel1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane1.setBounds(44, 230, 551, 150);
			panel1.add(scrollPane1);
		}

		JButton btnNextStep = new JButton("Next Step");
		btnNextStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (flightID != null && !(isRoundTrip ^ flightID_return != null)) {

					panel1.setVisible(false);
					panel2.setVisible(true);
					getPassengerDetails();
				}
			}
		});
		btnNextStep.setBounds(288, 515, 115, 29);
		panel1.add(btnNextStep);
	}

	private void addSelectFlightDetailsPanel() {
		JLabel lblDepartureLocation = new JLabel("Departure location:");
		lblDepartureLocation.setBounds(7, 47, 146, 20);
		panel.add(lblDepartureLocation);

		JComboBox<String> comboBoxDepartureLocation = new JComboBox<>();
		comboBoxDepartureLocation.setBounds(10, 70, 230, 25);
		for (String s : Values.locations) {
			comboBoxDepartureLocation.addItem(s);
		}
		panel.add(comboBoxDepartureLocation);

		JLabel lblArrivalLocation = new JLabel("Arrival location:");
		lblArrivalLocation.setBounds(250, 47, 146, 20);
		panel.add(lblArrivalLocation);

		JComboBox<String> comboBoxArrivalLocation = new JComboBox<>();
		comboBoxArrivalLocation.setBounds(250, 70, 230, 25);
		for (String s : Values.locations) {
			comboBoxArrivalLocation.addItem(s);
		}
		panel.add(comboBoxArrivalLocation);

		JLabel lblFlightClass = new JLabel("Select Flight Class:");
		lblFlightClass.setBounds(10, 118, 143, 25);
		panel.add(lblFlightClass);

		String[] flightClassList = { "Regular Economy", "Premium Economy", "First class", "Business class" };
		JComboBox<String> comboBoxFlightClass = new JComboBox<>();
		for (String s : flightClassList) {
			comboBoxFlightClass.addItem(s);
		}
		comboBoxFlightClass.setBounds(12, 150, 220, 25);
		panel.add(comboBoxFlightClass);

		JLabel lblPassengers = new JLabel("Passengers:");
		lblPassengers.setBounds(5, 216, 110, 20);
		panel.add(lblPassengers);

		JSpinner spinnerInfants = new JSpinner();
		spinnerInfants.setBounds(20, 245, 110, 26);
		panel.add(spinnerInfants);

		JLabel label = new JLabel("Infants under 2 yrs");
		label.setBounds(20, 275, 120, 20);
		panel.add(label);

		JSpinner spinnerChildren = new JSpinner();
		spinnerChildren.setBounds(160, 245, 110, 26);
		panel.add(spinnerChildren);

		JLabel label_1 = new JLabel("Children 2-11 yrs");
		label_1.setBounds(160, 275, 98, 20);
		panel.add(label_1);

		JSpinner spinnerAdults = new JSpinner();
		spinnerAdults.setBounds(315, 245, 110, 26);
		panel.add(spinnerAdults);

		JLabel label_2 = new JLabel("Adult 12+ years");
		label_2.setBounds(325, 275, 110, 20);
		panel.add(label_2);

		// String[] specialCategoryList = { "", "Armed Forces", "Senior
		// Citizen", "Doctors & Nurses", "Student" };
		// JComboBox<String> comboBoxSpecialCategory = new JComboBox<>();
		// comboBoxSpecialCategory.setBounds(470, 245, 146, 26);
		// for (String s : specialCategoryList) {
		// comboBoxSpecialCategory.addItem(s);
		// }
		// panel.add(comboBoxSpecialCategory);
		//
		// JLabel lblSpecialCategory = new JLabel("Special Category
		// (optional)");
		// lblSpecialCategory.setBounds(465, 220, 198, 20);
		// panel.add(lblSpecialCategory);

		JLabel lblDepartureDate = new JLabel("Departure date:");
		lblDepartureDate.setBounds(15, 356, 146, 20);
		panel.add(lblDepartureDate);

		JLabel lblArrivalDate = new JLabel("Return date:");
		lblArrivalDate.setEnabled(false);
		lblArrivalDate.setBounds(301, 353, 146, 27);
		panel.add(lblArrivalDate);

		JDateChooser dateChooserDepartureDate = new JDateChooser();
		dateChooserDepartureDate.setBounds(139, 354, 119, 26);
		dateChooserDepartureDate.setMinSelectableDate(new Date());
		panel.add(dateChooserDepartureDate);

		JDateChooser dateChooserArrivalDate = new JDateChooser();
		dateChooserArrivalDate.setBounds(402, 354, 119, 26);
		dateChooserArrivalDate.setMinSelectableDate(new Date());
		dateChooserArrivalDate.setEnabled(false);
		panel.add(dateChooserArrivalDate);

		JCheckBox chckbxRoundTrip = new JCheckBox("Round Trip");
		chckbxRoundTrip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblArrivalDate.setEnabled(!lblArrivalDate.isEnabled());
				dateChooserArrivalDate.setEnabled(!dateChooserArrivalDate.isEnabled());
			}
		});
		chckbxRoundTrip.setBounds(7, 315, 139, 29);
		panel.add(chckbxRoundTrip);

		btnSubmit = new JButton("Search Flights");
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				try {
					departure_location = Values.locations[comboBoxDepartureLocation.getSelectedIndex()];

					if (departure_location == "") {
						throw new Exception("Enter Departure Location");
					}

					arrival_location = Values.locations[comboBoxArrivalLocation.getSelectedIndex()];

					if (arrival_location == "") {
						throw new Exception("Enter Arrival Location");
					}

					if (departure_location.equals(arrival_location)) {
						throw new Exception("Departure and arrival location cant be same");
					}

					flightClass = comboBoxFlightClass.getSelectedIndex();

					numberOfInfants = (int) spinnerInfants.getValue();
					numberOfChildren = (int) spinnerChildren.getValue();
					numberOfAdults = (int) spinnerAdults.getValue();

					if (numberOfInfants < 0) {
						throw new Exception("Negative value not allowed");
					}
					if (numberOfChildren < 0) {
						throw new Exception("Negative value not allowed");
					}
					if (numberOfAdults < 0) {
						throw new Exception("Negative value not allowed");
					}
					if (numberOfChildren + numberOfAdults + numberOfInfants == 0) {
						throw new Exception("The total number of passengers should be more than 0");
					}

					// specialCategory =
					// specialCategoryList[comboBoxSpecialCategory.getSelectedIndex()];

					departureDate = dateChooserDepartureDate.getDate();

					if (departureDate == null) {
						throw new Exception("Departure date shouldnt be Empty");
					}

					isRoundTrip = chckbxRoundTrip.isSelected();

					if (isRoundTrip) {

						returnDate = dateChooserArrivalDate.getDate();

						if (returnDate == null) {
							throw new Exception("Return date shouldnt be Empty");
						}
					}

					panel.setVisible(false);
					panel1.setVisible(true);
					addflightListInPanel();

				} catch (Exception ex) {
					lblDataValidation.setVisible(true);

					if (ex.getMessage() != null) {
						lblDataValidation.setText(ex.getMessage());
					} else {
						lblDataValidation.setText("Error in submitting data");
					}
				}
			}
		});
		btnSubmit.setBounds(257, 442, 115, 29);
		panel.add(btnSubmit);

		lblDataValidation = new JLabel("Data Validation");
		lblDataValidation.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataValidation.setBounds(115, 492, 400, 29);
		lblDataValidation.setVisible(false);
		panel.add(lblDataValidation);
	}
	
//	public void showDialogBox(String text){
//		JFrame frame = new JFrame();
//        JDialog dialog = new JDialog(frame, true);
//        dialog.setLayout(new FlowLayout());
// 
//        JLabel displayText = new JLabel(text);
//        JButton btn = new JButton("OK");
//       
//        btn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                dialog.setVisible(false);
//            }
//        });
// 
//        dialog.add(displayText);
//        dialog.add(btn);
// 
//        dialog.setSize(200,150);
//        dialog.setTitle("Dialog Window");
//        dialog.setVisible(true);
//        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//	}
}