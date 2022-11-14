package main;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.FlowLayout;

public class BookFlightGUI extends JFrame {

	private JPanel mainPane, panel, panel1;

	private JLabel lblDataValidation;
	private JButton btnSubmit;

	private JList<JListComponent> list_1;
	
	private String departure_location;
	private String arrival_location;
	private int flightClass;
	private int numberOfInfants;
	private int numberOfChildren;
	private int numberOfAdults;
	private String specialCategory;
	private boolean isRoundTrip;
	private Date departureDate;
	private Date returnDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookFlightGUI frame = new BookFlightGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookFlightGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		panel1 = new JPanel();
		panel1.setBounds(0, 0, 670, 540);
		mainPane.add(panel1);
		panel1.setLayout(null);
		panel1.setVisible(false);
		//addflightListInPanel();

		panel = new JPanel();
		panel.setBounds(0, 0, 670, 540);
		mainPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(true);

		addSelectFlightDetailsPanel();
	}

	private void addflightListInPanel() {
		JLabel label_4 = new JLabel("Available flights:");
		label_4.setBounds(15, 16, 173, 20);
		panel1.add(label_4);
		
		JPanel nestedPanel = new JPanel();
		nestedPanel.setBackground(Color.WHITE);
		nestedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		FlightDetail[] flight = new FlightDetail[1];
		try {
			flight[0] = FlightDataDAL.getFlightList().get(0);
		} catch (ClassNotFoundException | IOException e) {
			
			e.printStackTrace();
		}
		
		List<JListComponent> components = new ArrayList<>();
		
		for(FlightDetail f: flight){
			components.add(new JListComponent(f.getAirlineName(), "subah", "shaam", f.getTicketPrice()[flightClass]));
		}
		
		for(JListComponent c : components){
			nestedPanel.add(c);
		}
		
		for(int i = 0; i < 10; i++){
			nestedPanel.add(new JListComponent("air india", "subah", "shaam", 1000.0 + i * 10));
		}
		  
		JScrollPane scrollPane = new JScrollPane(nestedPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,  
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(44, 62, 551, 150);
		panel1.add(scrollPane);
	}
	
	private void addSelectFlightDetailsPanel(){
		JLabel lblDepartureLocation = new JLabel("Departure location:");
		lblDepartureLocation.setBounds(7, 47, 146, 20);
		panel.add(lblDepartureLocation);

		String[] departure_airport_locations = { "", "Rajiv Gandhi International Airport HYD",
				"Pondicherry Airport PNY", "Safdarjung Airport VIDD", "Kempegowda International Airport BLR",
				"Mangalore International Airport IXE", "Kannur International Airport CNN",
				"Cochin International Airport COK", "Sabarimala International Airport",
				"Calicut International Airport CCJ", "Chhatrapati Shivaji Maharaj International Airport BOM",
				"Navi Mumbai International Airport", "Ludhiana International Airport VIHX",
				"Jaipur International Airport JAI", "Kurnool Airport KJB", "Hollongi Airport HGI",
				"Pasighat Airport IXT", "Rupsi " + "Airport RUP", "Swami Vivekananda Airport RPR", "Kullu Airport KUU",
				"Hubli Airport HBX", "Mysore Airport MYQ", "Chhatrapati Rajaram Maharaj Airport KLH",
				"Shillong Airport SHL", "Tirupati Airport TIR", "Vijayawada Airport VGA", "Gaya Airport GAY",
				"Jay Prakash Narayan Airport PAT", "Dabolim Airport GOI", "Mopa Airport GOX", "Rajkot Airport RAJ",
				"Sardar Vallabhbhai Patel International" + " Airport AMD", "Nashik International Airport ISK",
				"Chennai" + " International Airport MAA", "Jaipur International Airport JAI" };
		JComboBox<String> comboBoxDepartureLocation = new JComboBox<>();
		comboBoxDepartureLocation.setBounds(10, 70, 230, 25);
		for (String s : departure_airport_locations) {
			comboBoxDepartureLocation.addItem(s);
		}
		panel.add(comboBoxDepartureLocation);

		JLabel lblArrivalLocation = new JLabel("Arrival location:");
		lblArrivalLocation.setBounds(250, 47, 146, 20);
		panel.add(lblArrivalLocation);

		String[] arrival_airport_locations = { "", "Dabolim Airport GOI", "Navi Mumbai International Airport",
				"Ludhiana International Airport VIHX", "Jaipur International Airport JAI", "Kurnool Airport KJB",
				"Hollongi Airport HGI", "Pasighat Airport IXT", "Sabarimala International Airport",
				"Calicut International Airport CCJ", "Chhatrapati Shivaji Maharaj International Airport BOM",
				"Rupsi " + "Airport RUP", "Swami Vivekananda Airport RPR", "Kullu Airport KUU", "Gaya Airport GAY",
				"Jay Prakash Narayan Airport PAT", "Dabolim Airport GOI", "Mopa Airport GOX", "Rajkot Airport RAJ",
				"Kempegowda International Airport BLR", "Mangalore International Airport IXE",
				"Sardar Vallabhbhai Patel International" + " Airport AMD", "Nashik International Airport ISK",
				"Hubli Airport HBX", "Mysore Airport MYQ", "Chhatrapati Rajaram Maharaj Airport KLH",
				"Shillong Airport SHL", "Tirupati Airport TIR", "Vijayawada Airport VGA",
				"Rajiv Gandhi International Airport HYD", "Pondicherry Airport PNY", "Safdarjung Airport VIDD",
				"Kannur International Airport CNN", "Cochin International Airport COK",
				"Chennai International Airport MAA", "Jaipur International Airport JAI" };

		JComboBox<String> comboBoxArrivalLocation = new JComboBox<>();
		comboBoxArrivalLocation.setBounds(250, 70, 230, 25);
		for (String s : arrival_airport_locations) {
			comboBoxArrivalLocation.addItem(s);
		}
		panel.add(comboBoxArrivalLocation);

		JLabel lblFlightClass = new JLabel("Select Flight Class:");
		lblFlightClass.setBounds(10, 118, 143, 25);
		panel.add(lblFlightClass);

		String[] flightClassList = { "Regular Economy", "First class", "Business class", "Premium Economy" };
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

		String[] specialCategoryList = { "", "Armed Forces", "Senior Citizen", "Doctors & Nurses", "Student" };
		JComboBox<String> comboBoxSpecialCategory = new JComboBox<>();
		comboBoxSpecialCategory.setBounds(470, 245, 146, 26);
		for (String s : specialCategoryList) {
			comboBoxSpecialCategory.addItem(s);
		}
		panel.add(comboBoxSpecialCategory);

		JLabel lblSpecialCategory = new JLabel("Special Category (optional)");
		lblSpecialCategory.setBounds(465, 220, 198, 20);
		panel.add(lblSpecialCategory);

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
					departure_location = departure_airport_locations[comboBoxDepartureLocation.getSelectedIndex()];

					if (departure_location == "") {
						throw new Exception("Enter Departure Location");
					}
					
					arrival_location = arrival_airport_locations[comboBoxArrivalLocation.getSelectedIndex()];

					if (arrival_location == "") {
						throw new Exception("Enter Arrival Location");
					}
					
					if(departure_location.equals(arrival_location)){
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

					specialCategory = specialCategoryList[comboBoxSpecialCategory.getSelectedIndex()];
					
					departureDate = dateChooserDepartureDate.getDate();
					
					if(departureDate == null){
						throw new Exception("Departure date shouldnt be Empty");
					}
					
					isRoundTrip = chckbxRoundTrip.isSelected();
					
					if(isRoundTrip){
						
						returnDate = dateChooserArrivalDate.getDate();
						
						if(returnDate == null) {
							throw new Exception("Return date shouldnt be Empty");
						}
					}
					
					panel.setVisible(false);
					panel1.setVisible(true);
					addflightListInPanel();
					
				} catch (Exception ex) {
					lblDataValidation.setVisible(true);
					
					if(ex.getMessage() != null){
						lblDataValidation.setText(ex.getMessage());
					}
					else{
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
}