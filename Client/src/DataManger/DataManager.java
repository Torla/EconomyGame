package DataManger;

import Comunication.Comunication;
import World.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class DataManager {
	public static World world=null;
	public static World updateWorldData(){
		Object objectJAXB=null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("WorldData");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller ();
			objectJAXB = unmarshaller.unmarshal(Comunication.getWorldData());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		DataManager.world = new World(objectJAXB);
		return DataManager.world;
	}
}
