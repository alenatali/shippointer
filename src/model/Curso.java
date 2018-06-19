package model;

public class Curso {

	private int codigo;
	private String sigla;
	private String nome;
	private char periodo;
	
	public Curso () {
		
	}
	
	public Curso (int codigo, String sigla, String nome, char periodo) {
		setCodigo(codigo);
		setSigla(sigla);
		setNome(nome);
		setPeriodo(periodo);
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public char getPeriodo() {
		return periodo;
	}
	public void setPeriodo(char periodo) {
		this.periodo = periodo;
	}
}
