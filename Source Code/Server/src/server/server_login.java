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

public class server_login {
	server_login(Socket s) throws IOException, SQLException, ClassNotFoundException
	{
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		
		String strun,strpass;
		strun = dis.readUTF();
		strpass = dis.readUTF();
	
	try {
		String sql = "select * from login where username ='"+strun+"' and password = '"+strpass+"'";
		PreparedStatement ps =con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			dos.writeUTF("yes");
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
