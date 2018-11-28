import Connection.ClientConnection;
import Connection.ClientConnectionsRoutine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainTest {
	public static void main(String[] args) {
		ClientConnectionsRoutine clientConnectionsRoutine = new ClientConnectionsRoutine();
		new Thread(clientConnectionsRoutine).start();
		while (true){
			if(clientConnectionsRoutine.getClientConnections().size()>0){
				for (ClientConnection client:clientConnectionsRoutine.getClientConnections()) {
					BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
					String line;
					try {
						while(br.ready()) {
							line = br.readLine();
							System.out.println(line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
