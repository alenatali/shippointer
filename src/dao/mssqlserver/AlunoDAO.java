package dao.mssqlserver;

import dao.IAluno;
import dao.IContatoAluno;
import dao.IEnderecoAluno;
import model.Aluno;
import model.Contato;
import model.Endereco;

import java.io.CharArrayReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlunoDAO implements IAluno, IContatoAluno, IEnderecoAluno{
	
	@Override
	public int inserir(Aluno a) {
		String sql = new StringBuilder ("INSERT INTO aluno (cpf, nome, dt_nasc)")
		.append(" OUTPUT INSERTED.codigo AS codigo")
		.append(" VALUES (?, ?, ?)").toString();
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1,  a.getCpf());
			stmt.setString(2,  a.getNome());
			stmt.setDate(3, (java.sql.Date) a.getNascimento());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				return rs.getInt( "codigo" );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Aluno buscar(String cpf) {
		Aluno a = null;
		String sql = "SELECT * FROM aluno WHERE cpf = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				a = new Aluno();
				a.setNome( rs.getString("nome") );
				a.setCpf( rs.getString("cpf") );
				a.setNascimento( rs.getDate("dt_nasc") );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	@Override
	public List<Aluno> buscarVarios(String nome) {
		List<Aluno> lista = new ArrayList<>();
		String sql = "SELECT * FROM aluno al WHERE al.nome = '%?%'";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1, nome);			
			ResultSet rs = stmt.executeQuery();
			Aluno a = null;
			while (rs.next()) { 
				a = new Aluno();
				a.setNome( rs.getString("nome") );
				a.setCpf( rs.getString("cpf") );
				a.setNascimento( rs.getDate("dt_nasc") );
				lista.add( a );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void alterar(Aluno a, String cpf) {
		String sql = "UPDATE aluno SET cpf = ?, nome = ?, dt_nasc = ? WHERE  cpf = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1,  a.getCpf());
			stmt.setString(2,  a.getNome());
			stmt.setDate(3,  (java.sql.Date) a.getNascimento());
			stmt.setString(4,  cpf);			
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void excluir(String cpf) {
		String sql = "DELETE FROM aluno WHERE cpf = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1, cpf);
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	
	//-------------CONTATO
	
	@Override
	public int inserirContato(Contato c, String cpf) {
		int codigo = 0;
		String sql = new StringBuilder ("INSERT INTO contato (valor, descricao)")
				.append(" OUTPUT INSERTED.codigo AS codigo")
				.append(" VALUES (?, ?) ").toString();
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1,  c.getValor());
			stmt.setString(2,  c.getDescricao());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				codigo = rs.getInt("codigo");
				definirRelacaoContato(codigo, cpf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return codigo;
	}
	
	private void definirRelacaoContato (int codigo, String cpf) {
		String sql = new StringBuilder ("INSERT INTO contato_aluno (aluno_cpf, contato_codigo)")
				.append(" VALUES (?, ?) ").toString();
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1,  cpf);
			stmt.setInt(2,  codigo);
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public List<Contato> buscarContatos(String cpf) {
		List<Contato> lista = new ArrayList<>();
		String sql = new StringBuilder
		("SELECT co.descricao, co. valor")
				.append("FROM contato co LEFT JOIN contato_aluno ac")
				.append("ON ac.contato_codigo = co.codigo")
				.append("WHERE ac.aluno_cpf = ? ORDER BY co.codigo").toString();
		
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1, cpf);			
			ResultSet rs = stmt.executeQuery();
			Contato c = null;
			while (rs.next()) { 
				c = new Contato();
				c.setDescricao( rs.getString("descricao") );
				c.setValor( rs.getString("valor") );
				c.setCodigo( rs.getInt("codigo") );
				lista.add( c );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void alterarContato (Contato a, int codContato) {
		String sql = "UPDATE contato SET valor = ?, descricao = ? WHERE codigo = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1,  a.getValor());
			stmt.setString(2,  a.getDescricao());
			stmt.setInt(3, codContato);
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void excluirContato (int codContato, String cpf) {
		removerRelacaoContato(codContato, cpf);
		String sql = "DELETE FROM contato WHERE codigo = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setInt(1, codContato);
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void removerRelacaoContato (int codContato, String cpf) {
		String sql = "DELETE FROM contato_aluno WHERE contato_codigo = ? AND aluno_cpf = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setInt(1,  codContato);
			stmt.setString(2,  cpf);
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	//-------------ENDERECO
	
	@Override
	public int inserirEndereco (Endereco e, String cpf) {
		int codigo = 0;
		String sql = new StringBuilder ("INSERT INTO endereco")
				.append(" (tipo, logradouro, n, complemento, uf, cidade)")
				.append(" OUTPUT INSERTED.codigo AS codigo")
				.append(" VALUES (?, ?, ?, ?, ?, ?)").toString();
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setCharacterStream( 1, new CharArrayReader( e.getTipoAbreviado() ) );
			stmt.setString( 2, e.getLogradouro() );
			stmt.setString( 3, e.getN() );
			stmt.setString( 4, e.getCompleto() );
			stmt.setCharacterStream( 5, new CharArrayReader( e.getUf() ) );
			stmt.setString( 6, e.getCidade() );
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				codigo = rs.getInt("codigo");
				definirRelacaoEndereco(codigo, cpf);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return codigo;
	}
	
	private void definirRelacaoEndereco (int codEndereco, String cpf) {
		String sql = new StringBuilder ("INSERT INTO aluno_endereco (aluno_cpf, endereco_codigo)")
				.append(" VALUES (?, ?)").toString();
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString( 1, cpf );
			stmt.setInt( 2, codEndereco );
			int i = stmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public List<Endereco> buscarEnderecos(String cpf) {
		List<Endereco> lista = new ArrayList<>();
		String sql = new StringBuilder
		("SELECT ed.codigo, ed.tipo, ed.logradouro AS l, ed.complemento AS c, ed.n, ed.uf, ed.cidade")
				.append("FROM endereco ed LEFT JOIN aluno_enderco ae")
				.append("ON ae.endereco_codigo = ed.codigo")
				.append("WHERE ae.aluno_cpf = ? ORDER BY ed.codigo").toString();
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setString(1, cpf);			
			ResultSet rs = stmt.executeQuery();
			Endereco e = null;
			while (rs.next()) { 
				e = new Endereco();
				e.setCodigo( rs.getInt("codigo") );
				char[] tipo = {(char) rs.getCharacterStream("tipo").read(),
							   (char) rs.getCharacterStream("tipo").read()};
				e.setTipoAbreviado( tipo );
				e.setLogradouro( rs.getString("l") );
				e.setCompleto( rs.getString("c") );
				e.setN( rs.getString("n") );
				char[] uf = {(char) rs.getCharacterStream("uf").read(),
							 (char) rs.getCharacterStream("uf").read()};
				e.setUf( uf );
				e.setCidade( rs.getString("cidade") );
				lista.add( e );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void alterarEndereco (Endereco e, int codEndereco) {
		String sql = new StringBuilder
		("UPDATE endereco SET")
				.append("tipo=?, logradouro=?, complemento=?, n=?, uf=?, cidade=?")
				.append("WHERE codigo-?").toString();
		
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setCharacterStream(1, new CharArrayReader( e.getTipoAbreviado() ) );
			stmt.setString(2, e.getLogradouro() );
			stmt.setString(3, e.getCompleto() );
			stmt.setString(4, e.getN() );
			stmt.setCharacterStream(5, new CharArrayReader( e.getUf() ) );
			stmt.setString(6, e.getCidade() );
			stmt.setInt(7, codEndereco);
			int i = stmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void excluirEndereco(int codEndereco, String cpf) {
		removerRelacaoEndereco(codEndereco, cpf);
		String sql = "DELETE FROM aluno_endereco WHERE endereco_codigo = ? AND aluno_cpf = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setInt( 1, codEndereco );
			stmt.setString( 2, cpf );
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void removerRelacaoEndereco (int codEndereco, String cpf) {
		String sql = "DELETE FROM endereco WHERE codigo = ?";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setInt( 1, codEndereco );
			int i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int calcIdade(Date d) {
		int ret = 0;
		String sql = "SELECT DATEDIFF(YEAR, ?, GETDATE()) AS idade";
		try {
			PreparedStatement stmt = Conexao.getConection().prepareStatement( sql );
			stmt.setDate( 1, (java.sql.Date) d );			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) { 
				return rs.getInt("idade");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ret <= 0) {
			return 0;
		}
		return ret;
	}

}
