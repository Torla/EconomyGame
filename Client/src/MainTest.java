import Connection.ServerConnection;
import DataManger.DataManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainTest {
	public static void main(String[] args) {

		ServerConnection.connect();

		DataManager.updateWorldData();

		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ServerConnection.disconnect();
	}
}
