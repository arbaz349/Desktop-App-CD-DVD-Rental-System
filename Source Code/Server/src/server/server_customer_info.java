package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class server_customer_info {

	public server_customer_info(Socket s) throws IOException, SQLException, ClassNotFoundException {
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		
		String strun,strfirstnm,strlastnm,strem,strphone,strdate;
		strun = dis.readUTF();
		strfirstnm = dis.readUTF();
		strlastnm = dis.readUTF();
		strem = dis.readUTF();
		strphone = dis.readUTF();
		strdate = dis.readUTF();
			String sql1 ="insert into customer values (?,?,?,?,?,?,'No')";
			PreparedStatement ps1 =con.prepareStatement(sql1);
			ps1.setString(1, strun);
			ps1.setString(2, strfirstnm);
			ps1.setString(3, strlastnm);
			ps1.setString(4, strem);
			ps1.setString(5, strphone);
			ps1.setString(6, strdate);
			ps1.executeUpdate();
			dos.writeUTF("yes");

	}

}
