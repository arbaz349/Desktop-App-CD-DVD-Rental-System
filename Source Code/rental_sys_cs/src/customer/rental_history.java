package customer;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class rental_history {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 * @param s 
	 */
	public static void main(String customer_id, Socket s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rental_history window = new rental_history(customer_id,s);
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
	public rental_history(String customer_id, Socket s) {
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
		frame.setBounds(100, 100, 622, 432);
		frame.setTitle("RENTAL HISTORY");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 78, 622, 324);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
String[] col= {"DVD ID","RENTED DATE","RETURN DATE","AMOUNT PAID"};
		
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
		
		JLabel label = new JLabel("logo");
		label.setIcon(new ImageIcon(rental_history.class.getResource("/image/PicsArt_09-15-01.27.30.jpg")));
		label.setBounds(211, 14, 218, 50);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("logo");
		label_1.setIcon(new ImageIcon(rental_history.class.getResource("/image/PicsArt_09-15-02.04.45.jpg")));
		label_1.setBounds(47, 12, 72, 54);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("logo");
		label_2.setIcon(new ImageIcon(rental_history.class.getResource("/image/PicsArt_09-15-02.04.45.jpg")));
		label_2.setBounds(505, 12, 72, 54);
		frame.getContentPane().add(label_2);
		DataInputStream dis;
		DataOutputStream dos;
		try {
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("rental_history");
			dos.writeUTF(customer_id);
			
			while(!dis.readUTF().equals("$no$"))
			{
				mod.addRow(new Object[]{dis.readUTF(),dis.readUTF(),dis.readUTF(),dis.readUTF()});
			}
			} catch (IOException e) {
			
				e.printStackTrace();
			}
	
		}
}
