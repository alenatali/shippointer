package dao;

import java.util.List;

import model.Contato;

public interface IContatoAluno extends IContato{
	
	public int inserirContato (Contato c, String cpf); //retorna o codigo (pk) da base de dados
	public List<Contato> buscarContatos (String cpf);
	public void excluirContato (int codContato, String cpf);
	
}
