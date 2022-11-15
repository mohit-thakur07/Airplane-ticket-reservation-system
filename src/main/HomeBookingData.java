package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.bookingData.BookFlightGUI;
import main.bookingData.CancelBookingGUI;
import main.bookingData.GetBookingByIDGUI;
import main.util.JPanelWBI;

public class HomeBookingData extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeBookingData frame = new HomeBookingData();
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
	public HomeBookingData() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 700, 600);
		contentPane = new JPanelWBI("img\\bg1.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextArea txtrAirlineReservationSystem = new JTextArea();
		txtrAirlineReservationSystem.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 37));
		txtrAirlineReservationSystem.setText("Airline Reservation System");
		txtrAirlineReservationSystem.setBounds(32, 32, 611, 68);
		txtrAirlineReservationSystem.setEditable(false);
		contentPane.add(txtrAirlineReservationSystem);

		JButton AddNewFlightButton = new JButton("Book Flight");
		AddNewFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookFlightGUI bfg = new BookFlightGUI();
				setVisible(false);
				bfg.setVisible(true);
			}
		});
		AddNewFlightButton.setForeground(Color.BLACK);
		AddNewFlightButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		AddNewFlightButton.setBounds(32, 159, 168, 52);
		contentPane.add(AddNewFlightButton);

		JButton CancelFlightButton = new JButton("Cancel Flight");
		CancelFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelBookingGUI cbg = new CancelBookingGUI();
				setVisible(false);
				cbg.setVisible(true);
			}
		});
		CancelFlightButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		CancelFlightButton.setBounds(32, 245, 168, 52);
		contentPane.add(CancelFlightButton);

		JButton FlightDetailByIDButton = new JButton("Booking Detail");
		FlightDetailByIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetBookingByIDGUI gbi = new GetBookingByIDGUI();
				setVisible(false);
				gbi.setVisible(true);
			}
		});
		FlightDetailByIDButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		FlightDetailByIDButton.setBounds(32, 329, 168, 52);
		contentPane.add(FlightDetailByIDButton);

		JButton ExitButton = new JButton("EXIT");
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ExitButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ExitButton.setBounds(508, 422, 106, 40);
		contentPane.add(ExitButton);
	}

}
