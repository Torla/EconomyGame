package Comunication;

import Connection.ServerConnection;
import Protocol.Protocol;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;


public class Comunication {

	private static String sessionId;
	private static PublicKey pubKey=null;

	private static void getKey(){
		ServerConnection serverConnection = new ServerConnection();
		serverConnection.connect();
		serverConnection.send(Protocol.key);
		BufferedReader br = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
		String hexKey=null;
		try {
			hexKey=br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		KeyFactory kf = null;
		try {
			kf = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(DatatypeConverter.parseHexBinary(hexKey));
		try {
			pubKey = kf.generatePublic(x509EncodedKeySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		try {
			sessionId = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		serverConnection.disconnect();

	}



	public static InputStream getWorldData(){
		ServerConnection serverConnection = new ServerConnection();
		serverConnection.connect();
		serverConnection.send(Protocol.worldMessage);
		return serverConnection.getInputStream();
	}

	public static void PostStream(InputStream stream){
		if(pubKey==null) getKey();
		Cipher cipher=null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE,pubKey);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			e.printStackTrace();
		}


		ServerConnection serverConnection = new ServerConnection();
		serverConnection.connect();
		serverConnection.send(Protocol.post);
		serverConnection.send(sessionId);
		serverConnection.send("Torino");
		try {
			String string = DatatypeConverter.printHexBinary(cipher.doFinal("torino".getBytes()));
			serverConnection.send(string);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		serverConnection.send(stream);
		serverConnection.disconnect();
	}
}
