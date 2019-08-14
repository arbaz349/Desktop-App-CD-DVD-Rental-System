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

public class server_video_search {

	public server_video_search(Socket s) throws IOException, SQLException, ClassNotFoundException {
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		
		String str;
		str = dis.readUTF();
		System.out.println("yes");
	try {
		PreparedStatement ps =con.prepareStatement(str);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			dos.writeUTF("yes");
			dos.writeUTF(rs.getString("dvd_id"));
			dos.writeUTF(rs.getString("name"));
			dos.writeUTF(rs.getString("genre1"));
			dos.writeUTF(rs.getString("genre2"));
			dos.writeUTF(rs.getString("actor1"));
			dos.writeUTF(rs.getString("actor2"));
			dos.writeUTF(rs.getString("actor3"));
			dos.writeUTF(rs.getString("type"));
			dos.writeUTF(rs.getString("certificate"));
			dos.writeUTF(rs.getString("totalstock"));
			dos.writeUTF(rs.getString("price"));
			dos.writeUTF(rs.getString("addinfo"));
			dos.writeUTF(rs.getString("noofreview"));
			dos.writeUTF(rs.getString("totalrating"));
		}
			dos.writeUTF("$no$");
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}

}
