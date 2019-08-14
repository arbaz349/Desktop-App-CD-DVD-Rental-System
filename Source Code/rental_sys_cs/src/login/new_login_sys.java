package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

import customer.customer_info;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class new_login_sys {

	private JFrame frame;
	private JTextField txtusername;
	private JTextField txtemail;
	private JTextField txtanswer;
	private JPasswordField txtpassword;
	private JPasswordField txtpasswordre;

	/**
	 * Launch the application.
	 * @return 
	 */
	public static void main(Socket s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new_login_sys window = new new_login_sys(s);
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
	public new_login_sys(Socket s) {
		initialize(s);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param s 
	 */
	private void initialize(Socket s) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 582, 492);
		frame.setTitle("NEW CUSTOMER");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewUser = new JLabel("New Customer -");
		lblNewUser.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewUser.setBounds(37, 25, 203, 15);
		frame.getContentPane().add(lblNewUser);
		
		JLabel label = new JLabel("*Username -");
		label.setBounds(39, 94, 145, 15);
		frame.getContentPane().add(label);
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		txtusername.setBounds(216, 94, 327, 25);
		frame.getContentPane().add(txtusername);
		
		JLabel label_1 = new JLabel("*Email Address -");
		label_1.setBounds(36, 239, 180, 15);
		frame.getContentPane().add(label_1);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(213, 239, 330, 25);
		frame.getContentPane().add(txtemail);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(216, 288, 327, 24);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("What is Pet Name ?");
		comboBox.addItem("What is Favourite Sport Game ?");
		comboBox.addItem("What is School Name ?");
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(txtusername.getText().trim().length()==0 || txtpassword.getText().trim().length()==0 ||
					txtpasswordre.getText().trim().length()==0 || txtemail.getText().trim().length()==0 || 
					txtanswer.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, " * FIELD ARE COMPULSORY !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				if(txtpassword.getText().equals(txtpasswordre.getText()))
				{
					DataInputStream dis;
					DataOutputStream dos = null;
					String st = (String) comboBox.getSelectedItem();
					String stry = null;
						try {
							dis = new DataInputStream(s.getInputStream());
							dos = new DataOutputStream(s.getOutputStream());
							dos.writeUTF("new_login_sys");
							dos.writeUTF(txtusername.getText());
							 stry= dis.readUTF();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					if(stry.equals("yes"))
					{
						JOptionPane.showMessageDialog(null, "USERNAME IS ALREADY PRESENT !","ERROR !",JOptionPane.ERROR_MESSAGE);
						txtusername.setText(null);
						txtpasswordre.setText(null);
						txtemail.setText(null);
						txtpassword.setText(null);
						txtanswer.setText(null);
					}
					else
					{
						try {
							dos.writeUTF(txtpassword.getText());
							dos.writeUTF(txtemail.getText());
							dos.writeUTF(txtanswer.getText());
							dos.writeUTF(st);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, "CUSTOMER IS ADDED !","DONE !",JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);
						customer_info.main(txtusername.getText(),s);
						login_sys o = new login_sys(s);
						o.frame.setVisible(false);
						txtusername.setText(null);
						txtpasswordre.setText(null);
						txtemail.setText(null);
						txtpassword.setText(null);
						txtanswer.setText(null);
						
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "RE ENTERED PASSWORD IS INCORRECT !","Error !",JOptionPane.ERROR_MESSAGE);
					txtpasswordre.setText(null);
					txtpassword.setText(null);
				}
			}	
			}
		});
		btnSignIn.setForeground(Color.RED);
		btnSignIn.setBounds(27, 396, 114, 25);
		frame.getContentPane().add(btnSignIn);
		
		JButton button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtusername.setText(null);
				txtpasswordre.setText(null);
				txtemail.setText(null);
				txtpassword.setText(null);
				txtanswer.setText(null);
			}
		});
		button_1.setBounds(230, 396, 114, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				try {
					login_sys.main(null);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_2.setBounds(429, 396, 114, 25);
		frame.getContentPane().add(button_2);
		
		JLabel label_2 = new JLabel("*Security Question -");
		label_2.setBounds(39, 286, 177, 15);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("*Answer -");
		label_3.setBounds(36, 336, 145, 15);
		frame.getContentPane().add(label_3);
		
		txtanswer = new JTextField();
		txtanswer.setColumns(10);
		txtanswer.setBounds(213, 336, 330, 25);
		frame.getContentPane().add(txtanswer);
		
		JLabel label_4 = new JLabel("*Password -");
		label_4.setBounds(39, 143, 145, 15);
		frame.getContentPane().add(label_4);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(216, 140, 327, 25);
		frame.getContentPane().add(txtpassword);
		
		JLabel label_5 = new JLabel("*Re-Enter Password -");
		label_5.setBounds(39, 191, 177, 15);
		frame.getContentPane().add(label_5);
		
		txtpasswordre = new JPasswordField();
		txtpasswordre.setBounds(216, 188, 327, 25);
		frame.getContentPane().add(txtpasswordre);
		
		JLabel lblLogo = new JLabel("logo");
		lblLogo.setIcon(new ImageIcon(new_login_sys.class.getResource("/image/PicsArt_09-15-01.27.30.jpg")));
		lblLogo.setBounds(325, 12, 218, 50);
		frame.getContentPane().add(lblLogo);
	}
}
