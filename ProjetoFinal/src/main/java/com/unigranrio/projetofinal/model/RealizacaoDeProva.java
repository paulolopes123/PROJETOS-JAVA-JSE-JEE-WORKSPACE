package com.unigranrio.projetofinal.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.unigranrio.projetofinal.controller.ITabelavel;


/**
 * Implementa a classe Realiza��oDeProva que tem o "implements Serializable"
 * para realizar o processo de serializa��o e o "implements Tabelavel" para
 * informar que os objetos poder�o ser exibidos em uma tabela de interface
 * 
 *
 */
@Entity
public class RealizacaoDeProva implements IDados, ITabelavel, Serializable {
	private static final long serialVersionIUD = 1L;
	// Atributos
	@Id
	@GeneratedValue
	private Long id;
	private Date data;
	private Time horaInicio;
	private Time horaFim;
	@ManyToOne
	private Aluno aluno;
	@ManyToOne
	private AplicacaoDeProva aplicacaodeprova;

	@OneToMany(mappedBy = "realizacao")
	private Set<Responde> responde = new HashSet<Responde>();

	// M�todos
	public RealizacaoDeProva(Long id, Date data, Time horaInicio, Time horaFim, Aluno aluno) throws DadosException {
		super();
		this.id = id;
		this.setData(data);
		this.setHoraInicio(horaInicio);
		this.setHoraFim(horaFim);
		this.setAluno(aluno);
	}

	public RealizacaoDeProva() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataa() {
		return data;
	}

	public void setData(Date data) throws DadosException {
		validarData(data);
		this.data = data;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) throws DadosException {
		validarHoraInicio(horaInicio);
		this.horaInicio = horaInicio;
	}

	public Time getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Time horaFim) throws DadosException {
		validarHoraFim(horaFim);
		this.horaFim = horaFim;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) throws DadosException {
		if (this.aluno == aluno)
			return;
		if (aluno == null) {
			Aluno antigo = this.aluno;
			this.aluno = null;
			antigo.removeRealizacaoDeProva(this);

		} else {
			if (this.aluno != null)
				this.aluno.removeRealizacaoDeProva(this);
			this.aluno = aluno;
			aluno.adicionaRealizacaoDeProva(this);

		}

	}

	public AplicacaoDeProva getAplicacaodeprova() {
		return aplicacaodeprova;
	}

	public void setAplicacaodeprova(AplicacaoDeProva aplicacaodeprova) throws DadosException {
		this.aplicacaodeprova = aplicacaodeprova;
	}

	public Set<Responde> getResponde() {
		return new HashSet<Responde>(this.responde);
	}

	// metodo adiciona Resposta do atributo de relacionamento n-�rio
	public void adicionaResposta(Responde responde) throws DadosException {
		if (this.responde.contains(responde))
			return;
		this.responde.add(responde);
		responde.setRealizacaoDeProva(this);

	}

	// metodo remove Resposta do atributo de relacionamento n-�rio
	public void removeResposta(Responde responde) throws DadosException {
		if (!this.responde.contains(responde))
			return;
		this.responde.remove(responde);
		responde.setRealizacaoDeProva(null);

	}

	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Realiza��oDeProva
	 */
	public String toString() {
		return "RealizacaoDeProva [horaInicio=" + horaInicio + ", horaFim=" + horaFim + "]";
	}

	// valida��o dos atributos
	@RegraDeDominio
	public void validarData(Date d) throws DadosException {
		if (d == null || ((CharSequence) d).length() == 0)
			throw new DadosException(new ErroDeDominio(1, "Data Inv�lido"));

	}

	@RegraDeDominio
	public void validarHoraInicio(Time i) throws DadosException {
		if (i == null || ((CharSequence) i).length() == -1)
			throw new DadosException(new ErroDeDominio(2, "Hora de in�cio inv�lida"));

	}

	@RegraDeDominio
	public void validarHoraFim(Time f) throws DadosException {
		if (f == null || ((CharSequence) f).length() == -1)
			throw new DadosException(new ErroDeDominio(3, "Hora fim inv�lida"));

	}

	@RegraDeDominio
	public void validarAluno(Aluno aluno) throws DadosException {
		if (aluno == null)
			throw new DadosException(new ErroDeDominio(4, "Aluno n�o pode ser nulo"));

	}

	@RegraDeDominio
	public void validarAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		if (aplicacao == null)
			throw new DadosException(new ErroDeDominio(5, "Aplica��o n�o pode ser nula"));

	}

	@RegraDeDominio
	public void validarResponde(Responde responde) throws DadosException {
		// N�o h� regras de valida��o
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		// TODO Auto-generated method stub
		return new Object[] { this.data };
	}

	@Override
	public Object getChave() {
		// TODO Auto-generated method stub
		return id;
	}

}
