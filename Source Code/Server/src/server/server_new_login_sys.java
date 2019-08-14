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

public class server_new_login_sys {

	public server_new_login_sys(Socket s) throws IOException, SQLException, ClassNotFoundException {
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		String strun,strpass,strem,strque,strans;
		strun = dis.readUTF();
		String sql = "select * from login where username ='"+strun+"'";
		try {
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
			strpass = dis.readUTF();
			strem = dis.readUTF();
			strans = dis.readUTF();
			strque = dis.readUTF();
			String sql1 ="insert into login values (?,?,?,?,?)";
			PreparedStatement ps1 =con.prepareStatement(sql1);
			ps1.setString(1, strun);
			ps1.setString(2, strpass);
			ps1.setString(3, strem);
			ps1.setString(4, strque);
			ps1.setString(5, strans);
			ps1.executeUpdate();
		}

	}

