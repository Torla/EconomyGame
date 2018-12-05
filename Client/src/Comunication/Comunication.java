package Comunication;

import Connection.ServerConnection;
import Protocol.Protocol;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;


public class Comunication {

	private static String sessionId;
	private static PublicKey pubKey;

	public static void getKey(){
		ServerConnection serverConnection = new ServerConnection();
		serverConnection.connect();
		serverConnection.send(Protocol.key);

		byte[] encKey= new byte[294];
		try {
			serverConnection.getInputStream().read(encKey,0,294);
		} catch (IOException e) {
			e.printStackTrace();
		}
		KeyFactory kf = null;
		try {
			kf = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(encKey);
		try {
			pubKey = kf.generatePublic(x509EncodedKeySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		System.out.println(DatatypeConverter.printHexBinary(pubKey.getEncoded()));

		try {
			sessionId = new BufferedReader(new InputStreamReader(serverConnection.getInputStream())).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(sessionId);

	}

	public static InputStream getWorldData(){
		ServerConnection serverConnection = new ServerConnection();
		serverConnection.connect();
		serverConnection.send(Protocol.worldMessage);
		return serverConnection.getInputStream();
	}

	public static void PostStream(InputStream stream){
		ServerConnection serverConnection = new ServerConnection();
		serverConnection.connect();
		serverConnection.send(Protocol.post);
		serverConnection.send(stream);
		serverConnection.disconnect();
	}
}
