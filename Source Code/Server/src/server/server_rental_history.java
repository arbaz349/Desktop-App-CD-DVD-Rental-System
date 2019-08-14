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

public class server_rental_history {

	public server_rental_history(Socket s) throws IOException, SQLException, ClassNotFoundException {
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		
		String str;
		str = dis.readUTF();
	try {
		PreparedStatement ps =con.prepareStatement("select * from hisrental where cust_id = ? ");
		ps.setString(1, str);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			dos.writeUTF("yes");
			dos.writeUTF(rs.getString("dvd_id"));
			dos.writeUTF(rs.getString("renteddate"));
			dos.writeUTF(rs.getString("returndate"));
			dos.writeUTF(rs.getString("amountpaid"));
		}
			dos.writeUTF("$no$");
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}

}
