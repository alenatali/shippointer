package dao;

import java.util.Date;
import java.util.List;

import model.Aluno;

public interface IAluno {

	public int inserir (Aluno a); //retorna o codigo do aluno
	public Aluno buscar (String cpf);
	public List<Aluno> buscarVarios (String nome);
	public void alterar (Aluno a, String cpf);
	public void excluir (String cpf);
	public int calcIdade (Date d);
}
