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

import main.flightData.CreateFlightGUI;
import main.flightData.GetFlightByIDGUI;
import main.flightData.RemoveFlightGUI;
import main.util.JPanelWBI;

public class HomeFlightData extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFlightData frame = new HomeFlightData();
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
	public HomeFlightData() {
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

		JButton AddNewFlightButton = new JButton("Add New Flight");
		AddNewFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateFlightGUI cfg = new CreateFlightGUI();
				setVisible(false);
				cfg.setVisible(true);
			}
		});
		AddNewFlightButton.setForeground(Color.BLACK);
		AddNewFlightButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		AddNewFlightButton.setBounds(32, 159, 168, 52);
		contentPane.add(AddNewFlightButton);

		JButton RemoveFlightButton = new JButton("Remove Flight");
		RemoveFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveFlightGUI rfg = new RemoveFlightGUI();
				setVisible(false);
				rfg.setVisible(true);
			}
		});
		RemoveFlightButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		RemoveFlightButton.setBounds(32, 245, 168, 52);
		contentPane.add(RemoveFlightButton);

		JButton UpdateFlightButton = new JButton("Update Flight");
		UpdateFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		UpdateFlightButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		UpdateFlightButton.setBounds(32, 330, 168, 52);
		contentPane.add(UpdateFlightButton);

		JButton FlightDetailByIDButton = new JButton("Get Flight Detail");
		FlightDetailByIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetFlightByIDGUI flightDataGUI = new GetFlightByIDGUI();
				setVisible(false);
				flightDataGUI.setVisible(true);
			}
		});
		FlightDetailByIDButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		FlightDetailByIDButton.setBounds(32, 414, 168, 52);
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
