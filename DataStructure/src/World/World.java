package World;

import WorldData.CityType;

import java.util.LinkedList;
import java.util.List;

public class World {

	private List<City> cities = new LinkedList<>();
	public World(Object o) {
		WorldData.World world = (WorldData.World) o;
		for(CityType cityType:world.getCity()) {
			City city = new City();
			city.setName(cityType.getName());
			city.setHappines(cityType.getHappines().intValue());
			city.setPopulation(cityType.getPopulation().intValue());
			city.setPositionX(cityType.getPositionX().intValue());
			city.setPositionY(cityType.getPostionY33().intValue());
			city.buildProductionMod(cityType.getProductionModifiers());
			city.buildStock(cityType.getStock());
			city.buildWorkers(cityType.getWorkers());
			cities.add(city);
		}
	}

	public List<City> getCities() {
		return cities;
	}
}
