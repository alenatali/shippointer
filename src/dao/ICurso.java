package dao;

import java.util.List;

import model.Curso;

public interface ICurso {

	public List<Curso> buscarVarios ();
	public Curso buscar (int codCurso);
	public void inserir (Curso c);
	public void alterar(Curso c, int codigo);
	public void excluir (int codigo);
}
