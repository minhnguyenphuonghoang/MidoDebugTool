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

public class MidoDebugTool extends JFrame{

	private JPanel contentPane;

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
		setTitle("Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblApplicationTitle = new JLabel("Tool");
		panel.add(lblApplicationTitle);
		
		JButton btnCloseDraw = new JButton("New button");
		btnCloseDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeMidoDraw();
			}
		});
		panel.add(btnCloseDraw);
	}
	
	public void closeMidoDraw(){
		JOptionPane.showMessageDialog(null, "OK great, let's close this popup");
		resources temp = new resources(); 
		String[] a = temp.login("mt5@yopmail.com", "qwerty");
				
		if(a[0].equals("false"))
			temp.showErrorMessage("ERROR", "Can't login at this time.\nYour account: " + "mt5@yopmail.com");
		else{
			
		
		}
		
		temp.sendGet("Bearer MTQ2MDUzNjYwNDQxOTpiMTM2Y2Y0YzAzZWI1NmRlOGU4MDJhOWFiZWVhMmRiNjU3NGU4MDRkMTA3YWE2MmEyODBlN2ExNmY0ZTNmMDY2OlVTRVJfTE9HSU46Om10NUB5b3BtYWlsLmNvbTo6X186Ol9fOjoxMTUuNzkuNTUuMTc3OjoyODU0YWRmNy05OWM5LTQyNTEtOWU2Yy0xNGJiOTRkYWZlNDQ6YzI0ODQ0YzU1NjdkMjU5ZWRmODBmMWJmYzQyNDk0YmZjYmFkN2Y3Zjc0NjYwNTJhMDRkYTQxNDI3ZjE4OWQ0ODZjMTk5NzVmYzAwMDQ3NDcyNDQ0MWM3ZTg1ZmFhNjRkMmE4MzI2ODhiMmViNjNmYjVjNzYxOTA1YzUyMWFhMjg=");
	}
	
	
	
	

}
