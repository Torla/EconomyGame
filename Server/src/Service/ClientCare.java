package Service;

import Connection.ClientConnection;
import Protocol.Protocol;

import java.io.*;

@SuppressWarnings("Duplicates")
public class ClientCare implements Runnable {
	private ClientConnection connection;

	public ClientCare(ClientConnection connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while(true){
			String req=null;
			try {

				req=br.readLine();

				if(req.equals(Protocol.worldMessage)){
					sendWorldData();
					return;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
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
}
