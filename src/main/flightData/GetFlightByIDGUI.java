package main.flightData;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.HomeFlightData;
import main.dal.FlightDataDAL;
import main.entity.FlightDetail;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class GetFlightByIDGUI extends JFrame {

	private JPanel contentPane;
	private JTextField flightId;

	/**
	 * Create the frame.
	 */
	public GetFlightByIDGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFlightid = new JLabel("FlightId");
		lblFlightid.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblFlightid.setBounds(160, 13, 84, 48);
		contentPane.add(lblFlightid);
		
		JTextField flightId = new JTextField();
		flightId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		flightId.setBounds(256, 13, 212, 51);
		contentPane.add(flightId);
		flightId.setColumns(10);
		
		JTextArea text = new JTextArea();
		text.setFont(new Font("Monospaced", Font.PLAIN, 18));
		text.setBounds(95, 76, 527, 326);
		text.setVisible(false);
		contentPane.add(text);
		
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text.setVisible(true);
				FlightDetail flight=FlightDataDAL.getFlightDetail(flightId.getText());
				if (flight==null)
					text.setText("Invalid FlightID");
				else
					text.setText(flight.toString());
			}
		});
		submit.setBounds(467, 12, 96, 53);
		contentPane.add(submit);
		
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
		BackButton.setBounds(0, 13, 117, 48);
		contentPane.add(BackButton);
	}
}
