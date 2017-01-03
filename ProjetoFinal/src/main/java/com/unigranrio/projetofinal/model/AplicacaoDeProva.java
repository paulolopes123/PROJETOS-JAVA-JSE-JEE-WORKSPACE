package com.unigranrio.projetofinal.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.unigranrio.projetofinal.controller.ITabelavel;


/**
 * Implementa a classe Aplica��o de prova que tem o "implements Serializable"
 * para realizar o processo de serializa��o e o "implements Tabelavel" para
 * informar que os objetos poder�o ser exibidos em uma tabela de interface
 * 
 *
 */

@Entity
public class AplicacaoDeProva implements IDados, ITabelavel, Serializable {
	private static final long serialVersionIUD = 1L;
	private enum Status {
		Disponivel, EmRealizacao, Finalizada;
		public static void validarTransicaoStatus(Status anterior, Status novo) throws DadosException {
			if (anterior == null && novo == EmRealizacao || anterior == Disponivel && novo == EmRealizacao
					|| anterior == Disponivel && novo == Finalizada || anterior == EmRealizacao && novo == Disponivel
					|| anterior == EmRealizacao && novo == Disponivel)
				return;
		}
	};

	// Atributos
	@Id
	@GeneratedValue
	private Long id;
	private Status status;
	private Date dataInicio;

	private Date dataFim;
	@ManyToOne
	private Prova prova;
	@ManyToOne
	private Aluno aluno;
	@ManyToOne
	private Professor professor;

	// M�todos
	public AplicacaoDeProva(Long id, Status status, Date dataInicio, Date dataFim, Time horaInicio, Time horaFim,
			Prova prova, Aluno aluno, Professor professor) throws DadosException {
		super();
		this.id = id;
		this.setStatus(status.EmRealizacao);// estado inicial
		this.setDataInicio(dataInicio);
		this.setDataFim(dataFim);
		this.setProva(prova);
		this.setAluno(aluno);
		this.setProfessor(professor);
	}

	public AplicacaoDeProva() {
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) throws DadosException {
		validarDataInicio(dataInicio);
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) throws DadosException {
		validarDataFim(dataFim);
		this.dataFim = dataFim;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) throws DadosException {
		if (this.prova == prova)
			return;
		if (prova == null) {
			this.setStatus(Status.EmRealizacao);
			Prova antigo = this.prova;
			this.prova = null;
			antigo.removeAplicacaoDeProva(this);
		} else {
			this.setStatus(Status.Disponivel);
			if (this.prova != null)
				this.prova.removeAplicacaoDeProva(this);
			this.prova = prova;
			prova.adicionaAplicacaoDeProva(this);
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) throws DadosException {
		if (this.aluno == aluno)
			return;
		if (aluno == null) {
			this.setStatus(Status.EmRealizacao);
			Aluno antigo = this.aluno;
			this.prova = null;
			antigo.removeAplicacaoDeProva(this);
		} else {
			this.setStatus(Status.Finalizada);

			if (this.aluno != null)
				this.aluno.removeAplicacaoDeProva(this);
			this.aluno = aluno;
			aluno.adicionaAplicacaoDeProva(this);
		}
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) throws DadosException {

		if (this.professor == professor)
			return;
		if (professor == null) {
			this.setStatus(Status.EmRealizacao);
			Professor antigo = this.professor;
			this.professor = null;
			antigo.removeAplicacaoDeProva(this);
		} else {
			this.setStatus(Status.Finalizada);

			if (this.professor != null)
				this.professor.removeAplicacaoDeProva(this);
			this.professor = professor;
			professor.adicionaAplicacaoDeProva(this);
		}
	}

	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Aplica��oDeProva
	 */
	public String toString() {
		return "Aplica��o [aluno=" + aluno + "]";
	}

	// valida��o dos atributos
	@RegraDeDominio
	public void validarDataInicio(Date d) throws DadosException {
		if (d == null || ((CharSequence) d).length() == 0)
			throw new DadosException(new ErroDeDominio(1, "Data De In�cio Inv�lida"));

	}

	@RegraDeDominio
	public void validarDataFim(Date d) throws DadosException {
		if (d == null || ((CharSequence) d).length() == 0)
			throw new DadosException(new ErroDeDominio(2, "Data De Fim Inv�lida"));

	}

	@RegraDeDominio
	public void validarProva(Prova prova) throws DadosException {
		if (prova == null)
			throw new DadosException(new ErroDeDominio(3, "Precisa haver alguma prova em aplica��o"));
	}

	@RegraDeDominio
	public void validarAluno(Aluno aluno) throws DadosException {
		// n�o h� regras de valida��o
	}

	@RegraDeDominio
	public void validarProfessor(Professor professor) throws DadosException {
		if (professor == null)
			throw new DadosException(new ErroDeDominio(4, "Precisa haver algum professor aplicando prova"));
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		// TODO Auto-generated method stub
		return new Object[] { this.dataInicio, this.dataFim };
	}

	@Override
	public Object getChave() {
		// TODO Auto-generated method stub
		return id;
	}

}
