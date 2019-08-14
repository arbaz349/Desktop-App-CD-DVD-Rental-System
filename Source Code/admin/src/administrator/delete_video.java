package administrator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class delete_video {

	private JFrame frame;
	private JTextField txtdvd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					delete_video window = new delete_video();
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
	public delete_video() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 639, 355);
		frame.setTitle("DELETE DVD");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_1 = new JLabel("Delete DVD -");
		label_1.setFont(new Font("Dialog", Font.BOLD, 23));
		label_1.setBounds(23, 22, 253, 33);
		frame.getContentPane().add(label_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(136, 97, 217, 24);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("DVD ID");
		comboBox.addItem("DVD NAME");
		
		JLabel lblSearchBy = new JLabel("Search By -");
		lblSearchBy.setBounds(33, 102, 85, 15);
		frame.getContentPane().add(lblSearchBy);
		
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		button.setBounds(482, 243, 114, 25);
		frame.getContentPane().add(button);
		
		txtdvd = new JTextField();
		txtdvd.setColumns(10);
		txtdvd.setBounds(183, 179, 284, 19);
		frame.getContentPane().add(txtdvd);
		
		JButton button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtdvd.setText(null);
			}
		});
		button_1.setBounds(270, 243, 114, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtdvd.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(null, comboBox.getSelectedItem()+" CAN NOT BE EMPTY !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
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
						frame = new JFrame("Exit");
						if(JOptionPane.showConfirmDialog(frame,"Confirm if you want to Delete DVD ?","Delete DVD",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
						{
							String did;
							if(comboBox.getSelectedItem().equals("DVD ID"))
								did = "delete from dvd where dvd_id = ? ";
							else
								did = "delete from dvd where name = ? ";
							
							PreparedStatement ps1 =con.prepareStatement(did);
							ps1.setString(1,txtdvd.getText().toString());
							ps1.executeUpdate();
							JOptionPane.showMessageDialog(null, "DVD IS DELETED !","Done !",JOptionPane.INFORMATION_MESSAGE);
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
			}
			}
		});
		button_2.setForeground(Color.RED);
		button_2.setBounds(33, 243, 114, 25);
		frame.getContentPane().add(button_2);
		
		JLabel lblenterNull = new JLabel("*Enter "+comboBox.getSelectedItem()+" -");
		lblenterNull.setBounds(33, 173, 132, 31);
		frame.getContentPane().add(lblenterNull);
		
		JLabel label = new JLabel("logo");
		label.setIcon(new ImageIcon(delete_video.class.getResource("/image/PicsArt_09-14-04.36.13.jpg")));
		label.setBounds(531, 16, 75, 70);
		frame.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("logo");
		label_2.setIcon(new ImageIcon(delete_video.class.getResource("/image/PicsArt_09-15-02.04.45.jpg")));
		label_2.setBounds(534, 144, 72, 54);
		frame.getContentPane().add(label_2);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblenterNull.setText("*Enter "+comboBox.getSelectedItem()+" -");
			}
		}
		);
		
	}
}
