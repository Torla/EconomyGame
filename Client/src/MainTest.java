import DataManger.DataManager;

public class MainTest {
	public static void main(String[] args) {


		DataManager.updateWorldData();

		System.out.println(WorldData.WorldData.get().getCity().get(1).getProductionModifiers().getClay());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
