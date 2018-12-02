package Comunication;

import Connection.ServerConnection;
import Protocol.Protocol;

import java.io.InputStream;


public class Comunication {


	public static InputStream getWorldData(){
		ServerConnection serverConnection = new ServerConnection();
		serverConnection.connect();
		serverConnection.send(Protocol.worldMessage);
		return serverConnection.getInputStream();
	}
}
