package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class video_search {

	private JFrame frame;
	private JTextField txtdvd;
	private JTable table;

	/**
	 * Launch the application.
	 * @param s 
	 */
	public static void main(String customer_id, Socket s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					video_search window = new video_search(s);
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
	public video_search(Socket s) {
		initialize(s);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param s 
	 */
	private void initialize(Socket s) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 634, 561);
		frame.setTitle("DVD SEARCH");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setTitle("Search DVD");
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Search DVD -");
		label.setFont(new Font("Dialog", Font.BOLD, 23));
		label.setBounds(28, 24, 253, 33);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("*Search By -");
		label_1.setBounds(38, 104, 85, 15);
		frame.getContentPane().add(label_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(141, 99, 217, 24);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("DVD ID");
		comboBox.addItem("DVD NAME");
		comboBox.addItem("ACTOR");
		comboBox.addItem("GENRE");
		comboBox.addItem("TYPE");
		comboBox.addItem("CERTIFICATE");
		comboBox.addItem("PRICE");
		comboBox.addItem("RATING");
		
		JLabel label_2 = new JLabel("*Enter "+comboBox.getSelectedItem()+" -");
		label_2.setBounds(38, 175, 187, 24);
		frame.getContentPane().add(label_2);
		
		JLabel lblSelectBy = new JLabel("*Select -");
		lblSelectBy.setBounds(386, 100, 85, 15);
		frame.getContentPane().add(lblSelectBy);
		
		JComboBox<String> comboBox1 = new JComboBox<String>();
		comboBox1.setBounds(476, 99, 125, 24);
		frame.getContentPane().add(comboBox1);
		comboBox1.setVisible(false);
		lblSelectBy.setVisible(false);
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label_2.setText("*Enter "+comboBox.getSelectedItem()+" -");
				if(comboBox.getSelectedItem().equals("GENRE") || comboBox.getSelectedItem().equals("TYPE") || 
				comboBox.getSelectedItem().equals("CERTIFICATE") || comboBox.getSelectedItem().equals("RATING")|| comboBox.getSelectedItem().equals("PRICE") )
				{
				
				txtdvd.setEditable(false);
				comboBox1.setVisible(true);
				lblSelectBy.setVisible(true);
				}
				else
				{
					txtdvd.setText(null);
					txtdvd.setEditable(true);
					comboBox1.setVisible(false);
					lblSelectBy.setVisible(false);
				}
				if(comboBox.getSelectedItem().equals("GENRE"))
				{
					comboBox1.removeAllItems();
					comboBox1.addItem("Action");
					comboBox1.addItem("Adventure");
					comboBox1.addItem("Horror");
					comboBox1.addItem("Crime");
					comboBox1.addItem("Romance");
					comboBox1.addItem("Science-Fiction");
					comboBox1.addItem("Documentry");
					
				}
				if(comboBox.getSelectedItem().equals("TYPE"))
				{
					comboBox1.removeAllItems();
					comboBox1.addItem("English");
					comboBox1.addItem("Indian");
					
				}
				if(comboBox.getSelectedItem().equals("CERTIFICATE"))
				{
					comboBox1.removeAllItems();
					comboBox1.addItem("U");
					comboBox1.addItem("U/A");
					comboBox1.addItem("A");
					
				}
				if(comboBox.getSelectedItem().equals("RATING"))
				{
					comboBox1.removeAllItems();
					comboBox1.addItem("0-3");
					comboBox1.addItem("3-7");
					comboBox1.addItem("7-10");
					
				}
				if(comboBox.getSelectedItem().equals("PRICE"))
				{
					comboBox1.removeAllItems();
					comboBox1.addItem("<10");
					comboBox1.addItem("10-30");
					comboBox1.addItem("30-70");
					comboBox1.addItem("70-100");
					comboBox1.addItem(">100");
					
				}
			}
		}
		);
		
		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtdvd.setText((String) comboBox1.getSelectedItem());
			}
		}
		);
		
		txtdvd = new JTextField();
		txtdvd.setColumns(10);
		txtdvd.setBounds(223, 181, 284, 19);
		frame.getContentPane().add(txtdvd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 311, 563, 196);
		frame.getContentPane().add(scrollPane);
		
String[] col= {"DVD ID","DVD NAME","GENRE-1","GENRE-2","ACTOR-1","ACTOR-2","ACTOR-3","TYPE","CERTIFICATE","TOTAL STOCK","RENTAL PRICE","ADD.INFO.","NO OF REVIEW","TOTAL RATING"};
		
		DefaultTableModel mod = new DefaultTableModel();
		mod.setColumnIdentifiers(col);
		table = new JTable();
		table.setModel(mod);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(200);
		table.getColumnModel().getColumn(8).setPreferredWidth(200);
		table.getColumnModel().getColumn(9).setPreferredWidth(200);
		table.getColumnModel().getColumn(10).setPreferredWidth(200);
		table.getColumnModel().getColumn(11).setPreferredWidth(200);
		table.getColumnModel().getColumn(12).setPreferredWidth(200);
		table.getColumnModel().getColumn(13).setPreferredWidth(200);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtdvd.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(null, comboBox.getSelectedItem()+" CAN NOT BE EMPTY !","Error !",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				for(int i=mod.getRowCount()-1;i>=0;i--)
					mod.removeRow(i);
				String sid = null;
				if(comboBox.getSelectedItem().equals("DVD ID"))
					sid = "select * from dvd where dvd_id = '"+txtdvd.getText().toString()+"' and rented ='No' ";
				else if(comboBox.getSelectedItem().equals("DVD NAME"))
					sid = "select * from dvd where name = '"+txtdvd.getText().toString()+"' and rented ='No' ";
				else if(comboBox.getSelectedItem().equals("ACTOR"))
					sid = "select * from dvd where rented ='No' and ( actor1 = '"+txtdvd.getText().toString()+"' or actor2 = '"+txtdvd.getText().toString()+"' or actor3 = '"+txtdvd.getText().toString()+"' )";
				else if(comboBox.getSelectedItem().equals("GENRE"))
					sid = "select * from dvd where (genre1 = '"+txtdvd.getText().toString()+"' or genre2 = '"+txtdvd.getText().toString()+"') and rented ='No' ";
				else if(comboBox.getSelectedItem().equals("TYPE"))
					sid = "select * from dvd where type = '"+txtdvd.getText().toString()+"' and rented ='No' ";
				else if(comboBox.getSelectedItem().equals("CERTIFICATE"))
					sid = "select * from dvd where certificate = '"+txtdvd.getText().toString()+"' and rented ='No' ";
				else if(comboBox.getSelectedItem().equals("PRICE"))
					if(comboBox1.getSelectedItem().equals("<10"))
					{
						sid = "select * from dvd where price >= 0 and price < 10 and rented ='No' ";
					}
					else if(comboBox1.getSelectedItem().equals("10-30"))
					{
						sid = "select * from dvd where price >= 10 and price < 30 and rented ='No' ";
					}
					else if(comboBox1.getSelectedItem().equals("30-70"))
					{
						sid = "select * from dvd where price >= 30 and price < 70 and rented ='No' ";
					}
					else if(comboBox1.getSelectedItem().equals("70-100"))
					{
						sid = "select * from dvd where price >= 70 and price < 100 and rented ='No' ";
					}
					else
					{
						sid = "select * from dvd where price >= 100 and price <= 1000 and rented ='No' ";
					}
				else
					if(comboBox1.getSelectedItem().equals("0-3"))
					{
						sid = "select * from dvd where totalrating >= 0 and totalrating < 3 and rented ='No' ";
					}
					else if(comboBox1.getSelectedItem().equals("3-7"))
					{
						sid = "select * from dvd where totalrating >= 3 and totalrating < 7 and rented ='No' ";
					}
					else
					{
						sid = "select * from dvd where totalrating >= 7 and totalrating <= 10 and rented ='No' ";
					}
					DataInputStream dis;
					DataOutputStream dos;
					try {
						dis = new DataInputStream(s.getInputStream());
						dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("video_search");
						dos.writeUTF(sid);
						
				while(!dis.readUTF().equals("$no$"))
				{
					mod.addRow(new Object[]{dis.readUTF(),dis.readUTF(),dis.readUTF(),dis.readUTF(),dis.readUTF(),
							dis.readUTF(),dis.readUTF(),dis.readUTF(),dis.readUTF(),dis.readUTF(),dis.readUTF(),
							dis.readUTF(),dis.readUTF(),dis.readUTF()
					});
				}
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				txtdvd.setText(null);
			}
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(38, 245, 114, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Reset");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtdvd.setText(null);
			}
		});
		button_1.setBounds(275, 245, 114, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		button_2.setBounds(487, 245, 114, 25);
		frame.getContentPane().add(button_2);
		
		JLabel label_3 = new JLabel("logo");
		label_3.setIcon(new ImageIcon(video_search.class.getResource("/image/PicsArt_09-15-01.27.30.jpg")));
		label_3.setBounds(383, 12, 218, 50);
		frame.getContentPane().add(label_3);
		
	}
}
