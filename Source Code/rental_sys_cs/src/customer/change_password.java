package customer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class change_password {

	private JFrame frame;
	private JPasswordField txtpassword;
	private JPasswordField txtpasswordre;
	private JPasswordField txtoldpassword;

	/**
	 * Launch the application.
	 * @param s 
	 */
	public static void main(String customer_id, Socket s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					change_password window = new change_password(customer_id,s);
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
	public change_password(String customer_id, Socket s) {
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
		frame.setBounds(100, 100, 536, 356);
		frame.setTitle("CHANGE PASSWORD");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewPassword = new JLabel("*New Password -");
		lblNewPassword.setBounds(25, 151, 150, 15);
		frame.getContentPane().add(lblNewPassword);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(225, 141, 280, 25);
		frame.getContentPane().add(txtpassword);
		
		JLabel lblReenterNewPassword = new JLabel("*Re-Enter New Password -");
		lblReenterNewPassword.setBounds(25, 199, 182, 15);
		frame.getContentPane().add(lblReenterNewPassword);
		
		txtpasswordre = new JPasswordField();
		txtpasswordre.setBounds(225, 189, 280, 25);
		frame.getContentPane().add(txtpasswordre);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(txtoldpassword.getText().trim().length()==0 || txtpassword.getText().trim().length()==0 || txtpasswordre.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, " PASSWORD FIELD CAN NOT BE EMPTY !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				if(txtpassword.getText().equals(txtpasswordre.getText()))
				{
					DataInputStream dis;
					DataOutputStream dos;
					try {
						dis = new DataInputStream(s.getInputStream());
						dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("password_change");
						dos.writeUTF(customer_id);
						dos.writeUTF(txtoldpassword.getText());
						dos.writeUTF(txtpassword.getText());
						String str;
						str = dis.readUTF();
					if(str.equals("yes"))
					{
						txtpasswordre.setText(null);
						txtpassword.setText(null);
						txtoldpassword.setText(null);
						JOptionPane.showMessageDialog(null, "PASSWORD IS CHANGED !","DONE !",JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);
					}
					else
					{
						txtpasswordre.setText(null);
						txtpassword.setText(null);
						txtoldpassword.setText(null);
						JOptionPane.showMessageDialog(null, "INCORRECT OLD PASSWORD !","Error !",JOptionPane.ERROR_MESSAGE);
					}
				}
				 catch (IOException e) {
					
					e.printStackTrace();
				}
				}
				else
				{
					txtoldpassword.setText(null);
					txtpasswordre.setText(null);
					txtpassword.setText(null);
					JOptionPane.showMessageDialog(null, "RE ENTERED PASSWORD IS INCORRECT !","Error !",JOptionPane.ERROR_MESSAGE);
				}
			}
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(25, 265, 114, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtoldpassword.setText(null);
				txtpassword.setText(null);
				txtpasswordre.setText(null);
			}
		});
		button_1.setBounds(210, 265, 114, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		button_2.setBounds(391, 265, 114, 25);
		frame.getContentPane().add(button_2);
		
		JLabel lblOldPassword = new JLabel("*Old Password -");
		lblOldPassword.setBounds(25, 104, 150, 15);
		frame.getContentPane().add(lblOldPassword);
		
		txtoldpassword = new JPasswordField();
		txtoldpassword.setBounds(225, 94, 280, 25);
		frame.getContentPane().add(txtoldpassword);
		
		JLabel lblChangePassword = new JLabel("Change Password -");
		lblChangePassword.setFont(new Font("Dialog", Font.BOLD, 20));
		lblChangePassword.setBounds(25, 22, 216, 25);
		frame.getContentPane().add(lblChangePassword);
		
		JLabel label = new JLabel("logo");
		label.setIcon(new ImageIcon(change_password.class.getResource("/image/PicsArt_09-15-01.27.30.jpg")));
		label.setBounds(306, 12, 218, 50);
		frame.getContentPane().add(label);
	}
}
