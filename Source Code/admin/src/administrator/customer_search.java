package administrator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class customer_search {

	private JFrame frame;
	private JTextField txtcustomerid;
	private JTextField txtlastnm;
	private JTextField txtfirstnm;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JTextField txtdob;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customer_search window = new customer_search();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public customer_search() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 616, 505);
		frame.setTitle("CUSTOMER SEARCH");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterCustomerId = new JLabel("*Enter Customer ID -");
		lblEnterCustomerId.setBounds(28, 92, 175, 31);
		frame.getContentPane().add(lblEnterCustomerId);
		
		txtcustomerid = new JTextField();
		txtcustomerid.setBounds(221, 98, 284, 19);
		frame.getContentPane().add(txtcustomerid);
		txtcustomerid.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(31, 217, 557, 233);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("First Name -");
		label.setBounds(12, 27, 98, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Last Name -");
		label_1.setBounds(304, 27, 81, 15);
		panel.add(label_1);
		
		txtlastnm = new JTextField();
		txtlastnm.setBackground(Color.WHITE);
		txtlastnm.setEditable(false);
		txtlastnm.setColumns(10);
		txtlastnm.setBounds(418, 25, 124, 19);
		panel.add(txtlastnm);
		
		txtfirstnm = new JTextField();
		txtfirstnm.setBackground(Color.WHITE);
		txtfirstnm.setEditable(false);
		txtfirstnm.setColumns(10);
		txtfirstnm.setBounds(134, 27, 124, 19);
		panel.add(txtfirstnm);
		
		JLabel label_2 = new JLabel("Address -");
		label_2.setBounds(12, 71, 66, 15);
		panel.add(label_2);
		
		txtaddress = new JTextField();
		txtaddress.setBackground(Color.WHITE);
		txtaddress.setEditable(false);
		txtaddress.setColumns(10);
		txtaddress.setBounds(131, 73, 411, 44);
		panel.add(txtaddress);
		
		txtphone = new JTextField();
		txtphone.setBackground(Color.WHITE);
		txtphone.setEditable(false);
		txtphone.setColumns(10);
		txtphone.setBounds(134, 146, 411, 19);
		panel.add(txtphone);
		
		JLabel label_3 = new JLabel("Phone No -");
		label_3.setBounds(15, 146, 95, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Date of Birth -");
		label_4.setBounds(15, 195, 109, 15);
		panel.add(label_4);
		
		txtdob = new JTextField();
		txtdob.setBackground(Color.WHITE);
		txtdob.setEditable(false);
		txtdob.setColumns(10);
		txtdob.setBounds(134, 195, 202, 19);
		panel.add(txtdob);
		panel.setVisible(false);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtcustomerid.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "CUSTOMER ID FIELD CAN NOT BE EMPTY !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
					PreparedStatement ps =con.prepareStatement("select * from customer where cust_id = ? ");
					ps.setString(1,txtcustomerid.getText().toString());
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						txtfirstnm.setText(rs.getString("firstname"));
						txtlastnm.setText(rs.getString("lastname"));
						txtaddress.setText(rs.getString("address"));
						txtphone.setText(rs.getString("mobno"));
						txtdob.setText(rs.getString("dob"));
						panel.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "CUSTOMER NOT FOUND !","Error !",JOptionPane.ERROR_MESSAGE);
						txtcustomerid.setText(null);
						txtfirstnm.setText(null);
						txtlastnm.setText(null);
						txtaddress.setText(null);
						txtphone.setText(null);
						txtdob.setText(null);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
			}
		});
		btnSearch.setForeground(Color.RED);
		btnSearch.setBounds(25, 154, 114, 25);
		frame.getContentPane().add(btnSearch);
		
		JButton button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtcustomerid.setText(null);
				txtfirstnm.setText(null);
				txtlastnm.setText(null);
				txtaddress.setText(null);
				txtphone.setText(null);
				txtdob.setText(null);
				panel.setVisible(false);
			}
		});
		button_1.setBounds(262, 154, 114, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		button_2.setBounds(474, 154, 114, 25);
		frame.getContentPane().add(button_2);
		
		JLabel lblSearchCustomer = new JLabel("Search Customer  -");
		lblSearchCustomer.setFont(new Font("Dialog", Font.BOLD, 23));
		lblSearchCustomer.setBounds(28, 31, 253, 33);
		frame.getContentPane().add(lblSearchCustomer);
		
		JLabel label_5 = new JLabel("logo");
		label_5.setIcon(new ImageIcon(customer_search.class.getResource("/image/PicsArt_09-14-04.36.13.jpg")));
		label_5.setBounds(529, 12, 75, 70);
		frame.getContentPane().add(label_5);
		
		
	}
}
