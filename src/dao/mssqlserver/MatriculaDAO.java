package dao.mssqlserver;

import java.io.CharArrayReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IMatricula;
import model.Matricula;

public class MatriculaDAO implements IMatricula{
	
	@Override
	public int inserir(Matricula m, String cpf) {
		int codigo = 0;
		String sql = new StringBuilder
		("INSERT INTO matricula (ra, curso_cod, aluno_cpf, sstatus, dt_inicio, dt_termino)")
		.append("OUTPUT INSERTED.codigo AS codigo")
		.append("VALUES (?,?,?,?,?,?)").toString();
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString( 1, m.getRa() );
			stmt.setInt( 2, m.getCurso().getCodigo() );
			stmt.setString( 3, cpf );
			stmt.setCharacterStream( 4, new CharArrayReader(new char [] {m.getStatus()}) );
			stmt.setDate( 5, m.getDataInicio() );
			stmt.setDate( 6, m.getDataTermino() );
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				codigo = rs.getInt("codigo");
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return codigo;
	}

	@Override
	public List<Matricula> buscarVarios(String ra) {
		ArrayList<Matricula> lista = new ArrayList<>();
		String sql = "SELECT * FROM matricula WHERE aluno_cpf = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString( 1, ra );
			ResultSet rs = stmt.executeQuery();
			Matricula m;
			while(rs.next()) {
				m = new Matricula();
				m.setCodigo( rs.getInt("codigo") );
				m.setRa( rs.getString("ra") );
				m.setCurso( new CursoDAO().buscar( rs.getInt("curso_cod") ) );
				m.setStatus( (char) rs.getCharacterStream("sstatus").read() );
				m.setDataInicio( rs.getDate("dt_inicio") );
				m.setDataTermino( rs.getDate("dt_termino") );
				lista.add( m );
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return lista;
	}

	@Override
	public void alterar(Matricula m, String ra, String cpf) {
		String sql = new StringBuilder
		("UPDATE matricula SET")
		.append("curso_cod=?, aluno_cpf=?, sstatus=?, dt_inicio=?, dt_termino=?")
		.append("WHERE ra=?").toString();

		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setInt( 1, m.getCurso().getCodigo() );
			stmt.setString( 2, cpf );
			stmt.setCharacterStream( 3, new CharArrayReader(new char [] {m.getStatus()}) );
			stmt.setDate( 4, m.getDataInicio() );
			stmt.setDate( 5, m.getDataTermino() );
			stmt.setString( 6, ra );
			int i = stmt.executeUpdate();
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
	}

	@Override
	public void excluir(String ra) {
		String sql = "DELETE FROM matricula WHERE ra = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1, ra);
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
