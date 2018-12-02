package WorldData;

public class WorldData{
	static private World world;
	static public void set(Object o){
		world=(World) o;

	}
	static public World get(){
		return world;
	}
}
