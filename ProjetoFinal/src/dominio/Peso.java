package dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import controle.ITabelavel;

/**
 * Implementa a classe Peso que tem o "implements Serializable" para realizar o
 * processo de serialização e o "implements Tabelavel" para informar que os
 * objetos poderão ser exibidos em uma tabela de interface
 * 
 *
 */
@Entity
public class Peso implements IDados, ITabelavel, Serializable {
	// Atributos
	@Id
	@GeneratedValue
	private Long id;
	private int valor;
	@ManyToOne
	private Questao questao;
	@ManyToOne
	private Prova prova;

	// Métodos
	public Peso(Long id, int valor, Questao questao, Prova prova) throws DadosException {
		super();
		this.id = id;
		this.setValor(valor);
		this.setQuestão(questao);
		this.setProva(prova);
	}

	public Peso() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) throws DadosException {
		validarValor(valor);
		this.valor = valor;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestão(Questao questao) throws DadosException {

		if (this.questao == questao)
			return;
		if (questao == null) {

			Questao antigo = this.questao;
			this.questao = null;
			antigo.removePeso(this);

		} else {

			if (this.questao != null) {
				this.questao.removePeso(this);
				this.questao = questao;
				questao.adicionaPeso(this);

			}
		}

	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) throws DadosException {
		if (this.prova == prova)
			return;
		if (prova == null) {

			Prova antigo = this.prova;
			this.prova = null;
			antigo.removePeso(this);

		} else {

			if (this.prova != null) {
				this.prova.removePeso(this);
				this.prova = prova;
				prova.adicionaPeso(this);

			}
		}
	}

	/**
	 * Implementação do método toString que retorna uma String que descreve o
	 * objeto Peso
	 */
	public String toString() {
		return "Peso [prova=" + prova + "]";
	}

	// validação dos atributos
	@RegraDeDominio
	public void validarValor(int v) throws DadosException {
		if (v == -1)
			throw new DadosException(new ErroDeDominio(1, "Valor não pode ser negativo"));

	}

	@RegraDeDominio
	public void validarQuestao(Questao questao) throws DadosException {
		if (questao == null)
			throw new DadosException(new ErroDeDominio(2, "A Questão não pode ser nulo"));

	}

	@RegraDeDominio
	public void validarProva(Prova prova) throws DadosException {
		if (prova == null)
			throw new DadosException(new ErroDeDominio(3, "A Prova não pode ser nula"));

	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		// TODO Auto-generated method stub
		return new Object[] { this.valor };
	}

	@Override
	public Object getChave() {
		// TODO Auto-generated method stub
		return id;
	}

}
