package main.util;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		
		JLabel lblDepartureTime = new JLabel(depTime);
		lblDepartureTime.setBounds(10, 50, 89, 38);
		lblDepartureTime.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDepartureTime);
		
		JLabel lblArrivalTime = new JLabel(arrTime);
		lblArrivalTime.setBounds(114, 50, 83, 38);
		lblArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblArrivalTime);
		
		JLabel lblPrice = new JLabel(String.valueOf(price));
		lblPrice.setBounds(207, 50, 79, 38);
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPrice);
		
		btnSelectFlight = new JButton("Select Flight");
		btnSelectFlight.setBounds(114, 90, 120, 30);
		add(btnSelectFlight);
	}

}
