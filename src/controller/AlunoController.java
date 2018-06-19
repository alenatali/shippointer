package controller;

import model.Aluno;
import model.Curso;

import java.util.ArrayList;
import java.util.List;

import dao.*;
import dao.mssqlserver.*;

public class AlunoController{
	
	private IAluno aluno = new AlunoDAO();
	private ICurso curso = new CursoDAO();
	
	//campos view
	private String cpf = "";
	
	public List<Aluno> pesquisarAlunos (String nome) {
		return aluno.buscarAlunos(nome);				
	}
	
	public Aluno pesquisarAluno (String cpf) {
		return aluno.buscarAluno(cpf);
	}

	public String[] listarCursos (){
        ArrayList cs = (ArrayList) curso.buscarCursos();
        String[] cursos = new String[cs.size()];
        for(int x = 0; x < cs.size(); x++){
        	cursos[x] = ((Curso) cs.get(x)).getNome();
        }
		return cursos;
	}
	
	
}
