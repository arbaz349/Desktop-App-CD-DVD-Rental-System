package login;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.UnsupportedLookAndFeelException;

public class new_main {

	public new_main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		Socket s = new Socket("localhost",5500);
		login_sys.main(s);

	}

}
