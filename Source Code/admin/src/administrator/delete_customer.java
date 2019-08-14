package administrator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class delete_customer {

	private JFrame frame;
	private JTextField txtcustomerid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					delete_customer window = new delete_customer();
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
	public delete_customer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 611, 364);
		frame.setTitle("DELETE CUSTOMER");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("*Enter Customer ID -");
		label.setBounds(28, 118, 161, 31);
		frame.getContentPane().add(label);
		
		txtcustomerid = new JTextField();
		txtcustomerid.setColumns(10);
		txtcustomerid.setBounds(221, 124, 284, 19);
		frame.getContentPane().add(txtcustomerid);
		
		JButton button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
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
						JFrame frame1 = new JFrame("Exit");
						if(JOptionPane.showConfirmDialog(frame1,"Confirm if you want to Delete Customer ?","Delete Customer",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
						{
							PreparedStatement ps1 =con.prepareStatement("delete from customer where cust_id = ? ");
							ps1.setString(1,txtcustomerid.getText().toString());
							ps1.executeUpdate();
							PreparedStatement ps2 =con.prepareStatement("delete from login where username = ? ");
							ps2.setString(1,txtcustomerid.getText().toString());
							ps2.executeUpdate();
							frame.setVisible(false);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "CUSTOMER NOT FOUND !","Error !",JOptionPane.ERROR_MESSAGE);
						txtcustomerid.setText(null);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(28, 237, 114, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtcustomerid.setText(null);
			}
		});
		button_1.setBounds(265, 237, 114, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		button_2.setBounds(477, 237, 114, 25);
		frame.getContentPane().add(button_2);
		
		JLabel lblDeleteCustomer = new JLabel("Delete Customer  -");
		lblDeleteCustomer.setFont(new Font("Dialog", Font.BOLD, 23));
		lblDeleteCustomer.setBounds(25, 26, 253, 33);
		frame.getContentPane().add(lblDeleteCustomer);
		
		JLabel label_1 = new JLabel("logo");
		label_1.setIcon(new ImageIcon(delete_customer.class.getResource("/image/PicsArt_09-15-01.27.30.jpg")));
		label_1.setBounds(373, 12, 218, 50);
		frame.getContentPane().add(label_1);
	}
}
