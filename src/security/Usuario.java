package security;

import util.EPerfil;

public class Usuario {
	
	private String user;
	private String pass; //hash only
	private String tip;
	private EPerfil perfil;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
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
	public void setPerfil(EPerfil perfil) {
		this.perfil = perfil;
	}

}
