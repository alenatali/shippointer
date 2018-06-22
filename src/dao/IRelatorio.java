package dao;

import java.util.List;

import model.Aluno;
import model.Curso;

public interface IRelatorio {

	public List<String> cursosPopulares ();
	public List<String> alunosPorCidade ();
	public List<String> alunosPorCurso (Curso c);

}
