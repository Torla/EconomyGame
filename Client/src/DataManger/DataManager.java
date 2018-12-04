package DataManger;

import Comunication.Comunication;
import World.World;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

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

	public static void postChange(String cityName){
		Object object = world.instanceObjectJAXB(cityName);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("WorldData");
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(object,byteArrayOutputStream);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		Comunication.PostStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));

	}
}
