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

public class server_change_password {

	public server_change_password(Socket s) throws IOException, ClassNotFoundException, SQLException {
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		
		String strun,strpass,strnewpass;
		strun = dis.readUTF();
		strpass = dis.readUTF();
		strnewpass= dis.readUTF();
		String sql = "select * from login where username ='"+strun+"' and password = '"+strpass+"'";
			PreparedStatement ps =con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String sql1 = "update login set password = ? where username = ?; ";
				PreparedStatement ps1 =con.prepareStatement(sql1);
				ps1.setString(1, strnewpass);
				ps1.setString(2, strun);
				ps1.executeUpdate();
				dos.writeUTF("yes");
			}
			else
			{
				dos.writeUTF("no");	
			}
		
	}

}
