package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class server_update_customer {

	public server_update_customer(Socket s) throws IOException, SQLException, ClassNotFoundException {
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
	
			String sql1 ="update customer set firstname = ? , lastname= ? , address = ?, mobno = ? , dob = ? where cust_id = ?";
			PreparedStatement ps1 =con.prepareStatement(sql1);
			ps1.setString(1, strfirstnm);
			ps1.setString(2, strlastnm);
			ps1.setString(3, strem);
			ps1.setString(4, strphone);
			ps1.setString(5, strdate);
			ps1.setString(6, strun);
			ps1.executeUpdate();
			dos.writeUTF("yes");
		
		
	}

}
