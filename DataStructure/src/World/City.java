package World;

import WorldData.ProductionModifiersType;
import WorldData.StockType;
import WorldData.WorkersType;

public class City {
	private String name;
	private Integer population;
	private Integer positionX;
	private Integer positionY;
	private Integer happines;

	private GoodsAttrs productionMod = new GoodsAttrs();
	private GoodsAttrs stock = new GoodsAttrs();
	private GoodsAttrs workers = new GoodsAttrs();

	public GoodsAttrs getStock() {
		return stock;
	}

	public GoodsAttrs getWorkers() {
		return workers;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public void setPositionX(Integer positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(Integer positionY) {
		this.positionY = positionY;
	}

	public void setHappines(Integer happines) {
		this.happines = happines;
	}

	public String getName() {

		return name;
	}

	public Integer getPopulation() {
		return population;
	}

	public Integer getPositionX() {
		return positionX;
	}

	public Integer getPositionY() {
		return positionY;
	}

	public Integer getHappines() {
		return happines;
	}

	void buildProductionMod(ProductionModifiersType productionModifiersType){
		productionMod.setFood((int) productionModifiersType.getFood());
		productionMod.setWood((int) productionModifiersType.getWood());
		productionMod.setClay((int) productionModifiersType.getClay());
		productionMod.setStone((int) productionModifiersType.getStone());
		productionMod.setForniture((int) productionModifiersType.getForniture());
		productionMod.setPottery((int) productionModifiersType.getPottery());
		productionMod.setGold((int) productionModifiersType.getGold());
	}	
	
	void buildStock(StockType stockType){
		stock.setFood((int) stockType.getFood());
		stock.setWood((int) stockType.getWood());
		stock.setClay((int) stockType.getClay());
		stock.setStone((int) stockType.getStone());
		stock.setForniture((int) stockType.getForniture());
		stock.setPottery((int) stockType.getPottery());
		stock.setGold((int) stockType.getGold());
	}
	void buildWorkers(WorkersType workersType){
		workers.setFood((int) workersType.getFood());
		workers.setWood((int) workersType.getWood());
		workers.setClay((int) workersType.getClay());
		workers.setStone((int) workersType.getStone());
		workers.setForniture((int) workersType.getForniture());
		workers.setPottery((int) workersType.getPottery());

	}
	
	public GoodsAttrs getProductionMod() {
		return productionMod;
	}

	ProductionModifiersType buildProductionModType(){
		ProductionModifiersType productionModifiersType=new ProductionModifiersType();
		productionModifiersType.setClay(productionMod.getClay().byteValue());
		productionModifiersType.setFood(productionMod.getFood().byteValue());
		productionModifiersType.setWood(productionMod.getWood().byteValue());
		productionModifiersType.setStone(productionMod.getStone().byteValue());
		productionModifiersType.setForniture(productionMod.getForniture().byteValue());
		productionModifiersType.setPottery(productionMod.getPottery().byteValue());
		productionModifiersType.setGold(productionMod.getGold().byteValue());
		return productionModifiersType;
	}	
	
	StockType buildStockType(){
		StockType stockType=new StockType();
		stockType.setClay(stock.getClay().byteValue());
		stockType.setFood(stock.getFood().byteValue());
		stockType.setWood(stock.getWood().byteValue());
		stockType.setStone(stock.getStone().byteValue());
		stockType.setForniture(stock.getForniture().byteValue());
		stockType.setPottery(stock.getPottery().byteValue());
		stockType.setGold(stock.getGold().byteValue());
		return stockType;
	}	
	
	WorkersType buildworkersType(){
		WorkersType workersType=new WorkersType();
		workersType.setClay(workers.getClay().byteValue());
		workersType.setFood(workers.getFood().byteValue());
		workersType.setWood(workers.getWood().byteValue());
		workersType.setStone(workers.getStone().byteValue());
		workersType.setForniture(workers.getForniture().byteValue());
		workersType.setPottery(workers.getPottery().byteValue());
		return workersType;
	}
}
