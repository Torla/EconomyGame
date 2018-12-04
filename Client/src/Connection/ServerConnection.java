package Connection;

import java.io.*;
import java.net.Socket;
//todo error handling
public class ServerConnection {
	private final static String SERVER_IP = "127.0.0.1";
	private final static int SERVER_PORT = 10123;
	private static Socket socket;

	public void connect(){
		try {
			socket = new Socket(SERVER_IP,SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect(){
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void send(String string){
		string=string.concat("\n");
		try {
				byte [] stringBytes  =  string.getBytes();
				socket.getOutputStream().write(stringBytes,0, stringBytes.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("Duplicates")
	public void send(InputStream x){
		BufferedInputStream bis = new BufferedInputStream(x);

		try {
			while (bis.available()!=0) {
				final int len = (1000< bis.available()) ? 1000: bis.available();
				byte [] array  = new byte [len];
				bis.read(array,0, len);
				System.out.println(new String(array));
				socket.getOutputStream().write(array,0, array.length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public InputStream getInputStream(){
		try {
			return socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public OutputStream getOutputStream(){
		try {
			return socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
