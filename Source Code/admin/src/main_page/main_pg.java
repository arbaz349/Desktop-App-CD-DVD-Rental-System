package main_page;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import administrator.available_videos;
import administrator.change_price;
import administrator.customer_search;
import administrator.delete_customer;
import administrator.delete_video;
import administrator.import_video;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class main_pg {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_pg window = new main_pg();
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
	public main_pg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 412, 623);
		frame.setTitle("ADMINISTRATION");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btncs = new JButton("Customer Search");
		btncs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customer_search.main(null);
				
			}
		});
		btncs.setBounds(76, 170, 249, 25);
		frame.getContentPane().add(btncs);
		
		JLabel lblAdministration = new JLabel("Administration");
		lblAdministration.setFont(new Font("Dialog", Font.BOLD, 25));
		lblAdministration.setBounds(86, 112, 220, 25);
		frame.getContentPane().add(lblAdministration);
		
		JButton btndeletecus = new JButton("Delete Customer");
		btndeletecus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete_customer.main(null);
				
			}
		});
		btndeletecus.setBounds(76, 239, 249, 25);
		frame.getContentPane().add(btndeletecus);
		
		JButton button = new JButton("Import Video");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			import_video.main(null);	
			}
		});
		button.setBounds(76, 363, 249, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Remove Video");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete_video.main(null);
			}
		});
		button_1.setBounds(76, 421, 249, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Change Price");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				change_price.main(null);
			}
		});
		button_2.setBounds(76, 483, 249, 25);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Log Out");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame1 = new JFrame("Log Out");
				if(JOptionPane.showConfirmDialog(frame1,"Confirm if you want to Log Out ?","Login System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
		{
			frame.setVisible(false);
		}
			}
		});
		button_3.setForeground(Color.RED);
		button_3.setBounds(273, 549, 105, 25);
		frame.getContentPane().add(button_3);
		
		JButton btnAvailabelVideos = new JButton("Available Videos");
		btnAvailabelVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				available_videos.main(null);
			}
		});
		btnAvailabelVideos.setBounds(76, 299, 249, 25);
		frame.getContentPane().add(btnAvailabelVideos);
		
		JLabel lblNewLabel = new JLabel("Admin");
		lblNewLabel.setIcon(new ImageIcon(main_pg.class.getResource("/image/PicsArt_09-14-04.36.13.jpg")));
		lblNewLabel.setBounds(150, 11, 75, 70);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblLogo = new JLabel("logo");
		lblLogo.setIcon(new ImageIcon(main_pg.class.getResource("/image/PicsArt_09-15-02.04.45.jpg")));
		lblLogo.setBounds(37, 520, 72, 54);
		frame.getContentPane().add(lblLogo);
	}
}
