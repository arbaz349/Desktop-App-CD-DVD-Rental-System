package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class r_r_overdue {

	private JFrame frmRentreturnoverdueDvd;
	private JTextField txtdvdid;
	private JTextField txtdvdnm;
	private JTextField txtdtrent;
	private JDateChooser txtdtdue;
	private JTextField txtamt;
	private JTextField txtamt1;
	private JTextField txtdvdidr;
	private JTextField txtdtrentr;
	private JTextField txtamt3;
	private JTextField txtdtduer;
	private JTextField txtret;
	private JTextField txtamt2;
	private JTextField txtrate;
	private JTable table;
	private JTable table1;

	/**
	 * Launch the application.
	 * @param s 
	 */
	public static void main(String customer_id, Socket s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					r_r_overdue window = new r_r_overdue(customer_id,s);
					window.frmRentreturnoverdueDvd.setVisible(true);
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
	public r_r_overdue(String customer_id, Socket s) {
		initialize(customer_id,s);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param customer_id 
	 * @param s 
	 */
	private void initialize(String customer_id, Socket s) {
		frmRentreturnoverdueDvd = new JFrame();
		frmRentreturnoverdueDvd.setBounds(100, 100, 619, 551);
		frmRentreturnoverdueDvd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmRentreturnoverdueDvd.setTitle("Rent/Return/Rented DVD/Overdue DVD");
		frmRentreturnoverdueDvd.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 619, 521);
		frmRentreturnoverdueDvd.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Rent", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("*DVD ID -");
		label.setBounds(23, 150, 78, 15);
		panel.add(label);
		
		txtdvdid = new JTextField();
		txtdvdid.setColumns(10);
		txtdvdid.setBounds(206, 148, 243, 19);
		panel.add(txtdvdid);
		
		JLabel label_1 = new JLabel("*DVD Name -");
		label_1.setBounds(23, 196, 98, 15);
		panel.add(label_1);
		
		txtdvdnm = new JTextField();
		txtdvdnm.setEditable(false);
		txtdvdnm.setColumns(10);
		txtdvdnm.setBounds(206, 192, 243, 19);
		panel.add(txtdvdnm);
		
		JLabel lbldateRented = new JLabel("*Date Rented -");
		lbldateRented.setBounds(23, 291, 114, 15);
		panel.add(lbldateRented);
		
		txtdtrent = new JTextField();
		txtdtrent.setEditable(false);
		txtdtrent.setColumns(10);
		txtdtrent.setBounds(206, 287, 243, 19);
		panel.add(txtdtrent);
		
		JLabel lbldateDue = new JLabel("*Date Due -");
		lbldateDue.setBounds(23, 343, 98, 15);
		panel.add(lbldateDue);
		
		txtdtdue = new JDateChooser();
		txtdtdue.setBounds(206, 339, 243, 19);
		panel.add(txtdtdue);
		txtdtdue.setDateFormatString("yyyy-MM-dd");
		
		JLabel lblamountToBe = new JLabel("*Amount To Be Paid -");
		lblamountToBe.setBounds(23, 391, 149, 15);
		panel.add(lblamountToBe);
		
		txtamt = new JTextField();
		txtamt.setEditable(false);
		txtamt.setColumns(10);
		txtamt.setBounds(206, 387, 243, 19);
		panel.add(txtamt);
	
		
		JButton button = new JButton("Rent Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtdvdid.getText().trim().length()==0 || txtdvdnm.getText().trim().length()==0 || txtdtrent.getText().trim().length()==0 || txtamt1.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER DVD ID AND PRESS VERIFY ID BUTTON !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else if(((JTextField)txtdtdue.getDateEditor().getUiComponent()).getText().trim().length()==0 || txtamt.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER DUE DATE AND PRESS CALCULATE BUTTON !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				try
				{
					@SuppressWarnings("unused")
					LocalDate dt1 =LocalDate.parse(((JTextField)txtdtdue.getDateEditor().getUiComponent()).getText());
					JFrame frame1 = new JFrame("RENT OUT");
					if(JOptionPane.showConfirmDialog(frame1,"Confirm if you want to Rent Out DVD and Pay "
							+ txtamt.getText()+" Rs ?","Rent out DVD",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
					{
						DataOutputStream dos;
						try {
							dos = new DataOutputStream(s.getOutputStream());
							dos.writeUTF("rent");
							dos.writeUTF(customer_id);
							dos.writeUTF(txtdvdid.getText().toString());
							dos.writeUTF(txtdtrent.getText());
							dos.writeUTF(((JTextField)txtdtdue.getDateEditor().getUiComponent()).getText());
							dos.writeUTF(txtamt.getText());
							frmRentreturnoverdueDvd.setVisible(false);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					}
				}
				catch(DateTimeParseException a)
				{
					JOptionPane.showMessageDialog(null,"PLEASE ENTER VALID DUE DATE !","Error !",JOptionPane.ERROR_MESSAGE);
					txtdtdue.setDate(null);
				}
			}
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(23, 447, 114, 25);
		panel.add(button);
		
		JButton button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtdvdid.setText(null);
				txtdvdnm.setText(null);
				txtdtrent.setText(null);
				txtdtdue.setDate(null);
				txtamt.setText(null);
				txtamt1.setText(null);
			}
		});
		button_1.setBounds(260, 447, 114, 25);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmRentreturnoverdueDvd.setVisible(false);
			}
		});
		button_2.setBounds(472, 447, 114, 25);
		panel.add(button_2);
		
		JButton btnVerify = new JButton(" Verify ID");
		btnVerify.setForeground(Color.BLUE);
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtdvdid.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "DVD ID CAN NOT BE EMPTY !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					DataInputStream dis;
					DataOutputStream dos;
					try {
						dis = new DataInputStream(s.getInputStream());
						dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("verify_dvd");
						dos.writeUTF(txtdvdid.getText());
						String str;
						str = dis.readUTF();
						
						if(str.equals("yes"))
						{
							txtdvdnm.setText(dis.readUTF());
							txtamt1.setText(dis.readUTF());
							txtdtrent.setText(new Date(System.currentTimeMillis()).toString());	
						}
						else
						{
							JOptionPane.showMessageDialog(null, "DVD NOT FOUND !","Error !",JOptionPane.ERROR_MESSAGE);
							txtdvdid.setText(null);
							txtdvdnm.setText(null);
							txtdtrent.setText(null);
							txtdtdue.setDate(null);
							txtamt.setText(null);
						}
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					}
			}
		});
		btnVerify.setBounds(472, 147, 114, 25);
		panel.add(btnVerify);
		
		JButton btnCalculateAmount = new JButton("Calculate");
		btnCalculateAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtdvdid.getText().trim().length()==0 || txtdvdnm.getText().trim().length()==0 || txtdtrent.getText().trim().length()==0 || txtamt1.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER DVD ID AND PRESS VERIFY ID BUTTON !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else if(((JTextField)txtdtdue.getDateEditor().getUiComponent()).getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "DUE DATE CAN NOT BE EMPTY !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				try
				{
				LocalDate dt1 =LocalDate.parse(txtdtrent.getText());
				LocalDate dt2 =LocalDate.parse(((JTextField)txtdtdue.getDateEditor().getUiComponent()).getText());
				long day = ChronoUnit.DAYS.between(dt1, dt2);
				double amt = day*Double.parseDouble(txtamt1.getText());
				txtamt.setText(String.valueOf(amt));
				}
				catch(DateTimeParseException a)
				{
					JOptionPane.showMessageDialog(null,"PLEASE ENTER VALID DUE DATE !","Error !",JOptionPane.ERROR_MESSAGE);
					txtdtdue.setDate(null);
				}
				}
			}
		});
		btnCalculateAmount.setBounds(472, 386, 114, 25);
		panel.add(btnCalculateAmount);
		
		JLabel lblrentalPrice = new JLabel("*Rental Price -");
		lblrentalPrice.setBounds(23, 246, 149, 15);
		panel.add(lblrentalPrice);
		
		txtamt1 = new JTextField();
		txtamt1.setEditable(false);
		txtamt1.setColumns(10);
		txtamt1.setBounds(206, 244, 243, 19);
		panel.add(txtamt1);
		
		JLabel lblLogo_1 = new JLabel("logo");
		lblLogo_1.setIcon(new ImageIcon(r_r_overdue.class.getResource("/image/PicsArt_09-15-02.45.54.jpg")));
		lblLogo_1.setBounds(135, 12, 184, 112);
		panel.add(lblLogo_1);
		
		JLabel label_4 = new JLabel("logo");
		label_4.setIcon(new ImageIcon(r_r_overdue.class.getResource("/image/PicsArt_09-15-02.34.27.jpg")));
		label_4.setBounds(318, 12, 134, 112);
		panel.add(label_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Return", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_2 = new JLabel("*DVD ID -");
		label_2.setBounds(12, 72, 78, 15);
		panel_1.add(label_2);
		
		txtdvdidr = new JTextField();
		txtdvdidr.setColumns(10);
		txtdvdidr.setBounds(207, 68, 243, 19);
		panel_1.add(txtdvdidr);
		
		JLabel lblrentedDate = new JLabel("*Rented Date -");
		lblrentedDate.setBounds(12, 116, 114, 15);
		panel_1.add(lblrentedDate);
		
		txtdtrentr = new JTextField();
		txtdtrentr.setEditable(false);
		txtdtrentr.setColumns(10);
		txtdtrentr.setBounds(207, 112, 243, 19);
		panel_1.add(txtdtrentr);
		
		JButton button_3 = new JButton(" Verify ID");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtdvdidr.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "DVD ID CAN NOT BE EMPTY !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					DataInputStream dis;
					DataOutputStream dos;
				try {
					dis = new DataInputStream(s.getInputStream());
					dos = new DataOutputStream(s.getOutputStream());
					dos.writeUTF("verify_dvd_rented");
					dos.writeUTF(txtdvdidr.getText());
					dos.writeUTF(customer_id);
					String str;
					str = dis.readUTF();
					if(str.equals("yes"))
					{
						txtdtrentr.setText(dis.readUTF());
						txtdtduer.setText(dis.readUTF());
						txtamt2.setText(dis.readUTF());
						txtret.setText(new Date(System.currentTimeMillis()).toString());					}
					else
					{
						JOptionPane.showMessageDialog(null, "DVD NOT FOUND !","Error !",JOptionPane.ERROR_MESSAGE);
						txtdvdidr.setText(null);
						txtdtrentr.setText(null);
						txtdtduer.setText(null);
						txtret.setText(null);
						txtamt2.setText(null);
						txtamt3.setText(null);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				}
			}
		});
		button_3.setForeground(Color.BLUE);
		button_3.setBounds(473, 67, 114, 25);
		panel_1.add(button_3);
		
		JLabel lblextraAmountTo = new JLabel("*Extra Amount To Be Paid -");
		lblextraAmountTo.setBounds(12, 322, 190, 15);
		panel_1.add(lblextraAmountTo);
		
		txtamt3 = new JTextField();
		txtamt3.setEditable(false);
		txtamt3.setColumns(10);
		txtamt3.setBounds(207, 318, 243, 19);
		panel_1.add(txtamt3);
		
		JButton button_4 = new JButton("Calculate");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double amt = 0;
				if(txtdvdidr.getText().trim().length()==0 || txtdtrentr.getText().trim().length()==0 || txtdtduer.getText().trim().length()==0 || txtret.getText().trim().length()==0 || txtamt2.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER DVD ID AND PRESS VERIFY ID BUTTON !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				LocalDate dt1 =LocalDate.parse(txtdtrentr.getText());
				LocalDate dt2 =LocalDate.parse(txtdtduer.getText());
				LocalDate dt3 =LocalDate.parse(txtret.getText());
				long day = ChronoUnit.DAYS.between(dt1, dt2);
				if(day!=0)
				amt = Double.parseDouble(txtamt2.getText())/day;
				else
					day=1;
				long day1 = ChronoUnit.DAYS.between(dt2, dt3);
				double amt1 = day1*amt*1.5;
				if(amt1<0)
					amt1=0.0;
				txtamt3.setText(String.valueOf(amt1));
			}
		}
		});
		button_4.setBounds(473, 317, 114, 25);
		panel_1.add(button_4);
		
		JLabel lbldueDate = new JLabel("*Due Date -");
		lbldueDate.setBounds(12, 164, 114, 15);
		panel_1.add(lbldueDate);
		
		txtdtduer = new JTextField();
		txtdtduer.setEditable(false);
		txtdtduer.setColumns(10);
		txtdtduer.setBounds(207, 160, 243, 19);
		panel_1.add(txtdtduer);
		
		JButton button_5 = new JButton("Return");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtdvdidr.getText().trim().length()==0 || txtret.getText().trim().length()==0 || txtdtrentr.getText().trim().length()==0 || txtdtduer.getText().trim().length()==0 || txtamt2.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER DVD ID AND PRESS VERIFY ID BUTTON !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else if(txtamt3.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "PRESS CALCULATE BUTTON !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else if(txtrate.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER RATING !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				try
				{
					@SuppressWarnings("unused")
					int i = Integer.parseInt(txtrate.getText());
					JFrame frame1 = new JFrame("RETURN");
					if(JOptionPane.showConfirmDialog(frame1,"Confirm if you want to Return Out DVD and Pay Extra "
							+ txtamt3.getText()+" Rs ?","Rent out DVD",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
					{
						DataInputStream dis;
						DataOutputStream dos;
					try {
						dis = new DataInputStream(s.getInputStream());
						dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("return");
						dos.writeUTF(customer_id);
						dos.writeUTF(txtdvdidr.getText().toString());
						dos.writeUTF(txtdtrentr.getText());
						dos.writeUTF(txtret.getText());
						double am = Double.parseDouble(txtamt2.getText());
						double am1 = Double.parseDouble(txtamt3.getText());
						dos.writeUTF(String.valueOf(am1+am));
					String str;
					str = dis.readUTF();
					
					if(str.equals("yes"))
					{
						int s=Integer.parseInt(dis.readUTF());
						double st =(Double.parseDouble(dis.readUTF())*s+Double.parseDouble(txtrate.getText()))/(s+1);
						dos.writeUTF(String.valueOf(s+1));
						dos.writeUTF(String.valueOf(st));
					}
					} catch (IOException e) {
						e.printStackTrace();
					}
					frmRentreturnoverdueDvd.setVisible(false);
					}
				}
				catch(NumberFormatException a)
				{
					JOptionPane.showMessageDialog(null,"PLEASE ENTER VALID RATING !","Error !",JOptionPane.ERROR_MESSAGE);
					txtrate.setText(null);
				}
			}
			}
		});
		button_5.setForeground(Color.RED);
		button_5.setBounds(26, 430, 114, 25);
		panel_1.add(button_5);
		
		JButton button_6 = new JButton("Reset");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtdvdidr.setText(null);
				txtdtrentr.setText(null);
				txtdtduer.setText(null);
				txtret.setText(null);
				txtamt2.setText(null);
				txtamt3.setText(null);
			}
		});
		button_6.setBounds(263, 430, 114, 25);
		panel_1.add(button_6);
		
		JButton button_7 = new JButton("Cancel");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmRentreturnoverdueDvd.setVisible(false);
			}
		});
		button_7.setBounds(475, 430, 114, 25);
		panel_1.add(button_7);
		
		JLabel label_3 = new JLabel("*Return Date -");
		label_3.setBounds(12, 216, 114, 15);
		panel_1.add(label_3);
		
		txtret = new JTextField();
		txtret.setEditable(false);
		txtret.setColumns(10);
		txtret.setBounds(207, 212, 243, 19);
		panel_1.add(txtret);
		
		JLabel lblpaidAmount = new JLabel("*Paid Amount -");
		lblpaidAmount.setBounds(12, 264, 114, 15);
		panel_1.add(lblpaidAmount);
		
		txtamt2 = new JTextField();
		txtamt2.setEditable(false);
		txtamt2.setColumns(10);
		txtamt2.setBounds(207, 260, 243, 19);
		panel_1.add(txtamt2);
		
		JLabel lbldvdRating = new JLabel("*DVD Rating -");
		lbldvdRating.setBounds(12, 371, 114, 15);
		panel_1.add(lbldvdRating);
		
		txtrate = new JTextField();
		txtrate.setColumns(10);
		txtrate.setBounds(207, 367, 243, 19);
		panel_1.add(txtrate);
		
		JLabel label_5 = new JLabel("logo");
		label_5.setIcon(new ImageIcon(r_r_overdue.class.getResource("/image/PicsArt_09-14-04.36.13.jpg")));
		label_5.setBounds(495, 116, 75, 70);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("logo");
		label_6.setIcon(new ImageIcon(r_r_overdue.class.getResource("/image/PicsArt_09-15-02.04.45.jpg")));
		label_6.setBounds(498, 244, 72, 54);
		panel_1.add(label_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Rented", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 81, 614, 403);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
String[] col= {"DVD ID","RENTED DATE","DUE DATE","AMOUNT PAID"};
		
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(col);
		table.setModel(mod);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		scrollPane.setViewportView(table);
		
		JLabel lblLogo = new JLabel("logo");
		lblLogo.setIcon(new ImageIcon(r_r_overdue.class.getResource("/image/PicsArt_09-15-01.27.30.jpg")));
		lblLogo.setBounds(211, 19, 218, 50);
		panel_2.add(lblLogo);
		DataInputStream dis;
		DataOutputStream dos;
		try {
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("rented_dvd");
			dos.writeUTF(customer_id);
			
			while(!dis.readUTF().equals("$no$"))
			{
				mod.addRow(new Object[]{dis.readUTF(),dis.readUTF(),dis.readUTF(),dis.readUTF()});
			}
			} catch (IOException e) {
			
				e.printStackTrace();
			}

		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Overdue", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(0, 81, 614, 403);
		panel_3.add(scrollPane1);
		
		table1 = new JTable();
		scrollPane1.setViewportView(table1);
String[] col1= {"DVD ID","RENTED DATE","DUE DATE","AMOUNT PAID"};
		
		DefaultTableModel mod1 = new DefaultTableModel();
		mod1.setColumnIdentifiers(col1);
		table1.setModel(mod1);
		JTableHeader header1 = table1.getTableHeader();
		header1.setBackground(Color.yellow);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table1.getColumnModel().getColumn(0).setPreferredWidth(200);
		table1.getColumnModel().getColumn(1).setPreferredWidth(200);
		table1.getColumnModel().getColumn(2).setPreferredWidth(200);
		table1.getColumnModel().getColumn(3).setPreferredWidth(200);
		scrollPane1.setViewportView(table1);
		
		JLabel lblLogo1 = new JLabel("logo");
		lblLogo1.setIcon(new ImageIcon(r_r_overdue.class.getResource("/image/PicsArt_09-15-01.27.30.jpg")));
		lblLogo1.setBounds(211, 19, 218, 50);
		panel_3.add(lblLogo1);
		try {
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("overdue_dvd");
			dos.writeUTF(customer_id);
			
			while(!dis.readUTF().equals("$no$"))
			{
				mod1.addRow(new Object[]{dis.readUTF(),dis.readUTF(),dis.readUTF(),dis.readUTF()});
			}
			} catch (IOException e) {
			
				e.printStackTrace();
			}

	}
}
