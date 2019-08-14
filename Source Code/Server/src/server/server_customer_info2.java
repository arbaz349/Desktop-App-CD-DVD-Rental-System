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

public class server_customer_info2 {

	public server_customer_info2(Socket s) throws SQLException, IOException, ClassNotFoundException {
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_sys","root","vector");
		
		String strun;
		strun = dis.readUTF();
			PreparedStatement ps =con.prepareStatement("select * from customer where cust_id = ? ");
			ps.setString(1,strun);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				dos.writeUTF("yes");
				dos.writeUTF(rs.getString("firstname"));
				dos.writeUTF(rs.getString("lastname"));
				dos.writeUTF(rs.getString("address"));
				dos.writeUTF(rs.getString("mobno"));
				dos.writeUTF(rs.getString("dob"));

			}
			else
			{
				dos.writeUTF("no");
			}
	}

}
