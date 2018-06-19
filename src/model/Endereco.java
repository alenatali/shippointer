package model;

import util.ETipoEndereco;

public class Endereco {
	
	private int codigo;
	private ETipoEndereco tipo;
	private String logradouro;
	private String n;
	private String completo;
	private char[] uf;
	private String cidade;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public ETipoEndereco getTipo() {
		return tipo;
	}
	
	public void setTipo(ETipoEndereco t) {
		this.tipo = t;
	}
	
	public char[] getTipoAbreviado() {
		switch (this.tipo.toString()) {
          case "AVENIDA": 
        	  return new char[] {'a','v'};
          case "ESTRADA": 
        	  return new char[] {'e','t'};
          case "VIELA":
        	  return new char[] {'v','i'};
          case "TRAVESSA":
        	  return new char[] {'t','r'};
          default://RUA
        	  return new char[] {'r','u'};
		}
	}

	public void setTipoAbreviado(char[] t) {
		switch (new StringBuilder(t[0]).append(t[1]).toString()) {
          case "av": 
        	  this.tipo = ETipoEndereco.AVENIDA;
        	  break;
          case "et": 
        	  this.tipo = ETipoEndereco.ESTRADA;
        	  break;
          case "vi":
        	  this.tipo = ETipoEndereco.VIELA;
        	  break;
          case "tr":
        	  this.tipo = ETipoEndereco.TRAVESSA;
        	  break;
          default://RUA
        	  this.tipo = ETipoEndereco.RUA;
        	  break;
		}
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public String getCompleto() {
		return completo;
	}
	public void setCompleto(String completo) {
		this.completo = completo;
	}
	public char[] getUf() {
		return uf;
	}
	public void setUf(char[] uf) {
		this.uf = uf;
	}
	public String e() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCidade() {
		return cidade;
	}
	
}
