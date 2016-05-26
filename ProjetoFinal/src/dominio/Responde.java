package dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Responde {
	@Id
	@GeneratedValue
	private Long id;
	private String textoHtml;
	@ManyToOne
	private Questao questao;
	@ManyToOne
	private RealizacaoDeProva realizacao;
	@ManyToOne
	private Opcao opcao;

	public Responde(Long id, String textoHtml, Questao questao, RealizacaoDeProva realizacao, Opcao opcao)
			throws DominioException {
		super();
		this.id = id;
		this.textoHtml = textoHtml;
		this.setQuestão(questao);
		this.setRealizacaoDeProva(realizacao);
		this.opcao = opcao;
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

	public String getTextoHtml() {
		return textoHtml;
	}

	public void setTextoHtml(String textoHtml) throws DominioException {
		validarTexto(textoHtml);
		this.textoHtml = textoHtml;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestão(Questao questao) {
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

	public void setRealizacaoDeProva(RealizacaoDeProva realizacao) {
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

	public void setOpcao(Opcao opcao) {

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

	@Override
	public String toString() {
		return "Responde [textoHtml=" + textoHtml + "]";
	}

	// validação dos atributos
	public void validarTexto(String t) throws DominioException {
		if (t == null || t.length() == 0)
			throw new DominioException(ErroDominio.TEXTO_INVALIDO);
	}

}
