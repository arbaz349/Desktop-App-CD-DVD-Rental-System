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

public class server_return {

	public server_return(Socket s) throws SQLException, IOException, ClassNotFoundException {
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		
		String str,strun,strdtrent,strret,stramt;
		str = dis.readUTF();
		strun = dis.readUTF();
		strdtrent = dis.readUTF();
		strret = dis.readUTF();
		stramt = dis.readUTF();
		System.out.println("yes");
		
		PreparedStatement ps =con.prepareStatement("insert into hisrental values(?,?,?,?,?)");
		ps.setString(1,str);
		ps.setString(2, strun);
		ps.setString(3, strdtrent);
		ps.setString(4, strret);
		ps.setString(5, stramt);
		ps.executeUpdate();
		PreparedStatement ps1 =con.prepareStatement("delete from rental where cust_id = ? and dvd_id = ? ");
		ps1.setString(1,str);
		ps1.setString(2, strun);
		ps1.executeUpdate();
		PreparedStatement ps2 =con.prepareStatement("update dvd set rented = 'No' where dvd_id = ? ");
		ps2.setString(1, strun);
		ps2.executeUpdate();
		PreparedStatement ps3 =con.prepareStatement("select * from dvd where dvd_id = ? and rented = 'No' ");
		ps3.setString(1, strun);
		ResultSet rs=ps3.executeQuery();
		if(rs.next())
		{
			dos.writeUTF("yes");
			dos.writeUTF(rs.getString("noofreview"));
			dos.writeUTF(rs.getString("totalrating"));
			PreparedStatement ps4 =con.prepareStatement("update dvd set noofreview = ? , totalrating = ? where dvd_id = ? ");
			ps4.setString(1, dis.readUTF());
			ps4.setString(2, dis.readUTF());
			ps4.setString(3, strun);
			ps4.executeUpdate();
		}
		else
		{
			dos.writeUTF("no");
		}
	
	}

}
