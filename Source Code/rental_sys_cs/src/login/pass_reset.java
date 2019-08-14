package login;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class pass_reset {

	private JFrame frame;
	private JTextField txtanswer;
	private JTextField txtemail;
	private JPasswordField txtpassword;
	private JPasswordField txtpasswordre;
	private JTextField txtusername;
	JComboBox<String> comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(Socket s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pass_reset window = new pass_reset(s);
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
	public pass_reset(Socket s) {
		initialize(s);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param s 
	 */
	private void initialize(Socket s) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 612, 508);
		frame.setTitle("CHANGE PASSWORD");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCreateNewPassword = new JLabel("Create New Password -");
		lblCreateNewPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCreateNewPassword.setBounds(26, 37, 206, 15);
		frame.getContentPane().add(lblCreateNewPassword);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(241, 159, 326, 24);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("What is Pet Name ?");
		comboBox.addItem("What is Favourite Sport Game ?");
		comboBox.addItem("What is School Name ?");
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
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
					String s1 = (String) comboBox.getSelectedItem();
				if(txtpassword.getText().equals(txtpasswordre.getText()))
				{
					String st = null;
					try {
						DataInputStream dis = new DataInputStream(s.getInputStream());
						DataOutputStream dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("pass_reset");
						dos.writeUTF(txtusername.getText());
						dos.writeUTF(txtpassword.getText());
						dos.writeUTF(txtemail.getText());
						dos.writeUTF(txtanswer.getText());
						dos.writeUTF(s1);
						st = dis.readUTF();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(st.equals("yes"))
					{
						txtusername.setText(null);
						txtpasswordre.setText(null);
						txtemail.setText(null);
						txtpassword.setText(null);
						txtanswer.setText(null);
						JOptionPane.showMessageDialog(null, "PASSWORD IS CHANGED !","DONE !",JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);
					}
					else
					{
						txtusername.setText(null);
						txtpasswordre.setText(null);
						txtemail.setText(null);
						txtpassword.setText(null);
						txtanswer.setText(null);
						JOptionPane.showMessageDialog(null, "INVALID USERNAME !","Error !",JOptionPane.ERROR_MESSAGE);
					}
				
				}
				else
				{
					txtpasswordre.setText(null);
					txtpassword.setText(null);
					JOptionPane.showMessageDialog(null, "RE ENTERED PASSWORD IS INCORRECT !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				}		
			}
		});
								
		button.setForeground(Color.RED);
		button.setBounds(38, 412, 114, 25);
		frame.getContentPane().add(button);
		
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
		button_1.setBounds(260, 412, 114, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
					try {
						login_sys.main(s);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		});
		button_2.setBounds(453, 412, 114, 25);
		frame.getContentPane().add(button_2);
		
		JLabel lblAnswers = new JLabel("*Answer -");
		lblAnswers.setBounds(38, 214, 145, 15);
		frame.getContentPane().add(lblAnswers);
		
		txtanswer = new JTextField();
		txtanswer.setColumns(10);
		txtanswer.setBounds(238, 207, 329, 25);
		frame.getContentPane().add(txtanswer);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(238, 255, 329, 25);
		frame.getContentPane().add(txtemail);
		
		JLabel label_1 = new JLabel("*Email Address -");
		label_1.setBounds(38, 262, 145, 15);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("*Password -");
		label_2.setBounds(38, 314, 145, 15);
		frame.getContentPane().add(label_2);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(238, 304, 329, 25);
		frame.getContentPane().add(txtpassword);
		
		txtpasswordre = new JPasswordField();
		txtpasswordre.setBounds(238, 352, 329, 25);
		frame.getContentPane().add(txtpasswordre);
		
		JLabel label_3 = new JLabel("*Re-Enter Password");
		label_3.setBounds(38, 362, 164, 15);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("*Username -");
		label_4.setBounds(41, 117, 145, 15);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("*Security Question -");
		label_5.setBounds(41, 164, 161, 15);
		frame.getContentPane().add(label_5);
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		txtusername.setBounds(241, 110, 326, 25);
		frame.getContentPane().add(txtusername);
		
		JLabel label = new JLabel("logo");
		label.setIcon(new ImageIcon(pass_reset.class.getResource("/image/PicsArt_09-15-01.27.30.jpg")));
		label.setBounds(349, 12, 218, 50);
		frame.getContentPane().add(label);

	}
}
