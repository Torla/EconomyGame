package Service;

import Connection.ClientConnection;

import java.io.*;
import java.util.Arrays;

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
				System.out.println("o");
				req=br.readLine();
				System.out.println("k");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(req.equals("world")){
					sendWorldData();
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
				System.out.println(Arrays.toString(array));
				connection.getOutputStream().write(array,0, array.length);
			}
			connection.getOutputStream().close(); //todo work around this;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
