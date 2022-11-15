package main.bookingData;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.HomeBookingData;
import main.dal.BookingDataDAL;

public class CancelBookingGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CancelBookingGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblbookingId = new JLabel("Booking Id : ");
		lblbookingId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblbookingId.setBounds(159, 49, 120, 48);
		contentPane.add(lblbookingId);

		JTextField bookingId = new JTextField();
		bookingId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bookingId.setBounds(291, 49, 212, 51);
		contentPane.add(bookingId);
		bookingId.setColumns(10);
		
		JTextArea ResultArea = new JTextArea();
		ResultArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		ResultArea.setBounds(133, 133, 405, 48);
		ResultArea.setVisible(false);
		contentPane.add(ResultArea);

		JButton Remove = new JButton("Remove");
		Remove.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean bool = BookingDataDAL.Removebooking(Integer.parseInt(bookingId.getText()));
				ResultArea.setVisible(true);
				if (bool)
					ResultArea.setText("Booking Removed Successfully.");
				else
					ResultArea.setText("Invalid Booking ID.");
			}
		});
		Remove.setBounds(518, 48, 110, 53);
		contentPane.add(Remove);

		JButton btnNewButton = new JButton("<- Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomeBookingData home = new HomeBookingData();
				setVisible(false);
				home.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(25, 19, 110, 40);
		contentPane.add(btnNewButton);
	}
}
