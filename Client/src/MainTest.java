import Connection.ServerConnection;
import DataManger.DataManager;

public class MainTest {
	public static void main(String[] args) {

		ServerConnection.connect();

		DataManager.updateWorldData();
		DataManager.updateWorldData();

		System.out.println(WorldData.WorldData.get().getProva2());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ServerConnection.disconnect();
	}
}
