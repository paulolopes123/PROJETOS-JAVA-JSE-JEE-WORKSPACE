package com.unigranrio.projetofinal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import com.unigranrio.projetofinal.controller.ITabelavel;

@Entity
public class Aluno extends Usuario implements IDados, ITabelavel, Comparable<Aluno>, Serializable {
	private static final long serialVersionIUD = 1L;

	private enum Status {
		aptoUsarSistema, inaptoUsarSistema;
		public static void validarTransicaoStatus(Status anterior, Status novo) throws DadosException {
			if (anterior == null && novo == inaptoUsarSistema
					|| anterior == aptoUsarSistema && novo == inaptoUsarSistema)

				return;
			throw new DadosException(new ErroDeDominio(1, "Um Aluno n�o pode deixar de ser "
					+ (anterior == null ? "NULO" : anterior) + " e passar a ser " + novo));
		}
	};

	@GeneratedValue
	private long id;
	private Status status;
	private String nome;
	@Column(unique = true)
	private String matr;

	@OneToMany(mappedBy = "aluno")
	private Set<AplicacaoDeProva> aplicacao = new HashSet<AplicacaoDeProva>();
	@OneToMany(mappedBy = "aluno")
	private Set<RealizacaoDeProva> realizacao = new HashSet<RealizacaoDeProva>();

	// M�todos
	public Aluno(Long id, Status status, String nome, String matr) throws DadosException {
		super();
		this.id = id;
		this.setStatus(status.inaptoUsarSistema);// Estado inicial
		this.setNome(nome);
		this.setMatr(matr);

	}

	public Aluno() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status novo) throws DadosException {
		status.validarTransicaoStatus(this.status, novo);
		this.status = novo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws DadosException {
		validarNome(nome);
		this.nome = nome;
	}

	public String getMatr() {
		return matr;
	}

	public void setMatr(String matr) throws DadosException {
		validarMatr(matr);
		this.matr = matr;
	}

	public Set<AplicacaoDeProva> getAplicacao() {
		return new HashSet<AplicacaoDeProva>(this.aplicacao);
	}

	// metodo adiciona aplica��o de prova do atributo de relacionamento n-�rio
	public void adicionaAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		if (this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.add(aplicacao);
		aplicacao.setAluno(this);

	}

	// metodo remove aplica��o de prova do atributo de relacionamento n-�rio
	public void removeAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		if (!this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.remove(aplicacao);
		aplicacao.setAluno(null);

	}

	public Set<RealizacaoDeProva> getRealizacao() {
		return new HashSet<RealizacaoDeProva>(this.realizacao);
	}

	// metodo adiciona realiza��o de prova do atributo de relacionamento n-�rio
	public void adicionaRealizacaoDeProva(RealizacaoDeProva realizacao) throws DadosException {
		if (this.realizacao.contains(realizacao))
			return;
		this.realizacao.add(realizacao);
		realizacao.setAluno(this);

	}

	// metodo remove realiza��o de prova do atributo de relacionamento n-�rio
	public void removeRealizacaoDeProva(RealizacaoDeProva realizacao) throws DadosException {
		if (!this.realizacao.contains(realizacao))
			return;
		this.realizacao.remove(realizacao);
		realizacao.setAluno(null);

	}

	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Aluno
	 */
	public String toString() {
		return "Aluno [nome=" + nome + "]";
	}

	// valida��o dos atributos
	@RegraDeDominio
	public void validarNome(String n) throws DadosException {
		if (n == null || n.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "O Nome n�o pode ser nulo!"));
	}

	@RegraDeDominio
	public void validarMatr(String m) throws DadosException {
		if (m == null || m.length() == 0)
			throw new DadosException(new ErroDeDominio(2, "A Matr�cula n�o pode ser nula"));
	}

	@RegraDeDominio
	public void validarAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		// n�o h� regras de valida��o
	}

	@RegraDeDominio
	public void validarRealizacaoDeProva(RealizacaoDeProva realizacao) throws DadosException {
		if (realizacao == null)
			throw new DadosException(new ErroDeDominio(3, "A Realiza��o n�o pode ser nula"));

	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.nome };
	}

	public Object getChave() {
		return id;
	}

	/**
	 * M�todo utilizado para colocar os Alunos em ordem
	 */
	public int compareTo(Aluno d) {
		return this.nome.compareTo(d.nome);
	}

}
