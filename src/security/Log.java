package security;

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
	
	public Usuario validar (String user, String pass) {
		return new UsuarioDAO().validar(user, Usuario.hash( pass ));
	}
	
}
