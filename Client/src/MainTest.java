import DataManger.DataManager;

public class MainTest {
	public static void main(String[] args) {


		DataManager.updateWorldData();

		System.out.println(DataManager.world.getCities().get(1).getWorkers().getFood());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
