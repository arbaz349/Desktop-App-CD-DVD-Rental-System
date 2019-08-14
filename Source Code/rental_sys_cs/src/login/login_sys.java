package login;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import main_page.main_page_customer;
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
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(Socket s) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_sys window = new login_sys(s);
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
	public login_sys(Socket s) {
		initialize(s);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param s 
	 */
	private void initialize(Socket s) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 477, 376);
		frame.setTitle("LOGIN DETAILS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					DataInputStream dis;
					DataOutputStream dos;
					try {
						dis = new DataInputStream(s.getInputStream());
						dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("login_sys");
						dos.writeUTF(txtusername.getText());
						dos.writeUTF(txtpassword.getText());
						String str;
						str = dis.readUTF();
						if(str.equals("yes"))
						{
							frame.setVisible(false);
							main_page_customer.main(txtusername.getText(),s);
							txtusername.setText(null);
							txtpassword.setText(null);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "INVALID USERNAME AND PASSWORD !","Login Error !",JOptionPane.ERROR_MESSAGE);
							txtusername.setText(null);
							txtpassword.setText(null);
						}
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
			}
			}
		});
		btnNewButton.setBounds(41, 244, 99, 27);
		frame.getContentPane().add(btnNewButton);
		
		txtusername = new JTextField();
		txtusername.setBounds(184, 109, 200, 33);
		frame.getContentPane().add(txtusername);
		txtusername.setColumns(10);
		txtpassword.setBounds(184, 154, 200, 33);
		frame.getContentPane().add(txtpassword);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			txtusername.setText(null);
			txtpassword.setText(null);
			}
		});
		btnNewButton_1.setBounds(180, 244, 99, 27);
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
		btnNewButton_2.setBounds(322, 244, 99, 27);
		frame.getContentPane().add(btnNewButton_2);
		separator.setBounds(41, 223, 380, 33);
		frame.getContentPane().add(separator);
		lblNewLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		lblNewLabel.setBounds(41, 109, 121, 33);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel_1.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		lblNewLabel_1.setBounds(41, 154, 121, 33);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setIcon(new ImageIcon(login_sys.class.getResource("/image/PicsArt_09-14-04.32.45.jpg")));
		lblNewLabel_2.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		lblNewLabel_2.setBounds(158, 22, 153, 61);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel click = new JLabel("New User ?");
		click.setForeground(Color.BLUE);
		click.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	         new_login_sys.main(s);
	           }});
		click.setBounds(45, 296, 82, 15);
		frame.getContentPane().add(click);
		
		JLabel click2= new JLabel("Forget Password ?");
		click2.setForeground(Color.RED);
		click2.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	         pass_reset.main(s);
	          }});
		click2.setBounds(301, 296, 133, 15);
		frame.getContentPane().add(click2);
			}
}
