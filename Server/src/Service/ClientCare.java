package Service;

import Connection.ClientConnection;
import Protocol.Protocol;
import Sec.Authentication;
import World.World;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
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

		try {
			String pubKey = DatatypeConverter.printHexBinary(((PublicKey) keys.get("public")).getEncoded());
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(pubKey);
			out.write("\n");
			out.write(sessionId+"\n");
			out.close();
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
		String name=null,psw=null,sessionId=null;



		try {
			sessionId=br.readLine();
			name=br.readLine();

			Cipher cipher = Cipher.getInstance("Rsa");
			cipher.init(Cipher.DECRYPT_MODE, (Key) Authentication.getAsymmetricKeys(sessionId).get("private"));

			psw=new String(cipher.doFinal(DatatypeConverter.parseHexBinary(br.readLine())));
		} catch (IOException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
			e.printStackTrace();
		}

		if(!Authentication.checkPsw(name,psw)){
			return;
		}

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
