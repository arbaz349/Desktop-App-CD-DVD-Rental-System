package main_page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;

import customer.change_password;
import customer.customer_info2;
import customer.r_r_overdue;
import customer.rental_history;
import customer.update_customer;
import customer.video_search;
import login.login_sys;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class main_page_customer {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @param s 
	 */
	public static void main(String string, Socket s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_page_customer window = new main_page_customer(string,s);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param s 
	 */
	public main_page_customer(String customer_id, Socket s) {
		initialize(customer_id,s);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param s 
	 * @param string 
	 */
	private void initialize(String customer_id, Socket s) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 555);
		frame.setTitle("MAIN MENU");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnVideoSearch = new JButton("DVD Search");
		btnVideoSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				video_search.main(customer_id,s);
			}
		});
		btnVideoSearch.setBounds(37, 155, 298, 25);
		frame.getContentPane().add(btnVideoSearch);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setFont(new Font("Dialog", Font.BOLD, 25));
		lblMainMenu.setBounds(116, 88, 165, 25);
		frame.getContentPane().add(lblMainMenu);
		
		JButton btnRentreturnoverdueVideo = new JButton("Rent/Return/Rented DVD/Overdue DVD");
		btnRentreturnoverdueVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				r_r_overdue.main(customer_id,s);
			}
		});
		btnRentreturnoverdueVideo.setBounds(37, 213, 298, 25);
		frame.getContentPane().add(btnRentreturnoverdueVideo);
		
		JButton btnUpdateDetails = new JButton("Update Details");
		btnUpdateDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update_customer.main(customer_id,s);
			}
		});
		btnUpdateDetails.setBounds(37, 337, 298, 25);
		frame.getContentPane().add(btnUpdateDetails);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				change_password.main(customer_id,s);
			}
		});
		btnChangePassword.setBounds(37, 409, 298, 25);
		frame.getContentPane().add(btnChangePassword);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame1 = new JFrame("Log Out");
				if(JOptionPane.showConfirmDialog(frame1,"Confirm if you want to Log Out ?","Login System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
		{
					frame.setVisible(false);
					try {
						login_sys.main(s);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
			}
		});
		btnLogOut.setForeground(Color.RED);
		btnLogOut.setBounds(312, 482, 105, 25);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblUser = new JLabel("- "+ customer_id);
		lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUser.setForeground(Color.BLUE);
		lblUser.setFont(new Font("Dialog", Font.BOLD, 20));
		lblUser.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	         customer_info2.main(customer_id,s);
	          }});
		lblUser.setBounds(269, 22, 148, 32);
		frame.getContentPane().add(lblUser);
		
		JButton btnRentalHistory = new JButton("Rental History");
		btnRentalHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rental_history.main(customer_id,s);
			}
		});
		btnRentalHistory.setBounds(37, 276, 298, 25);
		frame.getContentPane().add(btnRentalHistory);
		
		JLabel lblNewLabel = new JLabel("Customer");
		lblNewLabel.setIcon(new ImageIcon(main_page_customer.class.getResource("/image/PicsArt_09-14-04.32.45.jpg")));
		lblNewLabel.setBounds(24, 10, 150, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("clip");
		lblNewLabel_1.setIcon(new ImageIcon(main_page_customer.class.getResource("/image/PicsArt_09-15-01.16.24.jpg")));
		lblNewLabel_1.setBounds(353, 88, 72, 346);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
