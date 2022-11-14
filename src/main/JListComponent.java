package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class JListComponent extends JPanel {

	/**
	 * Create the panel.
	 */
	public JListComponent(String airline, String depTime, String arrTime, double price) {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDepartureTime = new JLabel(depTime);
		lblDepartureTime.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDepartureTime);
		
		JLabel lblArrivalTime = new JLabel(arrTime);
		lblArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblArrivalTime);
		
		JLabel lblAirline = new JLabel(airline);
		lblAirline.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblAirline);
		
		JLabel lblPrice = new JLabel(String.valueOf(price));
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPrice);
		
		JButton btnSelectFlight = new JButton("Select Flight");
		add(btnSelectFlight);

	}
	
	public void name(){
		System.out.println("hii");
	}

}
