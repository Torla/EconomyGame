package Service;

import Connection.ClientConnection;
import Protocol.Protocol;
import Sec.Authentication;
import World.World;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.security.PublicKey;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("Duplicates")
public class ClientCare implements Runnable {
	private ClientConnection connection;

	public ClientCare(ClientConnection connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String req=null;
		try {

			req=br.readLine();

			if(req.equals(Protocol.worldMessage)){
				sendWorldData();
				return;
			}
			else if (req.equals(Protocol.post)){
				acceptPost(br);
				return;
			}
			else if(req.equals(Protocol.key)){
				exchangeKey();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void  exchangeKey(){
		Map<String,Object> keys = null;
		Integer sessionId= new Random().nextInt();
		keys = Authentication.genAsymmetricKeys(sessionId.toString());

		System.out.println(DatatypeConverter.printHexBinary(((PublicKey) keys.get("public")).getEncoded()));

		try {
			byte[] pubKey = ((PublicKey) keys.get("public")).getEncoded();
			OutputStream out = connection.getOutputStream();
			out.write(pubKey);
			OutputStreamWriter outW = new OutputStreamWriter(out);
			outW.write("/n");
			outW.write(sessionId+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void sendWorldData(){
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream("Server/data/worldData.xml"));
			while (bis.available()!=0) {
				final int len = (1000< bis.available()) ? 1000: bis.available();
				byte [] array  = new byte [len];
				bis.read(array,0, len);
				connection.getOutputStream().write(array,0, array.length);
			}
			connection.getOutputStream().close(); //todo work around this;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void acceptPost(BufferedReader br){


		Object objectJAXB=null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("WorldData");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller ();
			objectJAXB = unmarshaller.unmarshal(br);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(new World(objectJAXB).getCities().get(0).getWorkers().getFood());

	}

}
