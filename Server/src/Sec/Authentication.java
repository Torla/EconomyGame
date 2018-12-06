package Sec;

import java.io.*;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

public class Authentication {

	private static final String PSW_PATH= "Server/data/passwords.csv";

	static SecureRandom secureRandom = new SecureRandom();
	static KeyPairGenerator keyPairGenerator = null;

	static private Map<String,String> passowrd = null;
	static private Map<String,Map<String,Object>> keys = new HashMap<>();


	private static String pswDigest(String psw) {

		MessageDigest digest=null;

		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] hash = digest.digest(psw.getBytes());

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	private static void loadPsw(){
		BufferedReader br=null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(PSW_PATH)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		passowrd = new HashMap<>();
		String line;
		try {
			while((line=br.readLine())!=null){
				String[] s= line.split(",");
				passowrd.put(s[0],s[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean checkPsw(String name, String psw){
		if(passowrd==null) loadPsw();
		return passowrd.containsKey(name) && passowrd.get(name).equals(pswDigest(psw));
	}
	public  static void newPsw(String name,String psw){
		OutputStreamWriter ow=null;
		try {
			ow = new OutputStreamWriter(new FileOutputStream(PSW_PATH, true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		try {
			ow.write(name + "," + pswDigest(psw) + "\n");
			ow.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String,Object> genAsymmetricKeys(String name){
		if(keyPairGenerator==null) {
			try {
				keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			keyPairGenerator.initialize(2048, secureRandom);
		}
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		Map<String, Object> key = new HashMap<String, Object>();
		key.put("private", privateKey);
		key.put("public", publicKey);
		keys.put(name,key);
		return key;
	}
	public static   Map<String,Object> getAsymmetricKeys(String name){
		return keys.get(name);
	}
}
