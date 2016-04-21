package com.midodebug.tool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xpath.internal.operations.Variable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.awt.event.ActionEvent;
import Resources.resources;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JCheckBox;
import javax.annotation.Resources;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class MidoDebugTool extends JFrame{

	private JPanel contentPane;
	private JTextField txtWinningNumbers;
	private JTextField txtReceiver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MidoDebugTool frame = new MidoDebugTool();					
					
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width/2 - frame.getSize().width/2, dim.height/2 - frame.getSize().height/2);
					
					
					
					
					MidoDebugTool2 frame2 = new MidoDebugTool2();
					frame2.setLocation(dim.width/2 - frame2.getSize().width/2, dim.height/2 - frame2.getSize().height/2);

//					frame2.setVisible(true);
					

//					frame2.setVisible(false);
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
	
//	public ProgressSetup() {
//		
//	}
	
	
	
	
	
	
	
	
	public MidoDebugTool() {
		
		setResizable(false);
		setTitle("Mido Debug Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 625);
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
		
		btnGetMidoStatus.setBounds(130, 452, 241, 39);
		panel.add(btnGetMidoStatus);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Staging");
		rdbtnNewRadioButton.setBounds(139, 49, 141, 23);
		rdbtnNewRadioButton.setSelected(true);
		
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Demo");
		rdbtnNewRadioButton_1.setBounds(261, 49, 141, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		JLabel lblSelectEnvironment = new JLabel("Select environment");
		lblSelectEnvironment.setBounds(191, 21, 125, 16);
		panel.add(lblSelectEnvironment);
		
		JButton btnGetServerInfo = new JButton("Get Server Information");
		
		btnGetServerInfo.setBounds(150, 503, 195, 39);
		panel.add(btnGetServerInfo);
		
		JLabel lblclosedraw = new JLabel("");
		lblclosedraw.setHorizontalAlignment(SwingConstants.CENTER);
		lblclosedraw.setBounds(7, 155, 491, 39);
		panel.add(lblclosedraw);
		
		txtReceiver = new JTextField();
		txtReceiver.setBounds(224, 233, 237, 28);
		panel.add(txtReceiver);
		txtReceiver.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Invitee/Receiver Email Address");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 239, 202, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Send Group invitaion/Gift ticket(s) to someone");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(77, 206, 325, 16);
		panel.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
		comboBox.setSelectedIndex(0);
//		comboBox.setSelectedItem(anObject);
		comboBox.setMaximumRowCount(100);
//		comboBox.setSelectedIndex(1);
		comboBox.setBounds(224, 273, 66, 27);
		panel.add(comboBox);
		
		JLabel lblGiftStatus = new JLabel("");
		lblGiftStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiftStatus.setBounds(353, 395, 154, 35);
		panel.add(lblGiftStatus);
		
		JLabel lblNumberOfTickets = new JLabel("Number of tickets");
		lblNumberOfTickets.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfTickets.setBounds(25, 277, 187, 16);
		panel.add(lblNumberOfTickets);
		
		JButton btnNewButton = new JButton("Gift");
		
		btnNewButton.setBounds(110, 395, 117, 35);
		panel.add(btnNewButton);
		
		JCheckBox chkboxWinning = new JCheckBox("winning ticket?");
		chkboxWinning.setBounds(302, 273, 159, 23);
		panel.add(chkboxWinning);
		
		JEditorPane txtGiftMessage = new JEditorPane();
		txtGiftMessage.setText("Hi Bro,\nI'd like to give these tickets to you.\nGood luck!\n--Automation");
		txtGiftMessage.setBounds(224, 312, 237, 72);
		panel.add(txtGiftMessage);
		
		JLabel lblGiftMessage = new JLabel("Message");
		lblGiftMessage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGiftMessage.setBounds(25, 312, 187, 16);
		panel.add(lblGiftMessage);
		
		JButton btnInviteGroup = new JButton("Invite to Group");
		
		btnInviteGroup.setBounds(234, 395, 117, 35);
		panel.add(btnInviteGroup);
		
		JLabel lblNewLabel_1 = new JLabel("FileString | MidoPlay | April 2016");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(25, 554, 473, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblMinhfilestringmidoplaycomOrMinhnguyenfilestringcom = new JLabel("Contact me: minh.nguyen@filestring.com or minh.filestring@midoplay.com");
		lblMinhfilestringmidoplaycomOrMinhnguyenfilestringcom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinhfilestringmidoplaycomOrMinhnguyenfilestringcom.setForeground(Color.GRAY);
		lblMinhfilestringmidoplaycomOrMinhnguyenfilestringcom.setBounds(7, 571, 491, 16);
		panel.add(lblMinhfilestringmidoplaycomOrMinhnguyenfilestringcom);
		
		
		
		
		
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
		
		
		
		
		
		// invite someone to a group
		btnInviteGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				resources temp = new resources();
				if (rdbtnNewRadioButton.isSelected()==true)
					temp.environment = temp.STAGING;
				else
					temp.environment = temp.DEMO;
				
				
				String receiver = txtReceiver.getText().trim();
				String message = txtGiftMessage.getText().trim();
				
				
				
				
				Boolean result = temp.inviteToGroup(receiver, message);
				if (result==false){
					lblGiftStatus.setText("FAILED - 404 or 505");
					lblGiftStatus.setOpaque(true);
					lblGiftStatus.setForeground(Color.red);
				}else{
					lblGiftStatus.setText("Group Invitation Sent");
					lblGiftStatus.setOpaque(true);
					lblGiftStatus.setForeground(Color.blue);
				}
				
			
			
			
			}
		});
		
		
		
		// gift to someone using email address
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean isWinning = false;
				int numberOfTickets = comboBox.getSelectedIndex() + 1;
				if (chkboxWinning.isSelected())
					isWinning = true;
				
				resources temp = new resources();
				if (rdbtnNewRadioButton.isSelected()==true)
					temp.environment = temp.STAGING;
				else
					temp.environment = temp.DEMO;
				
				
				String receiver = txtReceiver.getText().trim();
				String message = txtGiftMessage.getText().trim();
				
				
				
				
				Boolean result = temp.giftTicketsToSomeone(receiver, numberOfTickets, isWinning, message);
				if (result==false){
					lblGiftStatus.setText("FAILED - 404 or 505");
					lblGiftStatus.setOpaque(true);
					lblGiftStatus.setForeground(Color.red);
				}else{
					lblGiftStatus.setText("Gift Invitation Sent");
					lblGiftStatus.setOpaque(true);
					lblGiftStatus.setForeground(Color.blue);
				}
				

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

				String result = temp.getCurrentDrawStatus();
				if (result != null){
					JOptionPane.showMessageDialog(null, result, "Mido Millions draw information", JOptionPane.INFORMATION_MESSAGE);
				}
				
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
				
				
				
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Date date = new Date();
				String currentTime = dateFormat.format(date);
						
				if (result == true){
					String midoDrawStatus = temp.getCurrentDrawStatus();

					if (midoDrawStatus == null){
						lblclosedraw.setText("<html>Can't get Mido Millions draw status at the moment.<br>" 
											+ "Mido Millions draw has been closed or completed successfully.</html>");
						lblclosedraw.setOpaque(true);
						lblclosedraw.setForeground(Color.blue);
					}
					else if (midoDrawStatus.contains("CURRENT_DRAW")){
						lblclosedraw.setText("<html>Mido Millions draw has just been COMPLETED.<br>" 
											+ "Current draw status is ACTIVE - CURRENT_DRAW.</html>");
						lblclosedraw.setOpaque(true);
						lblclosedraw.setForeground(Color.blue);
					} else{
						lblclosedraw.setText("<html>Mido Millions draw has just been CLOSED.<br>" 
								+ "Current draw status is CURRENT_PENDING_DRAW.</html>");
						lblclosedraw.setOpaque(true);
						lblclosedraw.setForeground(Color.blue);
					}
				}else{
					lblclosedraw.setText("<html>Can't close Mido Millions draw status at the moment.<br>"
							+ "Request was completed at: " + currentTime + "</html>");
					lblclosedraw.setOpaque(true);
					lblclosedraw.setForeground(Color.red);
				}
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
		
		
//		MidoDebugTool2 frame2 = new MidoDebugTool2();
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//
//		
//		frame2.setLocation(dim.width/2 - frame2.getSize().width/2, dim.height/2 - frame2.getSize().height/2);
//		frame2.setVisible(true);
//		
//		resources temp = new resources();
//		while(temp.checkVaribles()==false){
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}

			
		
	
		
		
		
		
	}
}
