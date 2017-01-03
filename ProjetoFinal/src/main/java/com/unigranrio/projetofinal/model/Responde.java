package com.unigranrio.projetofinal.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.unigranrio.projetofinal.controller.ITabelavel;


/**
 * Implementa a classe Responde que tem o "implements Serializable" para
 * realizar o processo de serializa��o e o "implements Tabelavel" para informar
 * que os objetos poder�o ser exibidos em uma tabela de interface
 * 
 *
 */

@Entity
public class Responde implements IDados, ITabelavel, Serializable {
	private static final long serialVersionIUD = 1L;

	// Atributos

	@Id
	@GeneratedValue
	private Long id;
	private Time horaResposta;
	private String textoHtml;
	@ManyToOne
	private Questao questao;
	@ManyToOne
	private RealizacaoDeProva realizacao;
	@ManyToOne
	private Opcao opcao;

	// M�todos

	public Responde(Long id, Time horaResposta, String textoHtml, Questao questao, RealizacaoDeProva realizacao,
			Opcao opcao) throws DadosException {
		super();
		this.id = id;
		this.setHoraResposta(horaResposta);
		this.setTextoHtml(textoHtml);
		this.setQuestao(questao);
		this.setRealizacaoDeProva(realizacao);
		this.setOpcao(opcao);
	}

	public Responde() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getHoraResposta() {
		return horaResposta;
	}

	public void setHoraResposta(Time horaResposta) throws DadosException {
		validarHoraResposta(horaResposta);
		this.horaResposta = horaResposta;
	}

	public String getTextoHtml() {
		return textoHtml;
	}

	public void setTextoHtml(String textoHtml) throws DadosException {
		validarTexto(textoHtml);
		this.textoHtml = textoHtml;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) throws DadosException {
		if (this.questao == questao)
			return;
		if (this.questao == null) {
			Questao antigo = this.questao;
			this.questao = null;
			antigo.removeResposta(this);

		} else {
			if (this.questao != null) {

				this.questao.removeResposta(this);
				this.questao = questao;
				questao.adicionaResposta(this);

			}

		}

	}

	public RealizacaoDeProva getRealizacaodeprova() {
		return realizacao;
	}

	public void setRealizacaoDeProva(RealizacaoDeProva realizacao) throws DadosException {
		if (this.realizacao == realizacao)
			return;
		if (this.realizacao == null) {
			RealizacaoDeProva antigo = this.realizacao;
			this.realizacao = null;
			antigo.removeResposta(this);

		} else {
			if (this.realizacao != null) {

				this.realizacao.removeResposta(this);
				this.realizacao = realizacao;
				realizacao.adicionaResposta(this);

			}

		}

	}

	public Opcao getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcao opcao) throws DadosException {

		if (this.opcao == opcao)
			return;
		if (this.opcao == null) {
			Opcao antigo = this.opcao;
			this.opcao = null;
			antigo.removeResposta(this);

		} else {
			if (this.opcao != null) {

				this.opcao.removeResposta(this);
				this.opcao = opcao;
				opcao.adicionaResposta(this);

			}

		}

	}

	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Responde
	 */
	public String toString() {
		return "Responde [textoHtml=" + textoHtml + "]";
	}

	// valida��o dos atributos
	@RegraDeDominio
	public void validarHoraResposta(Time r) throws DadosException {
		if (r == null || ((CharSequence) r).length() == -1)
			throw new DadosException(new ErroDeDominio(2, "Hora de in�cio inv�lida"));

	}

	@RegraDeDominio
	public static void validarTexto(String t) throws DadosException {
		if (t == null || t.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "O texto n�o pode ser nulo"));
	}

	@RegraDeDominio
	public static void validarQuestao(Questao questao) throws DadosException {
		// N�o h� regras de valida��o
	}

	@RegraDeDominio
	public static void validarRealizacaoDeProva(RealizacaoDeProva realizacao) throws DadosException {
		// N�o h� regras de valida��o
	}

	@RegraDeDominio
	public static void validarOpcao(Opcao opcao) throws DadosException {
		// n�o h� regras de valida��o
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.id, this.textoHtml, this.questao.getTextoHtml(), this.realizacao.getAluno(),
				this.opcao.getComentario() };
	}

	public Object getChave() {
		return id;
	}

}
