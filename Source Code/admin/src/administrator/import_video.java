package administrator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class import_video {

	private JFrame frame;
	private JTextField txtdvd_id;
	private JTextField txtmovienm;
	private JTextField txtmain;
	private JTextField txt2ndmain;
	private JTextField txt3rdmain;
	private JTextField txttotalstock;
	private JTextField txtrentalprice;
	private JTextField txtaddinfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					import_video window = new import_video();
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
	public import_video() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 657, 737);
		frame.setTitle("IMPORT DVD");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDvdId = new JLabel("*DVD ID");
		lblDvdId.setBounds(27, 88, 53, 15);
		frame.getContentPane().add(lblDvdId);
		
		JLabel lblMovieName = new JLabel("*DVD Name");
		lblMovieName.setBounds(27, 132, 98, 15);
		frame.getContentPane().add(lblMovieName);
		
		JLabel lblMainCharacter = new JLabel("*Main Character :");
		lblMainCharacter.setBounds(27, 337, 114, 15);
		frame.getContentPane().add(lblMainCharacter);
		
		JLabel lblndMainCharacter = new JLabel("*2nd Main Character :");
		lblndMainCharacter.setBounds(27, 386, 149, 15);
		frame.getContentPane().add(lblndMainCharacter);
		
		JLabel lblrdMainCharacter = new JLabel("3rd Main Character :");
		lblrdMainCharacter.setBounds(27, 435, 142, 15);
		frame.getContentPane().add(lblrdMainCharacter);
		
		txtdvd_id = new JTextField();
		txtdvd_id.setColumns(10);
		txtdvd_id.setBounds(210, 84, 243, 19);
		frame.getContentPane().add(txtdvd_id);
		
		txtmovienm = new JTextField();
		txtmovienm.setColumns(10);
		txtmovienm.setBounds(210, 128, 243, 19);
		frame.getContentPane().add(txtmovienm);
		
		txtmain = new JTextField();
		txtmain.setColumns(10);
		txtmain.setBounds(207, 335, 246, 19);
		frame.getContentPane().add(txtmain);
		
		txt2ndmain = new JTextField();
		txt2ndmain.setColumns(10);
		txt2ndmain.setBounds(210, 384, 243, 19);
		frame.getContentPane().add(txt2ndmain);
		
		txt3rdmain = new JTextField();
		txt3rdmain.setColumns(10);
		txt3rdmain.setBounds(210, 433, 243, 19);
		frame.getContentPane().add(txt3rdmain);
		
		JLabel lblgenre = new JLabel("*Genre :");
		lblgenre.setBounds(27, 175, 98, 15);
		frame.getContentPane().add(lblgenre);
		
		JComboBox<String> genre = new JComboBox<String>();
		genre.setBounds(210, 170, 243, 24);
		frame.getContentPane().add(genre);
		genre.addItem("Action");
		genre.addItem("Adventure");
		genre.addItem("Horror");
		genre.addItem("Crime");
		genre.addItem("Romance");
		genre.addItem("Science-Fiction");
		genre.addItem("Documentry");
		
		JLabel lblndGenre = new JLabel("2nd Genre :");
		lblndGenre.setBounds(27, 228, 98, 15);
		frame.getContentPane().add(lblndGenre);
		
		JComboBox genre2 = new JComboBox();
		genre2.setBounds(210, 223, 243, 24);
		frame.getContentPane().add(genre2);
		genre2.addItem("None");
		genre2.addItem("Action");
		genre2.addItem("Adventure");
		genre2.addItem("Horror");
		genre2.addItem("Crime");
		genre2.addItem("Romance");
		genre2.addItem("Science-Fiction");
		genre2.addItem("Documentry");
		
		JRadioButton rdbtnEnglish = new JRadioButton("English",true);
		rdbtnEnglish.setBounds(210, 283, 75, 23);
		frame.getContentPane().add(rdbtnEnglish);
		
		JRadioButton rdbtnIndian = new JRadioButton("Indian");
		rdbtnIndian.setBounds(309, 283, 68, 23);
		frame.getContentPane().add(rdbtnIndian);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnIndian);
		bg.add(rdbtnEnglish);
		
		JLabel lblOrigin = new JLabel("*Origin :");
		lblOrigin.setBounds(27, 287, 60, 15);
		frame.getContentPane().add(lblOrigin);
		
		JLabel lblcertificate = new JLabel("*Certificate :");
		lblcertificate.setBounds(27, 489, 98, 15);
		frame.getContentPane().add(lblcertificate);
		
		JComboBox<String> cert = new JComboBox<String>();
		cert.setBounds(210, 484, 243, 24);
		frame.getContentPane().add(cert);
		cert.addItem("U");
		cert.addItem("U/A");
		cert.addItem("A");
		
		JLabel lblTotalStock = new JLabel("*Total Stock :");
		lblTotalStock.setBounds(27, 543, 114, 15);
		frame.getContentPane().add(lblTotalStock);
		
		txttotalstock = new JTextField();
		txttotalstock.setColumns(10);
		txttotalstock.setBounds(207, 541, 246, 19);
		frame.getContentPane().add(txttotalstock);
		
		txtrentalprice = new JTextField();
		txtrentalprice.setColumns(10);
		txtrentalprice.setBounds(207, 588, 246, 19);
		frame.getContentPane().add(txtrentalprice);
		
		JLabel lblRentalPrice = new JLabel("*Rental Price :");
		lblRentalPrice.setBounds(27, 590, 114, 15);
		frame.getContentPane().add(lblRentalPrice);
		
		txtaddinfo = new JTextField();
		txtaddinfo.setColumns(10);
		txtaddinfo.setBounds(207, 640, 246, 55);
		frame.getContentPane().add(txtaddinfo);
		
		JLabel lblAdditionalInfo = new JLabel("Additional info. :");
		lblAdditionalInfo.setBounds(27, 642, 114, 15);
		frame.getContentPane().add(lblAdditionalInfo);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtdvd_id.getText().trim().length()==0 ||
				txtmain.getText().trim().length()==0 ||
				txt2ndmain.getText().trim().length()==0 ||
				txttotalstock.getText().trim().length()==0 ||
				txtmovienm.getText().trim().length()==0 ||
				txtrentalprice.getText().trim().length()==0	)
				{
					
					JOptionPane.showMessageDialog(null, "* FIELDS ARE COMPULSORY !","ERROR !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				rdbtnEnglish.setActionCommand("English");
				rdbtnIndian.setActionCommand("Indian");
				try
				{
					@SuppressWarnings("unused")
					int i = Integer.parseInt(txttotalstock.getText());
				try
				{
					@SuppressWarnings("unused")
					double d = Double.parseDouble(txtrentalprice.getText());
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
					String sql ="select * from dvd where dvd_id = ? ";
					PreparedStatement ps =con.prepareStatement(sql);
					ps.setString(1, txtdvd_id.getText().toString());
					ResultSet rs = ps.executeQuery();
					if(!rs.next())
					{
						String sql1 ="insert into dvd values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,'No')";
						PreparedStatement ps1 =con.prepareStatement(sql1);
						ps1.setString(1, txtdvd_id.getText().toString());
						ps1.setString(2, txtmovienm.getText().toString());
						ps1.setString(3, (String)genre.getSelectedItem());
						ps1.setString(4, (String)genre2.getSelectedItem());
						ps1.setString(5, txtmain.getText());
						ps1.setString(6, txt2ndmain.getText());
						ps1.setString(7, txt3rdmain.getText());
						ps1.setString(8, bg.getSelection().getActionCommand());
						ps1.setString(9, (String)cert.getSelectedItem());
						int ts = Integer.parseInt(txttotalstock.getText());
						ps1.setInt(10, ts);
						ps1.setString(11, txtaddinfo.getText());
						double rp = Double.parseDouble(txtrentalprice.getText());
						ps1.setDouble(12, rp);
						ps1.setString(13, "0");
						ps1.setString(14, "0");
						ps1.executeUpdate();
						JOptionPane.showMessageDialog(null, "DVD IS IMPORTED !","DONE !",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"DVD IS ALREADY PRESENT !","Error !",JOptionPane.ERROR_MESSAGE);
					}
					txtdvd_id.setText(null);
					txtmovienm.setText(null);
					txttotalstock.setText(null);
					txtrentalprice.setText(null);
					txtaddinfo.setText(null);
					txtmain.setText(null);
					txt2ndmain.setText(null);
					txt3rdmain.setText(null);
					
				} catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				catch(NumberFormatException a)
				{
					JOptionPane.showMessageDialog(null,"PLEASE ENTER VALID RENTAL PRICE !","Error !",JOptionPane.ERROR_MESSAGE);
					txtrentalprice.setText(null);
				}
				}
				catch(NumberFormatException a)
				{
					JOptionPane.showMessageDialog(null,"PLEASE ENTER VALID TOTAL STOCK !","Error !",JOptionPane.ERROR_MESSAGE);
					txttotalstock.setText(null);
				}
			}
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(515, 122, 114, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtdvd_id.setText(null);
				txtmovienm.setText(null);
				txttotalstock.setText(null);
				txtrentalprice.setText(null);
				txtaddinfo.setText(null);
				txtmain.setText(null);
				txt2ndmain.setText(null);
				txt3rdmain.setText(null);
			}
		});
		button_1.setBounds(515, 363, 114, 25);
		frame.getContentPane().add(button_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		btnCancel.setBounds(515, 585, 114, 25);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblDvdDetails = new JLabel("DVD Details -");
		lblDvdDetails.setFont(new Font("Dialog", Font.BOLD, 23));
		lblDvdDetails.setBounds(27, 25, 243, 33);
		frame.getContentPane().add(lblDvdDetails);
		
		JLabel label = new JLabel("logo");
		label.setIcon(new ImageIcon(import_video.class.getResource("/image/PicsArt_09-15-02.34.27.jpg")));
		label.setBounds(511, 192, 134, 112);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("logo");
		label_1.setIcon(new ImageIcon(import_video.class.getResource("/image/PicsArt_09-15-02.04.45.jpg")));
		label_1.setBounds(539, 456, 72, 54);
		frame.getContentPane().add(label_1);
			
	}
}
