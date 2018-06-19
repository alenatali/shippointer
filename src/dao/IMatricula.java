package dao;

import java.util.List;

import model.Matricula;

public interface IMatricula {

	public int inserir (Matricula m, String cpf);
	public List<Matricula> buscarVarios (String ra);
	public void alterar (Matricula m, String ra, String cpf);
	public void excluir (String ra);	
}
