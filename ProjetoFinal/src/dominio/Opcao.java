package dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Opcao {
	@Id
	@GeneratedValue
	private Long id;
	private Status status;

	private enum Status {
		OpcaoEmBranco, OpcaoSelecionada, OpcaoErrada, OpcaoCerta
	};

	private String textoHtml;

	private boolean ehcorreta;
	@ManyToOne
	private Questao questao;
	@OneToMany(mappedBy = "opcao")
	private Set<Comentario> comentario = new HashSet<Comentario>();
	@OneToMany(mappedBy = "opcao")
	private Set<Responde> responde = new HashSet<Responde>();

	public Opcao(Long id, Status status, String textoHtml, boolean ehcorreta, Questao questao) throws DominioException {
		super();
		this.id = id;
		this.setStatus(status);
		this.setTextoHtml(textoHtml);
		this.setEhcorreta(ehcorreta);
		this.setQuestão(questao);
	}

	public Opcao() {
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

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTextoHtml() {
		return textoHtml;
	}

	public void setTextoHtml(String textoHtml) throws DominioException {
		validarTexto(textoHtml);
		this.textoHtml = textoHtml;
	}

	public boolean isEhcorreta() {
		return ehcorreta;
	}

	public void setEhcorreta(boolean ehcorreta) throws DominioException {
		validarEhCorreta(ehcorreta);
		this.ehcorreta = ehcorreta;
	}

	public Questao getQuestão() {
		return questao;
	}

	public void setQuestão(Questao questao) {
		if (this.questao == questao)
			return;
		if (questao == null) {
			Questao antigo = this.questao;
			this.questao = null;
			antigo.removeOpcao(this);

		} else {
			if (this.questao != null)
				this.questao.removeOpcao(this);
			this.questao = questao;
			questao.adicionaOpcao(this);

		}

	}

	public Set<Comentario> getComentario() {
		return new HashSet<Comentario>(this.comentario);
	}

	// metodo adiciona Comentário do atributo de relacionamento n-ário
	public void adicionaComentario(Comentario comentario) {
		if (this.comentario.contains(comentario))
			return;
		this.comentario.add(comentario);
		comentario.setOpcao(this);

	}

	// metodo remove Comentário do atributo de relacionamento n-ário
	public void removeComentario(Comentario comentario) {
		if (!this.comentario.contains(comentario))
			return;
		this.responde.remove(comentario);
		comentario.setOpcao(null);

	}

	public Set<Responde> getResponde() {
		return new HashSet<Responde>(this.responde);
	}

	// metodo adiciona Resposta do atributo de relacionamento n-ário
	public void adicionaResposta(Responde responde) {
		if (this.responde.contains(responde))
			return;
		this.responde.add(responde);
		responde.setOpcao(this);

	}

	// metodo remove Resposta do atributo de relacionamento n-ário
	public void removeResposta(Responde responde) {
		if (!this.responde.contains(responde))
			return;
		this.responde.remove(responde);
		responde.setOpcao(null);

	}

	@Override
	public String toString() {
		return "Opção [textoHtml=" + textoHtml + "]";
	}

	// validação dos atributos
	public void validarTexto(String t) throws DominioException {
		if (t == null || t.length() == 0)
			throw new DominioException(ErroDominio.TEXTO_INVALIDO);
	}

	public void validarEhCorreta(boolean e) throws DominioException {
		if (e != true || e != false)
			throw new DominioException(ErroDominio.ITEM_INVALIDO);
	}

}
