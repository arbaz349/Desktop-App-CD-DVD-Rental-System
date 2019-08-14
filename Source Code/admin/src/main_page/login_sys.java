package main_page;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import main_page.main_pg;
import javax.swing.ImageIcon;

public class login_sys {

	public JFrame frame;
	private JTextField txtusername;
	private final JPasswordField txtpassword = new JPasswordField();
	private final JSeparator separator = new JSeparator();
	private final JLabel lblNewLabel = new JLabel("Username");
	private final JLabel lblNewLabel_1 = new JLabel("Password");
	private final JLabel lblNewLabel_2 = new JLabel("LOGIN DETAILS");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_sys window = new login_sys();
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
	public login_sys() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 477, 376);
		frame.setTitle("LOGIN DETAILS");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		btnNewButton.setForeground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(txtusername.getText().trim().length()==0 || txtpassword.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(null, "USERNAME AND PASSWORD CAN NOT BE EMPTY","Login Error !",JOptionPane.ERROR_MESSAGE);
					txtusername.setText(null);
					txtpassword.setText(null);
				}
				else
				{
				if(txtusername.getText().equalsIgnoreCase("admin") && txtpassword.getText().equalsIgnoreCase("admin"))
				{
					frame.setVisible(false);
					main_pg.main(null);
				}
				else
				{	
					JOptionPane.showMessageDialog(null, "INVALID USERNAME AND PASSWORD !","Login Error !",JOptionPane.ERROR_MESSAGE);
					txtusername.setText(null);
					txtpassword.setText(null);
				}
			}
			}
		});
		btnNewButton.setBounds(45, 286, 99, 27);
		frame.getContentPane().add(btnNewButton);
		
		txtusername = new JTextField();
		txtusername.setBounds(188, 151, 200, 33);
		frame.getContentPane().add(txtusername);
		txtusername.setColumns(10);
		txtpassword.setBounds(188, 196, 200, 33);
		frame.getContentPane().add(txtpassword);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			txtusername.setText(null);
			txtpassword.setText(null);
			}
		});
		btnNewButton_1.setBounds(184, 286, 99, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame1 = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame1,"Confirm if you want to Exit ?","Login System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
		{
			System.exit(0);
		}
			}
		});
		btnNewButton_2.setBounds(326, 286, 99, 27);
		frame.getContentPane().add(btnNewButton_2);
		separator.setBounds(45, 265, 380, 33);
		frame.getContentPane().add(separator);
		lblNewLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		lblNewLabel.setBounds(45, 151, 121, 33);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel_1.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		lblNewLabel_1.setBounds(45, 196, 121, 33);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setIcon(new ImageIcon(login_sys.class.getResource("/image/PicsArt_09-14-04.32.45.jpg")));
		lblNewLabel_2.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		lblNewLabel_2.setBounds(158, 22, 153, 61);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblAdministration = new JLabel("Administration");
		lblAdministration.setFont(new Font("Dialog", Font.BOLD, 20));
		lblAdministration.setBounds(158, 95, 176, 15);
		frame.getContentPane().add(lblAdministration);
		
		}
}
