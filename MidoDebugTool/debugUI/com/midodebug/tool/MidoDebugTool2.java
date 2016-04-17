package com.midodebug.tool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
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
import javax.swing.JProgressBar;

public class MidoDebugTool2 extends JFrame{

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	
	
	public MidoDebugTool2() {
		
		setResizable(false);
		setTitle("Mido Debug Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JProgressBar progressbar = new JProgressBar();
		progressbar.setBounds(20, 85, 464, 20);
		panel.add(progressbar);
		
		JLabel lblMidoDebugTool = new JLabel("Mido Debug Tool is loading neccessary data<br>Keep patience");
		lblMidoDebugTool.setHorizontalAlignment(SwingConstants.CENTER);
		lblMidoDebugTool.setBounds(20, 27, 464, 30);
		panel.add(lblMidoDebugTool);
		
		JLabel lblKeepPatience = new JLabel("Keep patience");
		lblKeepPatience.setHorizontalAlignment(SwingConstants.CENTER);
		lblKeepPatience.setBounds(20, 47, 464, 30);
		panel.add(lblKeepPatience);
		
		
		
//		
//		this.addWindowStateListener(new WindowStateListener() {
//			
//			@Override
//			public void windowStateChanged(WindowEvent e) {
//				System.out.println("goes here");
//
//				resources temp = new resources();
//				int value = 0;
//				temp.environment = temp.STAGING;
//				
//				while(temp.checkAuthenticationToken()==null){
//					value += 5;
//					progressbar.setValue(value);
//					System.out.println("loop 1");
//				}
//				value = 45;
//				temp.environment = temp.DEMO;
//
//				while(temp.checkAuthenticationToken()==null){
//					value += 5;
//					progressbar.setValue(value);
//					System.out.println("loop 2");
//				}
//				
//			}
//		});
//		
		
		progressbar.addAncestorListener(new AncestorListener() {
			
			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void ancestorMoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void ancestorAdded(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				System.out.println("goes here");

				resources temp = new resources();
				int value = 0;
				temp.environment = temp.STAGING;
				
				while(temp.checkAuthenticationToken()==null){
					value += 5;
					progressbar.setValue(value);
					System.out.println("loop 1");
				}
				value = 45;
				temp.environment = temp.DEMO;

				while(temp.checkAuthenticationToken()==null){
					value += 5;
					progressbar.setValue(value);
					System.out.println("loop 2");
				}
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
