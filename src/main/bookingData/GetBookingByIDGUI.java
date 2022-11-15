package main.bookingData;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.HomeBookingData;
import main.dal.BookingDataDAL;
import main.dal.FlightDataDAL;
import main.entity.BookingDetail;

public class GetBookingByIDGUI extends JFrame {

	private JPanel contentPane;
	private JTextField flightId;

	/**
	 * Create the frame.
	 */
	public GetBookingByIDGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblbookingId = new JLabel("Booking Id : ");
		lblbookingId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblbookingId.setBounds(149, 13, 120, 48);
		contentPane.add(lblbookingId);

		JTextField bookingId = new JTextField();
		bookingId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bookingId.setBounds(272, 13, 212, 51);
		contentPane.add(bookingId);
		bookingId.setColumns(10);

		JTextArea ResultArea = new JTextArea();
		ResultArea.setWrapStyleWord(true);
		ResultArea.setLineWrap(true);
		ResultArea.setColumns(10);
		ResultArea.setRows(10);
		ResultArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		ResultArea.setBounds(68, 133, 514, 362);
		ResultArea.setVisible(false);
		contentPane.add(ResultArea);

		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultArea.setVisible(true);
				BookingDetail booking = BookingDataDAL.getBookingDetail(Integer.parseInt(bookingId.getText()));

				if (booking == null)
					ResultArea.setText("Invalid bookingID");
				else{
					Date date = FlightDataDAL.getFlightDetail(booking.getFlightId()).getDepartureDate();
					Date date1 = FlightDataDAL.getFlightDetail(booking.getFlightId()).getArrivalDate();
					
					ResultArea.setText(booking.toString() + "\nDeparture date:\n" + 
							date.toLocaleString() + "\nArrival date:\n" + date1.toLocaleString());
				}
			}
		});
		submit.setBounds(501, 12, 96, 53);
		contentPane.add(submit);

		JButton BackButton = new JButton("<- Back");
		BackButton.setHorizontalAlignment(SwingConstants.LEFT);
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeBookingData home = new HomeBookingData();
				setVisible(false);
				home.setVisible(true);
			}
		});
		BackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BackButton.setBounds(0, 13, 117, 48);
		contentPane.add(BackButton);
	}
}
