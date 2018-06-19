package dao.mssqlserver;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.ICurso;
import model.Curso;

public class CursoDAO implements ICurso{

	@Override
	public List<Curso> buscarVarios() {
		List<Curso> lista = new ArrayList<>();
		String sql = "SELECT * FROM curso";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );	
			ResultSet rs = stmt.executeQuery();
			Curso c = null;
			while (rs.next()) { 
				int codigo = rs.getInt("codigo");
				String sigla = rs.getString("sigla");
				String nome = (rs.getString("nome"));
				char periodo;
				Reader r = rs.getCharacterStream("periodo");
				try {
					periodo = (char) r.read();
					c = new Curso(codigo, sigla, nome, periodo);
				} catch (IOException e) {
					e.printStackTrace();
				}
				lista.add( c );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public Curso buscar(int codCurso) {
		Curso c = null;
		String sql = "SELECT * FROM curso WHERE codigo = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setInt( 1, codCurso );
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) { 
				c.setCodigo( rs.getInt("codigo") );
				c.setSigla( rs.getString("sigla") );
				c.setNome( (rs.getString("nome")) );
				c.setPeriodo( (char) rs.getCharacterStream("periodo").read() );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void inserir(Curso c) {
		String sql = "INSERT INTO curso (sigla, nome, periodo) VALUES (?, ?, ?)";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1, c.getSigla());
			stmt.setString(2, c.getNome());
			stmt.setCharacterStream(3, new CharArrayReader( new char [] {c.getPeriodo()} ) );

			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void alterar(Curso c, int codigo) {
		String sql = "UPDATE curso SET sigla = ?, nome = ?, periodo = ? WHERE codigo = ?;";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1, c.getSigla());
			stmt.setString(2, c.getNome());
			stmt.setCharacterStream(3,  new CharArrayReader( new char [] {c.getPeriodo()} ) );
			stmt.setInt(4, codigo);
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void excluir(int codigo) {
		String sql = "DELETE FROM curso WHERE codigo = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setInt(1, codigo);
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
