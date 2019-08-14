package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.ImageIcon;

public class update_customer {

	private JFrame frame;
	private JTextField txtfirstnm;
	private JTextField txtlastnm;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JDateChooser txtdate;
	private JButton btnUpdate;
	private JButton button_1;
	private JLabel lblLogo;


	/**
	 * Launch the application.
	 * @param s 
	 */
	public static void main(String customer_id, Socket s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					update_customer window = new update_customer(customer_id,s);
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
	public update_customer(String customer_id, Socket s) {
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
		frame.setBounds(100, 100, 585, 398);
		frame.setTitle("UPDATE CUSTOMER DETAILS");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("*First Name -");
		lblFirstName.setBounds(25, 86, 98, 15);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("*Last Name -");
		lblLastName.setBounds(317, 86, 99, 15);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblAddress = new JLabel("*Address -");
		lblAddress.setBounds(25, 130, 98, 15);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblPhoneNo = new JLabel("*Phone No -");
		lblPhoneNo.setBounds(28, 205, 95, 15);
		frame.getContentPane().add(lblPhoneNo);
		
		JLabel lblDateOfBirth = new JLabel("*Date of Birth -");
		lblDateOfBirth.setBounds(28, 254, 114, 15);
		frame.getContentPane().add(lblDateOfBirth);
		
		txtfirstnm = new JTextField();
		txtfirstnm.setBounds(150, 86, 124, 19);
		frame.getContentPane().add(txtfirstnm);
		txtfirstnm.setColumns(10);
		
		txtlastnm = new JTextField();
		txtlastnm.setBounds(434, 84, 124, 19);
		frame.getContentPane().add(txtlastnm);
		txtlastnm.setColumns(10);
		
		txtaddress = new JTextField();
		txtaddress.setBounds(147, 132, 411, 44);
		frame.getContentPane().add(txtaddress);
		txtaddress.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setBounds(150, 205, 411, 19);
		frame.getContentPane().add(txtphone);
		txtphone.setColumns(10);
		
		txtdate = new JDateChooser();
		txtdate.setBounds(150, 254, 202, 19);
		frame.getContentPane().add(txtdate);
		txtdate.setDateFormatString("yyyy-MM-dd");
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtfirstnm.getText().trim().length()==0 || txtlastnm.getText().trim().length()==0 ||
						txtaddress.getText().trim().length()==0 || txtphone.getText().trim().length()==0 || 
						((JTextField)txtdate.getDateEditor().getUiComponent()).getText().trim().length()==0 )
					{
						JOptionPane.showMessageDialog(null, " * FIELD ARE COMPULSORY !","Error !",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
					try
					{
						@SuppressWarnings("unused")
						int i = Integer.parseInt(txtphone.getText());
					try
					{
					@SuppressWarnings("unused")
					LocalDate dt1 =LocalDate.parse(((JTextField)txtdate.getDateEditor().getUiComponent()).getText());
					DataInputStream dis;
					DataOutputStream dos;
					try {
						dis = new DataInputStream(s.getInputStream());
						dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("update_customer");
						dos.writeUTF(customer_id);
						dos.writeUTF(txtfirstnm.getText());
						dos.writeUTF(txtlastnm.getText());
						dos.writeUTF(txtaddress.getText());
						dos.writeUTF(txtphone.getText());
						dos.writeUTF(((JTextField)txtdate.getDateEditor().getUiComponent()).getText());
						String str;
						str = dis.readUTF();
						if(str.equals("yes"))
						{
							txtfirstnm.setText(null);
							txtlastnm.setText(null);
							txtaddress.setText(null);
							txtphone.setText(null);
							txtdate.setDate(null);
							JOptionPane.showMessageDialog(null, "CUSTOMER INFO IS UPDATED !","DONE !",JOptionPane.INFORMATION_MESSAGE);
							frame.setVisible(false);
						}
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
				catch(DateTimeParseException a)
				{
					JOptionPane.showMessageDialog(null,"PLEASE ENTER VALID BIRTH DATE !","Error !",JOptionPane.ERROR_MESSAGE);
					txtdate.setDate(null);
				}
				}
				catch(NumberFormatException a)
				{
					JOptionPane.showMessageDialog(null,"PLEASE ENTER VALID PHONE NO !","Error !",JOptionPane.ERROR_MESSAGE);
					txtphone.setText(null);
				}
			}	
			}
		});
		btnUpdate.setForeground(Color.RED);
		btnUpdate.setBounds(43, 316, 114, 25);
		frame.getContentPane().add(btnUpdate);
		
		button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtfirstnm.setText(null);
				txtlastnm.setText(null);
				txtaddress.setText(null);
				txtphone.setText(null);
				txtdate.setDate(null);
			}
		});
		button_1.setBounds(240, 316, 114, 25);
		frame.getContentPane().add(button_1);
		JLabel lblCustomerDetails = new JLabel("Update Customer Details -");
		lblCustomerDetails.setFont(new Font("Dialog", Font.BOLD, 23));
		lblCustomerDetails.setBounds(25, 23, 357, 33);
		frame.getContentPane().add(lblCustomerDetails);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		btnCancel.setBounds(427, 316, 114, 25);
		frame.getContentPane().add(btnCancel);
		
		lblLogo = new JLabel("logo");
		lblLogo.setIcon(new ImageIcon(update_customer.class.getResource("/image/PicsArt_09-14-04.32.45.jpg")));
		lblLogo.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		lblLogo.setBounds(409, 12, 153, 52);
		frame.getContentPane().add(lblLogo);
		
	}
}
