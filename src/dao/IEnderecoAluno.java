package dao;

import java.util.List;

import model.Endereco;

public interface IEnderecoAluno extends IEndereco {
	
	public int inserirEndereco (Endereco e, String cpf); //retorna o codigo (pk) da base de dados
	public List<Endereco> buscarEnderecos (String cpf);
	public void excluirEndereco (int codEndereco, String cpf);
}
