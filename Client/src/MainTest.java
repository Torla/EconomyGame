import DataManger.DataManager;

public class MainTest {
	public static void main(String[] args) {


		DataManager.updateWorldData();
		DataManager.updateWorldData();

		System.out.println(WorldData.WorldData.get().getProva2());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
