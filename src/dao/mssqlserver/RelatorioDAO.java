package dao.mssqlserver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IRelatorio;
import model.Curso;

public class RelatorioDAO implements IRelatorio{

	@Override
	public List<String> cursosPopulares() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT CONVERT(varchar, cs.codigo)+' - '+cs.sigla+' - '+cs.nome+' - '+cs.periodo+' - '+CONVERT(varchar, ma.curso_cod) AS inf"
		+" FROM curso cs LEFT JOIN matricula ma"
		+" ON ma.curso_cod = cs.codigo"
		+" GROUP BY cs.nome, ma.curso_cod, cs.codigo, cs.sigla, cs.periodo";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				list.add( rs.getString("inf") );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> alunosPorCidade() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT al.nome+' '+ed.cidade AS inf"
					+" FROM aluno al"
					+" INNER JOIN aluno_endereco"
					+" ON al.cpf = aluno_endereco.aluno_cpf"
					+" INNER JOIN endereco ed"
					+" ON aluno_endereco.endereco_codigo = ed.codigo"
					+" ORDER BY ed.cidade, al.nome";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				list.add( rs.getString("inf") );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> alunosPorCurso(Curso c) {
		List<String> list = new ArrayList<>();
		String sql = "SELECT ma.ra+' '+al.nome AS inf"
					+" FROM matricula ma"
					+" INNER JOIN aluno al"
					+" ON ma.aluno_cpf = al.cpf"
					+" INNER JOIN curso cu"
					+" ON ma.curso_cod = cu.codigo"
					+" WHERE cu.codigo = ?";

		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setInt( 1, c.getCodigo() );
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				list.add( rs.getString("inf") );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
