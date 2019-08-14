package administrator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class change_price {

	private JFrame frame;
	private JTextField txtdvd;
	private JTextField txtprice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					change_price window = new change_price();
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
	public change_price() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 636, 378);
		frame.setTitle("CHANGE RENTAL PRICE");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblChangeRentalPrice = new JLabel("Change Rental Price -");
		lblChangeRentalPrice.setFont(new Font("Dialog", Font.BOLD, 23));
		lblChangeRentalPrice.setBounds(23, 12, 287, 33);
		frame.getContentPane().add(lblChangeRentalPrice);
		
		JLabel label_1 = new JLabel("Search By -");
		label_1.setBounds(33, 92, 85, 15);
		frame.getContentPane().add(label_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(136, 87, 217, 24);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("DVD ID");
		comboBox.addItem("DVD NAME");
		
		JLabel label = new JLabel("*Enter "+comboBox.getSelectedItem()+" -");
		label.setBounds(33, 151, 132, 31);
		frame.getContentPane().add(label);
		
		txtdvd = new JTextField();
		txtdvd.setColumns(10);
		txtdvd.setBounds(226, 157, 284, 19);
		frame.getContentPane().add(txtdvd);
		
		JButton button = new JButton("Change");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtdvd.getText().trim().length()==0 || txtprice.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, comboBox.getSelectedItem()+" OR PRICE FIELD CAN NOT BE EMPTY !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				try
				{
				@SuppressWarnings("unused")
				double d =Double.parseDouble(txtprice.getText());
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
					String sid;
					if(comboBox.getSelectedItem().equals("DVD ID"))
						sid = "select * from dvd where dvd_id = ? and rented ='No' ";
					else
						sid = "select * from dvd where name = ? and rented ='No' ";
					PreparedStatement ps =con.prepareStatement(sid);
					ps.setString(1,txtdvd.getText().toString());
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						JFrame frame1 = new JFrame("Rental Price");
						if(JOptionPane.showConfirmDialog(frame1,"Confirm if you want to Change Rental Price of DVD ?","Change Price",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
						{
							String did;
							if(comboBox.getSelectedItem().equals("DVD ID"))
								did = "update dvd set price = ? where dvd_id = ? ";
							else
								did = "update dvd set price = ? where name = ? ";
							
							PreparedStatement ps1 =con.prepareStatement(did);
							double rp = Double.parseDouble(txtprice.getText());
							ps1.setDouble(1,rp);
							ps1.setString(2,txtdvd.getText().toString());
							ps1.executeUpdate();
							JOptionPane.showMessageDialog(null, "DVD PRICE IS UPDATED !","Done !",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "DVD NOT FOUND !","Error !",JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				txtdvd.setText(null);
				txtprice.setText(null);
			}
			catch(NumberFormatException a)
			{
				JOptionPane.showMessageDialog(null,"PLEASE ENTER VALID PRICE !","Error !",JOptionPane.ERROR_MESSAGE);
			}
			}
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(33, 279, 114, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtdvd.setText(null);
				txtprice.setText(null);
			}
		});
		button_1.setBounds(270, 279, 114, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		button_2.setBounds(482, 279, 114, 25);
		frame.getContentPane().add(button_2);
		
		JLabel lblEnterRentalPrice = new JLabel("*Enter Rental Price -");
		lblEnterRentalPrice.setBounds(33, 209, 159, 31);
		frame.getContentPane().add(lblEnterRentalPrice);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(226, 215, 284, 19);
		frame.getContentPane().add(txtprice);
		
		JLabel label_2 = new JLabel("logo");
		label_2.setIcon(new ImageIcon(change_price.class.getResource("/image/PicsArt_09-15-02.34.27.jpg")));
		label_2.setBounds(490, 12, 134, 112);
		frame.getContentPane().add(label_2);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label.setText("*Enter "+comboBox.getSelectedItem()+" -");
			}
		}
		);
	}
}
