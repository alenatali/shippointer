package controller;

import java.util.ArrayList;

import dao.mssqlserver.AlunoDAO;
import dao.mssqlserver.Conexao;
import dao.mssqlserver.CursoDAO;
import model.*;
import util.EContato;

public class Main {

	public static void main(String[] args) {
		
/*		Curso c = new Curso();
		c.setNome("ahhhhhhhhh");
		c.setPeriodo('m');
		c.setSigla("AhAhhHHH");
		new CursoDAO().insertCurso(c);*/
		
		ArrayList<Curso> aux = (ArrayList) new CursoDAO().buscarCursos();
		for(int x = 0; x < aux.size(); x++) {
			System.out.println(aux.get(x).getNome() +" - "+ aux.get(x).getPeriodo());
		}	
		
		AlunoDAO ad = new AlunoDAO();
		Aluno a = ad.buscarAluno("1");
		System.out.println(ad.calcIdade(a.getNascimento()));
	}

	
	
}
