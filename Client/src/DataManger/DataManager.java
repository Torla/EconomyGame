package DataManger;

import Comunication.Comunication;
import WorldData.WorldData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class DataManager {
	public static void updateWorldData(){
		Object objectJAXB=null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("WorldData");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller ();
			objectJAXB = unmarshaller.unmarshal(Comunication.getWorldData());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		WorldData.set(objectJAXB);
	}
}
