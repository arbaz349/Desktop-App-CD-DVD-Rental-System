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

public class server_verify_dvd_rented {

	public server_verify_dvd_rented(Socket s) throws IOException, ClassNotFoundException, SQLException {
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		
		String str,strun;
		str = dis.readUTF();
		strun = dis.readUTF();
		System.out.println("yes");
		try {
			PreparedStatement ps =con.prepareStatement("select * from rental where dvd_id = ? and cust_id = ?");
			ps.setString(1, str);
			ps.setString(2, strun);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				dos.writeUTF("yes");
				dos.writeUTF(rs.getString("renteddate"));
				dos.writeUTF(rs.getString("duedate"));
				dos.writeUTF(rs.getString("paid"));
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
