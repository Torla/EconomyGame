import Connection.ServerConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainTest {
	public static void main(String[] args) {

		ServerConnection serverConnection = new ServerConnection();
		try {
			serverConnection.send(new FileInputStream(new File("Client/Client.iml")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
