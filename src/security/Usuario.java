package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import util.EPerfil;
import util.ETipoEndereco;

public class Usuario {
	
	private String user;
	private String pass = ""; //hash only
	private String tip;
	private EPerfil perfil = null;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getHashPass() {
		return pass;
	}
	public void setPass(String pass) {
		if(pass.equals("")) {
			this.pass = hash( pass );
		}
		this.pass = pass;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public EPerfil getPerfil() {
		return perfil;
	}
	public char[] getPerfilChar() {
		char[] p = new char [] {'o', 'p'};
		if(this.perfil == EPerfil.ADMINISTRADOR) {
			p = new char [] {'a', 'd'};
		}
		return p;
	}
	public void setPerfil(EPerfil perfil) {
		this.perfil = perfil;
	}
	public void setPerfil(char[] c) {	
		this.perfil = EPerfil.OPERADOR;
		if(c.toString().equals("ad")) {
			this.perfil = EPerfil.ADMINISTRADOR;
		}
	}
	public boolean isAuthenticated () {
		if(perfil != null){
			return true;
		}
		return false;
	}
	public static String hash (String s){
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
