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
		setBounds(100, 100, 593, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnCloseDraw = new JButton("Close/Complete Mido Millions Draw");
		btnCloseDraw.setBounds(38, 110, 247, 46);
		
		panel.add(btnCloseDraw);
		
		txtWinningNumbers = new JTextField();
		txtWinningNumbers.setHorizontalAlignment(SwingConstants.CENTER);
		txtWinningNumbers.setText("01 02 03 04 05 06");
		txtWinningNumbers.setBounds(315, 108, 145, 49);
		panel.add(txtWinningNumbers);
		txtWinningNumbers.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Mido Millions Draw Status");
		lblNewLabel.setBounds(199, 198, 202, 16);
		panel.add(lblNewLabel);
		
		JButton btnGetMidoStatus = new JButton("Get Mido Millions Draw Status");
		
		btnGetMidoStatus.setBounds(160, 228, 241, 39);
		panel.add(btnGetMidoStatus);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Staging");
		rdbtnNewRadioButton.setBounds(164, 49, 141, 23);
		rdbtnNewRadioButton.setSelected(true);
		
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Demo");
		rdbtnNewRadioButton_1.setBounds(286, 49, 141, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		JLabel lblSelectEnvironment = new JLabel("Select environment");
		lblSelectEnvironment.setBounds(199, 21, 125, 16);
		panel.add(lblSelectEnvironment);
		
		JButton btnGetServerInfo = new JButton("Get Server Information");
		
		btnGetServerInfo.setBounds(184, 279, 195, 39);
		panel.add(btnGetServerInfo);
		
		
		
		
		
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
				
				temp.login("mt7@yopmail.com", "qwerty");
//				temp.getService("");
				
				
				
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
				
				temp.closeMidoDraw(txtWinningNumbers.getText().trim());
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
