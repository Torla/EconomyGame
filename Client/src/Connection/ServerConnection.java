package Connection;

import java.io.*;
import java.net.Socket;

public class ServerConnection {
	public final static String SERVER_IP = "127.0.0.1";
	public final static int SERVER_PORT = 10123;
	private Socket socket;

	public ServerConnection() {
		try {
			socket = new Socket(SERVER_IP,SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(InputStream x){
		BufferedInputStream bis = new BufferedInputStream(x);
		byte [] mybytearray  = new byte [1];
		try {
			while (bis.available()!=0) {
				bis.read(mybytearray,0, mybytearray.length);
				socket.getOutputStream().write(mybytearray);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
