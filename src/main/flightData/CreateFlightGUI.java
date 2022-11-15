package main.flightData;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import main.HomeFlightData;
import main.dal.FlightDataDAL;
import main.entity.FlightDetail;
import main.util.Values;

import javax.swing.SwingConstants;

public class CreateFlightGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblArrivalLocation;
	private JLabel lblDistance;
	private JTextField textField_2;
	private JLabel lblNewLabel_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblRegularEconomy;
	private JLabel lblSeats;
	private JSpinner textField_7;
	private JSpinner textField_8;
	private JSpinner textField_9;
	private JSpinner textField_10;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel lblNewLabel_5;
	private JLabel lblArrivalDate;
	private JLabel lblFlightId;
	private JTextField textField;
	private JLabel lblDataValidation;
	private JTextField txtDepartureTime;
	private JTextField txtArrivalTime;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CreateFlightGUI frame = new CreateFlightGUI();
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
	public CreateFlightGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<>();
		for(String s : Values.airlines){
			comboBox.addItem(s);
		}
		comboBox.setBounds(5, 5, 283, 26);
		contentPane.add(comboBox);
		
		lblFlightId = new JLabel("Flight ID:");
		lblFlightId.setBounds(337, 8, 69, 20);
		contentPane.add(lblFlightId);
		
		textField = new JTextField();
		textField.setBounds(417, 5, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Departure location:");
		lblNewLabel.setBounds(5, 47, 146, 20);
		contentPane.add(lblNewLabel);
		
		lblArrivalLocation = new JLabel("Arrival location:");
		lblArrivalLocation.setBounds(223, 47, 146, 20);
		contentPane.add(lblArrivalLocation);
		
		lblDistance = new JLabel("Distance:");
		lblDistance.setBounds(417, 47, 146, 20);
		contentPane.add(lblDistance);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(417, 70, 146, 32);
		contentPane.add(textField_2);
		
		lblNewLabel_1 = new JLabel("Prices:");
		lblNewLabel_1.setBounds(5, 118, 69, 20);
		contentPane.add(lblNewLabel_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(5, 145, 146, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(160, 145, 146, 26);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(315, 145, 146, 26);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(470, 145, 146, 26);
		contentPane.add(textField_6);
		
		lblNewLabel_2 = new JLabel("First class");
		lblNewLabel_2.setBounds(48, 180, 69, 20);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Business class");
		lblNewLabel_3.setBounds(190, 180, 98, 20);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Premium Economy");
		lblNewLabel_4.setBounds(337, 180, 110, 20);
		contentPane.add(lblNewLabel_4);
		
		lblRegularEconomy = new JLabel("Regular Economy");
		lblRegularEconomy.setBounds(494, 180, 110, 20);
		contentPane.add(lblRegularEconomy);
		
		lblSeats = new JLabel("Seats:");
		lblSeats.setBounds(5, 216, 69, 20);
		contentPane.add(lblSeats);
		
		textField_7 = new JSpinner();
		textField_7.setBounds(5, 237, 146, 26);
		contentPane.add(textField_7);
		
		textField_8 = new JSpinner();
		textField_8.setBounds(160, 237, 146, 26);
		contentPane.add(textField_8);
		
		textField_9 = new JSpinner();
		textField_9.setBounds(315, 237, 146, 26);
		contentPane.add(textField_9);
		
		textField_10 = new JSpinner();
		textField_10.setBounds(470, 237, 146, 26);
		contentPane.add(textField_10);
		
		label = new JLabel("First class");
		label.setBounds(48, 272, 69, 20);
		contentPane.add(label);
		
		label_1 = new JLabel("Business class");
		label_1.setBounds(190, 272, 98, 20);
		contentPane.add(label_1);
		
		label_2 = new JLabel("Premium Economy");
		label_2.setBounds(337, 272, 110, 20);
		contentPane.add(label_2);
		
		label_3 = new JLabel("Regular Economy");
		label_3.setBounds(494, 272, 110, 20);
		contentPane.add(label_3);
		
		lblNewLabel_5 = new JLabel("Departure date:");
		lblNewLabel_5.setBounds(5, 315, 146, 20);
		contentPane.add(lblNewLabel_5);
		
		lblArrivalDate = new JLabel("Arrival date:");
		lblArrivalDate.setBounds(277, 315, 104, 20);
		contentPane.add(lblArrivalDate);
		
		JComboBox<String> comboBox1 = new JComboBox<>();
		for(String s : Values.locations){
			comboBox1.addItem(s);
		}
		comboBox1.setBounds(5, 73, 146, 26);
		contentPane.add(comboBox1);
		
		JComboBox<String> comboBox2 = new JComboBox<>();
		for(String s : Values.locations){
			comboBox2.addItem(s);
		}
		comboBox2.setBounds(223, 73, 146, 26);
		contentPane.add(comboBox2);
		
		JDateChooser dateChooserDepartureDate = new JDateChooser();
		dateChooserDepartureDate.setBounds(119, 320, 119, 26);
		dateChooserDepartureDate.setMinSelectableDate(new Date());
		contentPane.add(dateChooserDepartureDate);
		
		JDateChooser dateChooserArrivalDate = new JDateChooser();
		dateChooserArrivalDate.setBounds(382, 320, 119, 26);
		dateChooserArrivalDate.setMinSelectableDate(new Date());
		contentPane.add(dateChooserArrivalDate);
		
		JButton btnSubmit = new JButton("submit");
		btnSubmit.addActionListener(new ActionListener() {
			private String airline;
			private String flightId;
			private String departure_location;
			private String arrival_location;
			private double distance;
			private double first_class_price;
			private double business_class_price;
			private double premium_class_price;
			private double economy_class_price;
			private int first_class_seats;
			private int business_class_seats;
			private int premium_class_seats;
			private int economy_class_seats;

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				try {
					
					airline  = Values.airlines[comboBox.getSelectedIndex()];
					
					if(airline == ""){
						throw new Exception("airline cant be empty");
					}
					
					flightId = textField.getText();
					
					if(flightId.equals("")){
						throw new Exception("flight Id cant be empty");
					}
					
					departure_location = Values.locations[comboBox1.getSelectedIndex()];
					
					if(departure_location == ""){
						throw new Exception("departure location cant be empty");
					}
					
					arrival_location = Values.locations[comboBox2.getSelectedIndex()];
					
					if(arrival_location == ""){
						throw new Exception("arrival location cant be empty");
					}
					
					if(departure_location == arrival_location){
						throw new Exception("arrival location cant be same as departure location");
					}
					
					distance = Double.parseDouble(textField_2.getText());
					
					first_class_price     = Double.parseDouble(textField_3.getText());
					business_class_price  = Double.parseDouble(textField_4.getText());
					premium_class_price   = Double.parseDouble(textField_5.getText());
					economy_class_price   = Double.parseDouble(textField_6.getText());
					
					
					first_class_seats = (int) textField_7.getValue();	
					business_class_seats = (int) textField_8.getValue();	
					premium_class_seats = (int) textField_9.getValue();	
					economy_class_seats = (int) textField_10.getValue();	
					
					Date departureDate = dateChooserDepartureDate.getDate();
					
					String[] time = txtDepartureTime.getText().split(":");
					
					if(departureDate == null){
						throw new Exception("Departure date shouldnt be Empty");
					}
					else{
						departureDate.setHours(Integer.parseInt(time[0]));
						departureDate.setMinutes(Integer.parseInt(time[1]));
					}
					
					Date arrivalDate = dateChooserArrivalDate.getDate();
					
					time = txtArrivalTime.getText().split(":");
					
					if(arrivalDate == null){
						throw new Exception("Arrival date shouldnt be Empty");
					}
					else{
						arrivalDate.setHours(Integer.parseInt(time[0]));
						arrivalDate.setMinutes(Integer.parseInt(time[1]));
					}
					
					FlightDetail new_flight = new FlightDetail(flightId, airline, departure_location, arrival_location, distance, 
							new double[]{economy_class_price, premium_class_price, business_class_price, first_class_price}, 
							new int[]{economy_class_seats, premium_class_seats, business_class_seats, first_class_seats},
							departureDate, arrivalDate);
					
					try{
						if(!FlightDataDAL.addNewFlight(new_flight)){
							throw new Exception("duplicate flight ID");
						}
					}
					catch(IOException | ClassNotFoundException e){
						e.printStackTrace();
						throw new Exception("Data cant be written");
					}
					lblDataValidation.setVisible(true);
					lblDataValidation.setText("Data added successfully");
					
					comboBox.setSelectedIndex(0);
					textField.setText("");
					comboBox1.setSelectedIndex(0);
					comboBox2.setSelectedIndex(0);
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					textField_6.setText("");
					textField_7.setValue(0);
					textField_8.setValue(0);
					textField_9.setValue(0);
					textField_10.setValue(0);
					dateChooserDepartureDate.setDate(null);
					dateChooserArrivalDate.setDate(null);
					txtDepartureTime.setText("");
					txtArrivalTime.setText("");
					
				}
				catch(NumberFormatException e){
					lblDataValidation.setVisible(true);
					lblDataValidation.setText("illegal Value");
				}
				catch(Exception e){
					lblDataValidation.setVisible(true);
					if(e.getMessage() != null){
						lblDataValidation.setText(e.getMessage());
					}
					else{
						lblDataValidation.setText("Error in submitting data");
					}
				}
			}
		});
		btnSubmit.setBounds(254, 414, 115, 29);
		contentPane.add(btnSubmit);
		
		lblDataValidation = new JLabel("New label");
		lblDataValidation.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataValidation.setBounds(119, 471, 415, 20);
		lblDataValidation.setVisible(false);
		contentPane.add(lblDataValidation);
		
		JButton BackButton = new JButton("<- Back");
		BackButton.setHorizontalAlignment(SwingConstants.LEFT);
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeFlightData home=new HomeFlightData();
				setVisible(false);
				home.setVisible(true);
			}
		});
		BackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BackButton.setBounds(254, 507, 117, 48);
		contentPane.add(BackButton);
		
		txtDepartureTime = new JTextField();
		txtDepartureTime.setBounds(119, 351, 146, 26);
		contentPane.add(txtDepartureTime);
		txtDepartureTime.setColumns(10);
		
		txtArrivalTime = new JTextField();
		txtArrivalTime.setBounds(382, 351, 146, 26);
		contentPane.add(txtArrivalTime);
		txtArrivalTime.setColumns(10);
		
		JLabel lblDepartureTime = new JLabel("Departure time:");
		lblDepartureTime.setBounds(5, 354, 112, 20);
		contentPane.add(lblDepartureTime);
		
		JLabel lblArrivalTime = new JLabel("Arrival Time:");
		lblArrivalTime.setBounds(277, 354, 104, 20);
		contentPane.add(lblArrivalTime);
	}
}