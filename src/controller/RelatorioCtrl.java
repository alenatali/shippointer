package controller;

import dao.IRelatorio;
import dao.mssqlserver.RelatorioDAO;
import model.Curso;

public class RelatorioCtrl {
	
	private IRelatorio dao = new RelatorioDAO();

	public String getRelatorioAlunoCurso(Curso c) {
		StringBuilder sb = new StringBuilder();
		for(String s : dao.alunosPorCurso(c)){
			sb.append(s);
			sb.append("\n");
		}
		return sb.toString();
	}

	public String getRelatorioCursosPopulares() {
		StringBuilder sb = new StringBuilder();
		for(String s : dao.cursosPopulares()) {
			sb.append(s);
			sb.append("\n");
		}
		return sb.toString();
	}

	public String getRelatorioAlunoCidade() {
		StringBuilder sb = new StringBuilder();
		for (String s : dao.alunosPorCidade()) {
			sb.append(s);
			sb.append("\n");
		}
		return sb.toString();
	}

}
