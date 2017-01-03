package com.unigranrio.projetofinal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.unigranrio.projetofinal.controller.ITabelavel;


/**
 * Implementa a classe Prova que tem o "implements Serializable" para realizar o
 * processo de serializa��o e o "implements Tabelavel" para informar que os
 * objetos poder�o ser exibidos em uma tabela de interface
 * 
 *
 */
@Entity
public class Prova implements IDados, ITabelavel, Comparable<Prova>, Serializable {

	private enum Status {
		EmElaboracao, Pronta;
		public static void validarTransicaoStatus(Status anterior, Status novo) throws DadosException {
			if (anterior == null && novo == Pronta || anterior == EmElaboracao && novo == Pronta)

				return;
			
		}
	};

	// Atributos
	@Column(unique = true)
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String textoHtml;
	private Status status;
	private boolean disponivel;
	@OneToMany(mappedBy = "prova")
	private Set<AplicacaoDeProva> aplicacao = new HashSet<AplicacaoDeProva>();
	@OneToMany(mappedBy = "prova")
	private Set<Peso> peso = new HashSet<Peso>();
	@ManyToOne
	private Professor professor;

	// M�todos
	public Prova(String nome,String textoHtml) throws DadosException {
		super();
		this.setNome(nome);
		this.setTextoHtml(textoHtml);
		this.setStatus(status.EmElaboracao);// Estado inicial
		//this.setDisponivel(disponivel);
		this.setProfessor(professor);
	}

	public Prova() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws DadosException {
		validarNome(nome);
		this.nome = nome;
	}
	
	public String getTextoHtml() {
		return textoHtml;
	}

	public void setTextoHtml(String textoHtml) throws DadosException {
		validarTexto(textoHtml);
		this.textoHtml = textoHtml;
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status novo) throws DadosException {
		status.validarTransicaoStatus(this.status, novo);
		this.status = novo;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) throws DadosException {
		validarDisponivel(disponivel);
		this.disponivel = disponivel;
	}

	public Set<AplicacaoDeProva> getAplicacao() {
		return aplicacao;
	}

	// metodo adiciona aplica��o de prova do atributo de relacionamento n-�rio
	public void adicionaAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		if (this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.add(aplicacao);
		aplicacao.setProva(this);

	}

	// metodo remove aplica��o de prova do atributo de relacionamento n-�rio
	public void removeAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		if (!this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.remove(aplicacao);
		aplicacao.setProva(null);

	}

	public Set<Peso> getPeso() {
		return peso;
	}

	// metodo adiciona Peso do atributo de relacionamento n-�rio
	public void adicionaPeso(Peso peso) throws DadosException {
		if (this.peso.contains(peso))
			return;
		this.peso.add(peso);
		peso.setProva(this);

	}

	// metodo remove Peso do atributo de relacionamento n-�rio
	public void removePeso(Peso peso) throws DadosException {
		if (!this.peso.contains(peso))
			return;
		this.peso.remove(peso);
		peso.setProva(null);

	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) throws DadosException {

		if (this.professor == professor)
			return;
		if (professor == null) {
			this.setStatus(Status.EmElaboracao);
			Professor antigo = this.professor;
			this.professor = null;
			antigo.removeProva(this);
		} else {
			this.setStatus(Status.Pronta);
			if (this.professor != null)
				this.professor.removeProva(this);
			this.professor = professor;
			professor.adicionaProva(this);
		}
	}

	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Prova
	 */
	public String toString() {
		return "Prova [professor=" + professor + "]";
	}

	// valida��o dos atributos
	@RegraDeDominio
	public void validarNome(String n) throws DadosException {
		if (n == null || n.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "O Nome n�o pode ser nulo!"));
	}
	@RegraDeDominio
	public void validarTexto(String t) throws DadosException {
		if (t == null || t.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "O Texto n�o pode ser nulo"));
	}

	@RegraDeDominio
	public void validarDisponivel(boolean e) throws DadosException {
		if (e != true || e != false)
			throw new DadosException(new ErroDeDominio(2, "Item Inv�lido"));
	}

	@RegraDeDominio
	public void validarAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		// N�o h� regras de valida��o
	}

	@RegraDeDominio
	public void validarPeso(Peso peso) throws DadosException {
		if (peso == null)
			throw new DadosException(new ErroDeDominio(3, "O Peso n�o pode ser nulo"));
	}

	@RegraDeDominio
	public void validarProfessor(Professor professor) throws DadosException {
		if (professor == null)
			throw new DadosException(new ErroDeDominio(4, "O Professor n�o pode ser nulo"));
	}

	/**
	 * M�todo utilizado para colocar as Provas em ordem
	 */
	public int compareTo(Prova d) {
		return this.nome.compareTo(d.nome);
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.nome };
	}

	@Override
	public Object getChave() {
		// TODO Auto-generated method stub
		return id;
	}
}
