package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;

public class customer_info2 {

	private JFrame frame;
	private JTextField txtlastnm;
	private JTextField txtfirstnm;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JTextField txtdob;

	/**
	 * Launch the application.
	 * @param s 
	 */
	public static void main(String customer_id, Socket s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customer_info2 window = new customer_info2(customer_id,s);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param customer_id 
	 * @param s 
	 */
	public customer_info2(String customer_id, Socket s) {
		initialize(customer_id,s);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param customer_id 
	 * @param s 
	 */
	private void initialize(String customer_id, Socket s) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 616, 361);
		frame.setTitle("CUSTOMER INFORMATION");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSearchCustomer = new JLabel("YOUR INFORMATION -");
		lblSearchCustomer.setFont(new Font("Dialog", Font.BOLD, 23));
		lblSearchCustomer.setBounds(28, 31, 302, 33);
		frame.getContentPane().add(lblSearchCustomer);
		
		JLabel label_5 = new JLabel("logo");
		label_5.setIcon(new ImageIcon(customer_info2.class.getResource("/image/PicsArt_09-14-04.36.13.jpg")));
		label_5.setBounds(529, 12, 75, 70);
		frame.getContentPane().add(label_5);
		
		JLabel label = new JLabel("First Name -");
		label.setBounds(28, 107, 98, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Last Name -");
		label_1.setBounds(320, 107, 81, 15);
		frame.getContentPane().add(label_1);
		
		txtlastnm = new JTextField();
		txtlastnm.setEditable(false);
		txtlastnm.setColumns(10);
		txtlastnm.setBackground(Color.WHITE);
		txtlastnm.setBounds(434, 105, 124, 19);
		frame.getContentPane().add(txtlastnm);
		
		txtfirstnm = new JTextField();
		txtfirstnm.setEditable(false);
		txtfirstnm.setColumns(10);
		txtfirstnm.setBackground(Color.WHITE);
		txtfirstnm.setBounds(150, 107, 124, 19);
		frame.getContentPane().add(txtfirstnm);
		
		JLabel label_2 = new JLabel("Address -");
		label_2.setBounds(28, 151, 66, 15);
		frame.getContentPane().add(label_2);
		
		txtaddress = new JTextField();
		txtaddress.setEditable(false);
		txtaddress.setColumns(10);
		txtaddress.setBackground(Color.WHITE);
		txtaddress.setBounds(147, 153, 411, 44);
		frame.getContentPane().add(txtaddress);
		
		txtphone = new JTextField();
		txtphone.setEditable(false);
		txtphone.setColumns(10);
		txtphone.setBackground(Color.WHITE);
		txtphone.setBounds(150, 226, 411, 19);
		frame.getContentPane().add(txtphone);
		
		JLabel label_3 = new JLabel("Phone No -");
		label_3.setBounds(31, 226, 95, 15);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Date of Birth -");
		label_4.setBounds(31, 275, 109, 15);
		frame.getContentPane().add(label_4);
		
		txtdob = new JTextField();
		txtdob.setEditable(false);
		txtdob.setColumns(10);
		txtdob.setBackground(Color.WHITE);
		txtdob.setBounds(150, 275, 202, 19);
		frame.getContentPane().add(txtdob);
		
		DataInputStream dis;
		DataOutputStream dos;
		try {
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("customer_info2");
			dos.writeUTF(customer_id);
			String str;
			str = dis.readUTF();
			if(str.equals("yes"))
			{
				txtfirstnm.setText(dis.readUTF());
				txtlastnm.setText(dis.readUTF());
				txtaddress.setText(dis.readUTF());
				txtphone.setText(dis.readUTF());
				txtdob.setText(dis.readUTF());
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
}
