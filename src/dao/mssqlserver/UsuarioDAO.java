package dao.mssqlserver;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.IUsuario;
import security.Usuario;

public class UsuarioDAO implements IUsuario {

	@Override
	public Usuario validar(String user, String pass) {
		Usuario u = null;
		String sql = "SELECT * FROM usuario WHERE usuario=? AND senha=?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString( 1, user );
			stmt.setString( 2, pass );
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				u = new Usuario();
				u.setUser(user);
				u.setPass(pass);
				u.setTip( rs.getString("dica") );
				String perfil =((BufferedReader) rs.getCharacterStream("perfil")).readLine();
				u.setPerfil( new char[]{perfil.charAt(0), perfil.charAt(1)} );
			} else {
				return null;
			}
		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
		return u;
	}
}
