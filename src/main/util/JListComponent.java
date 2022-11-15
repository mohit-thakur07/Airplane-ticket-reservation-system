package main.util;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class JListComponent extends JPanel {

	public JButton btnSelectFlight;

	/**
	 * Create the panel.
	 */
	public JListComponent(String flightId, String airline, String depTime, String arrTime, double price) {
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(301, 130));
		setLayout(null);
		
		JLabel lblAirline = new JLabel(airline);
		lblAirline.setBounds(10, 16, 75, 38);
		lblAirline.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblAirline);
		
		JTextArea lblDepartureTime = new JTextArea(depTime);
		lblDepartureTime.setWrapStyleWord(true);
		lblDepartureTime.setLineWrap(true);
		lblDepartureTime.setBounds(10, 50, 89, 38);
		add(lblDepartureTime);
		
		JTextArea lblArrivalTime = new JTextArea(arrTime);
		lblArrivalTime.setWrapStyleWord(true);
		lblArrivalTime.setLineWrap(true);
		lblArrivalTime.setBounds(114, 50, 83, 38);
		add(lblArrivalTime);
		
		JLabel lblPrice = new JLabel(String.valueOf(price));
		lblPrice.setBounds(207, 50, 79, 38);
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPrice);
		
		btnSelectFlight = new JButton("Select Flight");
		btnSelectFlight.setBounds(81, 95, 120, 30);
		add(btnSelectFlight);
	}

}
