package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class server_verify_dvd {

	public server_verify_dvd(Socket s) throws IOException, SQLException, ClassNotFoundException {
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		
		String str;
		str = dis.readUTF();
		System.out.println("yes");
		try {
			PreparedStatement ps =con.prepareStatement("select * from dvd where dvd_id = ? and rented = 'No' ");
			ps.setString(1, str);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				dos.writeUTF("yes");
				dos.writeUTF(rs.getString("name"));
				dos.writeUTF(rs.getString("price"));
			}
			else
			{
				dos.writeUTF("no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
