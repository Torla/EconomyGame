package WorldData;

public class WorldData{
	static WorldType world;
	static public void set(Object o){
		world=(WorldType) o;
	}
	static public WorldType get(){
		return world;
	}
}
