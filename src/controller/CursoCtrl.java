package controller;

import java.util.List;

import dao.ICurso;
import dao.mssqlserver.CursoDAO;
import model.Curso;


public class CursoCtrl {

	private ICurso dao = new CursoDAO();

	public Curso[] getCursos() {
		List<Curso> cursoVarios = dao.buscarVarios();
		Curso[] cursoLista = new Curso[cursoVarios.size()];
		
		cursoLista = cursoVarios.toArray(cursoLista);
		
		return cursoLista;
	}

}
