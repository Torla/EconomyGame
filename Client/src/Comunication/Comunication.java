package Comunication;

import Connection.ServerConnection;

import java.io.InputStream;

public class Comunication {
	static final String WORLD_REQUEST = "world\n";

	public static InputStream getWorldData(){
		ServerConnection serverConnection = new ServerConnection();
		serverConnection.connect();
		serverConnection.send(WORLD_REQUEST);
		return serverConnection.getInputStream();
	}
}
