import DataManger.DataManager;
import WorldData.World;

public class MainTest {
	public static void main(String[] args) {


		DataManager.updateWorldData();

		System.out.println(DataManager.world.getCities().get(1).getWorkers().getFood());

		DataManager.world.getCities().get(1).getWorkers().setFood(10);

		System.out.println(((World)(DataManager.world.instanceObjectJAXB(null))).getCity().get(1).getWorkers().getFood());



		DataManager.postChange("Biella");

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
