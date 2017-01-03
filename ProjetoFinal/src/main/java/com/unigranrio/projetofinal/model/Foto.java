package com.unigranrio.projetofinal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
@Table(name = "FOTO")
public class Foto implements Serializable {
	private static final long serialVersionUID = 1L;
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FOTO_ID")
	private long id;
	@Lob
	@Column(name = "FOTO_IMAGEM", columnDefinition = "LONGBLOB")
	private byte[] imagem;
	@Column(name = "FOTO_DESCRICAO")
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "PROD_ID")
	private Questao questao;

	// M�todos
	public Foto(Questao questao) throws DadosException {
		super();
		this.setQuestao(questao);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
			antigo.removeFoto(this);

		} else {
			if (this.questao != null) {

				this.questao.removeFoto(this);
				this.questao = questao;
				questao.adicionaFoto(this);

			}

		}

	}

	// Valida��o dos atributos
	@RegraDeDominio
	public void validarImagem(byte[] imagem) throws DadosException {
		// N�o h� regras de valida��o
	}

	public void validarDescricao(String descricao) throws DadosException {
		// N�o h� regras de valida��o
	}

}
