package dao;

import security.Usuario;

public interface IUsuario {
	
	public Usuario validar (String user, String pass);

}
