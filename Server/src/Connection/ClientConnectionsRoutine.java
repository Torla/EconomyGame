package Connection;

import Service.ClientCare;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ClientConnectionsRoutine implements Runnable{
	public final static int SOCKET_PORT = 10123;
	ServerSocket listenSock = null;
	private HashSet<ClientConnection> clientConnections = new HashSet<>();

	public ClientConnectionsRoutine() {
		try {
			listenSock = new ServerSocket(SOCKET_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				ClientConnection con = new ClientConnection(listenSock.accept());
				clientConnections.add(con);
				new Thread(new ClientCare(con)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Set<ClientConnection> getClientConnections() {
		return new HashSet<>(clientConnections);
	}
}
