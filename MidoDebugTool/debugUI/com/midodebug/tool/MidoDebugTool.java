package com.midodebug.tool;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Resources.resources;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class MidoDebugTool extends JFrame{

	private JPanel contentPane;
	private JTextField txtWinningNumbers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MidoDebugTool frame = new MidoDebugTool();
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
	public MidoDebugTool() {
		setResizable(false);
		setTitle("Mido Debug Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnCloseDraw = new JButton("Close/Complete Mido Millions Draw");
		btnCloseDraw.setBounds(25, 109, 247, 46);
		
		panel.add(btnCloseDraw);
		
		txtWinningNumbers = new JTextField();
		txtWinningNumbers.setHorizontalAlignment(SwingConstants.CENTER);
		txtWinningNumbers.setText("01 02 03 04 05 06");
		txtWinningNumbers.setBounds(302, 107, 145, 49);
		panel.add(txtWinningNumbers);
		txtWinningNumbers.setColumns(10);
		
		JButton btnGetMidoStatus = new JButton("Get Mido Millions Draw Status");
		
		btnGetMidoStatus.setBounds(128, 226, 241, 39);
		panel.add(btnGetMidoStatus);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Staging");
		rdbtnNewRadioButton.setBounds(128, 49, 141, 23);
		rdbtnNewRadioButton.setSelected(true);
		
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Demo");
		rdbtnNewRadioButton_1.setBounds(250, 49, 141, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		JLabel lblSelectEnvironment = new JLabel("Select environment");
		lblSelectEnvironment.setBounds(163, 21, 125, 16);
		panel.add(lblSelectEnvironment);
		
		JButton btnGetServerInfo = new JButton("Get Server Information");
		
		btnGetServerInfo.setBounds(149, 321, 195, 39);
		panel.add(btnGetServerInfo);
		
		JLabel lblNewLabel_1 = new JLabel("Close/Complete Status: UNKNOWN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(7, 155, 491, 39);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Current Mido draw status: UNKNOWN");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(6, 264, 492, 32);
		panel.add(lblNewLabel_2);
		
		
		
		
		
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton.setSelected(true);
				rdbtnNewRadioButton_1.setSelected(false);
			}
		});
		
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton_1.setSelected(true);
				rdbtnNewRadioButton.setSelected(false);
			}
		});
		
		
		
		
		
		
		// get Mido Millions draw status
		
		btnGetMidoStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resources temp = new resources();
				if (rdbtnNewRadioButton.isSelected()==true)
					temp.environment = temp.STAGING;
				else
					temp.environment = temp.DEMO;

//				temp.get
//				if (result == true)
//					lblNewLabel_1.setText("Close/Complete Status: PASS");
//				else
//					lblNewLabel_1.setText("Close/Complete Status: FAILED - 404 or 505");
				
				
			}
		});
		
		
		// close draw
		
		btnCloseDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resources temp = new resources();
				if (rdbtnNewRadioButton.isSelected()==true)
					temp.environment = temp.STAGING;
				else
					temp.environment = temp.DEMO;
				
				Boolean result = temp.closeMidoDraw(txtWinningNumbers.getText().trim());
				if (result == true)
					lblNewLabel_1.setText("Close/Complete Status: PASS");
				else
					lblNewLabel_1.setText("Close/Complete Status: FAILED - 404 or 505");
			}
		});
		
		
		
		
		
		
		// GET SERVER INFORMATION
		
		btnGetServerInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resources temp = new resources();
				if (rdbtnNewRadioButton.isSelected()==true)
					temp.environment = temp.STAGING;
				else
					temp.environment = temp.DEMO;
				temp.getService();
			}
		});
	}
}
