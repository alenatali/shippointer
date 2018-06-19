package model;

import util.EContato;

public class Contato {

	private int codigo;
	private EContato tipo;
	private String descricao;
	private String valor;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public EContato getTipo() {
		return tipo;
	}
	public void setTipo(EContato tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
