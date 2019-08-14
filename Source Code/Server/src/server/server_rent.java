package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class server_rent {

	public server_rent(Socket s) throws IOException, ClassNotFoundException, SQLException {
		DataInputStream dis = new DataInputStream(s.getInputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		
		String str,strun,strdtrent,strdtdue,stramt;
		str = dis.readUTF();
		strun = dis.readUTF();
		strdtrent = dis.readUTF();
		strdtdue = dis.readUTF();
		stramt = dis.readUTF();
		try {
			PreparedStatement ps =con.prepareStatement("insert into rental values(?,?,?,?,?)");
			ps.setString(1,str);
			ps.setString(2, strun);
			ps.setString(3, strdtrent);
			ps.setString(4, strdtdue);
			ps.setString(5, stramt);
			ps.executeUpdate();
			PreparedStatement ps1 =con.prepareStatement("update dvd set rented = 'Yes' where dvd_id = ?");
			ps1.setString(1, strun);
			ps1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
