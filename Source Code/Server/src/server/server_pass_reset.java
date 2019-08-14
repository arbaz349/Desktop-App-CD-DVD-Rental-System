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

public class server_pass_reset {

	public server_pass_reset(Socket s) throws IOException, SQLException, ClassNotFoundException{
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		String strun,strpass,strem,strque,strans;
		strun = dis.readUTF();
		strpass = dis.readUTF();
		strem = dis.readUTF();
		strans = dis.readUTF();
		strque = dis.readUTF();
		String sql = "select * from login where username ='"+strun+"' and email = '"+strem+"' and que = '"+strque+"' and ans =  '"+strans+"'";
			PreparedStatement ps =con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String sql1 = "update login set password = ? where username = ?; ";
				PreparedStatement ps1 =con.prepareStatement(sql1);
				ps1.setString(1, strpass);
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
