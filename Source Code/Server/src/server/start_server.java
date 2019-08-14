package server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class start_server {
	ServerSocket ss=null;
	Socket s=null;
	ExecutorService pool =null;
	public start_server() {
		this.pool=Executors.newFixedThreadPool(5);
	}

	private void initialize() throws IOException {
				
		ss = new ServerSocket(5500);
		while(true)
		{
		System.out.println("Waiting for connection...");
		s = ss.accept();
		System.out.println("Connection for 1 Client Established...");
		serverthread runnable = new serverthread(s);
		pool.execute(runnable);
		}
		
	}
	public static void main(String[] args) throws IOException {
		new start_server().initialize();
	}
}
class serverthread implements Runnable{

	ServerSocket ss=null;
	Socket s=null;
	DataInputStream dis=null;
	
	public serverthread(Socket s) {
		this.s=s;
		
	}

	@Override
	public void run() {
		try {
			dis = new DataInputStream(s.getInputStream());
			while(true)
			{
		try {
			String str = dis.readUTF();
			if(str.equals("login_sys"))
			{
				new server_login(s);

			}
			if(str.equals("new_login_sys"))
			{
				new server_new_login_sys(s);			
			
			}
			if(str.equals("pass_reset"))
			{
				new server_pass_reset(s);			
			
			}
			if(str.equals("password_change"))
			{
				new server_change_password(s);			
			
			}
			if(str.equals("customer_info"))
			{
				new server_customer_info(s);			
			
			}
			if(str.equals("customer_info2"))
			{
				new server_customer_info2(s);			
			
			}
			if(str.equals("update_customer"))
			{
				new server_update_customer(s);			
			
			}
			if(str.equals("video_search"))
			{
				new server_video_search(s);			
			
			}
			if(str.equals("rental_history"))
			{
				new server_rental_history(s);			
			
			}
			if(str.equals("verify_dvd"))
			{
				new server_verify_dvd(s);			
			
			}
			if(str.equals("verify_dvd_rented"))
			{
				new server_verify_dvd_rented(s);			
			
			}
			if(str.equals("rent"))
			{
				new server_rent(s);			
			
			}
			if(str.equals("return"))
			{
				new server_return(s);			
			
			}
			if(str.equals("rented_dvd"))
			{
				new server_rented_dvd(s);			
			
			}
			if(str.equals("overdue_dvd"))
			{
				new server_overdue_dvd(s);			
			
			}
		}catch (EOFException e) {
				break;
		}
		}
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
