package World;

import WorldData.CityType;

import java.util.LinkedList;
import java.util.List;

public class World {

	private List<City> cities = new LinkedList<>();

	public World(){}

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

	public Object instanceObjectJAXB(){
		WorldData.World o=new WorldData.World();
		for(City city:cities){
			CityType cityType=new CityType();
			cityType.setName(city.getName());
			cityType.setPopulation(city.getPopulation().byteValue());
			cityType.setPostionY33(city.getPositionY().byteValue());
			cityType.setPositionX(city.getPositionX().byteValue());
			cityType.setHappines(city.getHappines().byteValue());
			cityType.setProductionModifiers(city.buildProductionModType());
			cityType.setStock(city.buildStockType());
			cityType.setWorkers(city.buildworkersType());
			o.getCity().add(cityType);
		}
		return o;
	}
}
