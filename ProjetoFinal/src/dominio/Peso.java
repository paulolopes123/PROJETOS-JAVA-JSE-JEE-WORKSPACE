package dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Peso {
	@Id
	@GeneratedValue
	private Long id;
	private int valor;
	@ManyToOne
	private Questao questao;
	@ManyToOne
	private Prova prova;

	public Peso(Long id, int valor, Questao questao, Prova prova)
			throws DominioException {
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

	public void setValor(int valor) throws DominioException {
		validarValor(valor);
		this.valor = valor;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestão(Questao questao) {

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

	public void setProva(Prova prova) {
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

	@Override
	public String toString() {
		return "Peso [prova=" + prova + "]";
	}

	// validação dos atributos
	public void validarValor(int v) throws DominioException {
		if (v == -1)
			throw new DominioException(ErroDominio.VALOR_INVALIDO);

	}

}
