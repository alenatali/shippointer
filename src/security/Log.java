package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.mssqlserver.UsuarioDAO;

public class Log {
	
	private static Log log = null;
	
	private Log () {
	}
	
	public static Log getLog () {
		if(log == null) {
			log = new Log();
		}
		return log;
	}
	
	public boolean validar (String user, String pass) {
		return new UsuarioDAO().validar(user, pass);
	}
	
	public static String hash (String s) {
		String encryptedString = "";
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(s.getBytes());
			encryptedString = new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
}
