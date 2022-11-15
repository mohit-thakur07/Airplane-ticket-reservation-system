package main.util;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JListComponent1 extends JPanel {
	private JTextField txtName;
	private JTextField txtMobileNo;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;

	/**
	 * Create the panel.
	 */
	public JListComponent1() {
		setLayout(null);
		setPreferredSize(new Dimension(340, 200));
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(35, 39, 69, 20);
		add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(132, 36, 146, 26);
		add(txtName);
		txtName.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(35, 96, 69, 20);
		add(lblGender);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(132, 92, 86, 29);
		rdbtnMale.setSelected(true);
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMale.setSelected(true);
				rdbtnFemale.setSelected(false);
			}
		});
		add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(232, 92, 86, 29);
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMale.setSelected(false);
				rdbtnFemale.setSelected(true);
			}
		});
		add(rdbtnFemale);
		
		JLabel lblMobileNo = new JLabel("Mobile no:");
		lblMobileNo.setBounds(35, 146, 86, 20);
		add(lblMobileNo);
		
		txtMobileNo = new JTextField();
		txtMobileNo.setBounds(132, 146, 146, 26);
		add(txtMobileNo);
		txtMobileNo.setColumns(10);
	}
	
	public String getName(){
		return txtName.getText();
	}
	
	public String getGender(){
		if(rdbtnMale.isSelected()){
			return "Male";
		}
		else{
			return "Female";
		}
	}
	
	public long getMobileNo(){
		try{
			return Long.parseLong(txtMobileNo.getText());
		}
		catch(Exception e){
			return 0;
		}
	}
}
