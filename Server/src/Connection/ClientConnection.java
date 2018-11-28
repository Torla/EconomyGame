package Connection;

import java.io.IOException;
import java.io.InputStream;

import java.net.Socket;

public class ClientConnection {
	private final Socket socket;

	public ClientConnection(Socket socket) {
		this.socket = socket;
	}

	public InputStream getInputStream(){
		try {
			return socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
