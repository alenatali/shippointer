package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Aluno {

	private String cpf;
	private String nome;
	private Date nascimento;
	private Set<Matricula> matriculas;
	private Set<Contato> contatos;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public List getMatriculas() {
		List<Matricula> aux =  new ArrayList<>();
		Iterator<Matricula> i = matriculas.iterator();
		while(i.hasNext()) {
			aux.add(i.next());
		}
		return aux;
	}
	
	public Matricula getMatricula(String ra) {
		Iterator<Matricula> i = matriculas.iterator();
		Matricula m;
		while(i.hasNext()) {
			m = i.next();
			if(m.getRa().equals(ra)){
				return m;
			}
		}
		return null;
	}

	public void addMatricula(Matricula m) {
		if(matriculas == null) {
			matriculas = new HashSet<>();
		}
		matriculas.add(m);
	}
	
	public void removeMatricula(String ra) {
		matriculas.remove(getMatricula(ra));
		if(matriculas.isEmpty()) {
			matriculas = null;
		}
	}

	public List getContatos() {
		List<Contato> aux =  new ArrayList<>();
		Iterator<Contato> i = contatos.iterator();
		while(i.hasNext()) {
			aux.add(i.next());
		}
		return aux;
	}
	
	public Contato getContato(int codigo) {
		Iterator<Contato> i = contatos.iterator();
		Contato c;
		while(i.hasNext()) {
			c = i.next();
			if(c.getCodigo() == codigo){
				return c;
			}
		}
		return null;		
	}
	
	public void addContato(Contato c) {
		if(contatos == null) {
			contatos = new HashSet<>();
		}
		contatos.add(c);
	}
	
	public void removeContato(int codigo) {
		contatos.remove(getContato(codigo));
		if(contatos.isEmpty()) {
			contatos = null;
		}
	}
}
