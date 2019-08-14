package customer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class customer_info {

	private JFrame frame;
	private JTextField txtfirstnm;
	private JTextField txtlastnm;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JDateChooser txtdate;
	private JButton button;
	private JButton button_1;

	/**
	 * Launch the application.
	 * @param s 
	 */
	public static void main(String string, Socket s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customer_info window = new customer_info(string,s);
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
	 * @param string 
	 */
	public customer_info(String customer_id, Socket s) {
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
		frame.setTitle("CUSTOMER INFORMATION");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("*First Name -");
		lblFirstName.setBounds(25, 86, 98, 15);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("*Last Name -");
		lblLastName.setBounds(317, 86, 95, 15);
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
		txtfirstnm.setBounds(148, 86, 124, 19);
		frame.getContentPane().add(txtfirstnm);
		txtfirstnm.setColumns(10);
		
		txtlastnm = new JTextField();
		txtlastnm.setBounds(432, 84, 124, 19);
		frame.getContentPane().add(txtlastnm);
		txtlastnm.setColumns(10);
		
		txtaddress = new JTextField();
		txtaddress.setBounds(145, 132, 411, 44);
		frame.getContentPane().add(txtaddress);
		txtaddress.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setBounds(148, 205, 411, 19);
		frame.getContentPane().add(txtphone);
		txtphone.setColumns(10);
		
		txtdate = new JDateChooser();
		txtdate.setBounds(148, 254, 202, 19);
		frame.getContentPane().add(txtdate);
		txtdate.setDateFormatString("yyyy-MM-dd");

		
		button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtfirstnm.getText().trim().length()==0 || txtlastnm.getText().trim().length()==0 ||
					txtaddress.getText().trim().length()==0 || txtphone.getText().trim().length()==0 || 
					((JTextField)txtdate.getDateEditor().getUiComponent()).getText().trim().length()==0)
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
					LocalDate dt1 =LocalDate.parse((CharSequence)((JTextField)txtdate.getDateEditor().getUiComponent()).getText() );
					DataInputStream dis;
					DataOutputStream dos;
					try {
						dis = new DataInputStream(s.getInputStream());
						dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("customer_info");
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
							JOptionPane.showMessageDialog(null, "CUSTOMER INFO IS SAVED !","DONE !",JOptionPane.INFORMATION_MESSAGE);
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
		button.setForeground(Color.RED);
		button.setBounds(135, 315, 114, 25);
		frame.getContentPane().add(button);
		
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
		button_1.setBounds(320, 315, 114, 25);
		frame.getContentPane().add(button_1);
		
		JLabel lblCustomerDetails = new JLabel("Customer Details -");
		lblCustomerDetails.setFont(new Font("Dialog", Font.BOLD, 23));
		lblCustomerDetails.setBounds(25, 23, 243, 33);
		frame.getContentPane().add(lblCustomerDetails);
		
		JLabel lblNewLabel = new JLabel("- "+customer_id);
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(387, 27, 172, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("logo");
		label.setIcon(new ImageIcon(customer_info.class.getResource("/image/PicsArt_09-14-04.36.13.jpg")));
		label.setBounds(486, 270, 75, 70);
		frame.getContentPane().add(label);
	}
}
