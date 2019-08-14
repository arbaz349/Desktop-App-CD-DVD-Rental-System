package administrator;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class available_videos {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					available_videos window = new available_videos();
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
	public available_videos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 683, 447);
		frame.setTitle("AVAILABLE DVD");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 70, 683, 347);
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
		
		JLabel lblLogo = new JLabel("logo");
		lblLogo.setIcon(new ImageIcon(available_videos.class.getResource("/image/PicsArt_09-15-01.27.30.jpg")));
		lblLogo.setBounds(243, 12, 218, 50);
		frame.getContentPane().add(lblLogo);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
			PreparedStatement ps =con.prepareStatement("select * from dvd where rented ='No' ");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				mod.addRow(new Object[]{rs.getString("dvd_id"),rs.getString("name"),rs.getString("genre1"),rs.getString("genre2"),
				rs.getString("actor1"),rs.getString("actor2"),rs.getString("actor3"),rs.getString("type"),rs.getString("certificate"),
				rs.getString("totalstock"),rs.getString("price"),rs.getString("addinfo"),rs.getString("noofreview"),rs.getString("totalrating")});
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
