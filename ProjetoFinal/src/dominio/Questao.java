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
public class Questao {
	@Id
	@GeneratedValue
	private Long id;
	private Status status;

	private enum Status {
		QuestaoEmBranco, QuestaoSelecionada, QuestaoErrada, QuestaoCerta
	};

	private String textoHtml;
	@OneToMany(mappedBy = "questao")
	private Set<Peso> peso = new HashSet<Peso>();
	@OneToMany(mappedBy = "questao")
	private Set<Responde> responde = new HashSet<Responde>();
	@OneToMany(mappedBy = "questao")
	private Set<Opcao> opcao = new HashSet<Opcao>();
	@ManyToOne
	private Professor professor;

	public Questao(Long id, Status status, String textoHtml, Professor professor) throws DominioException {
		super();
		this.id = id;
		this.setStatus(status);
		this.setTextoHtml(textoHtml);
		this.setProfessor(professor);

	}

	public Questao() {
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

	public Set<Peso> getPeso() {
		return peso;
	}

	// metodo adiciona Peso do atributo de relacionamento n-�rio
	public void adicionaPeso(Peso peso) {
		if (this.peso.contains(peso))
			return;
		this.peso.add(peso);
		peso.setQuest�o(this);

	}

	// metodo remove Peso do atributo de relacionamento n-�rio
	public void removePeso(Peso peso) {
		if (!this.peso.contains(peso))
			return;
		this.peso.remove(peso);
		peso.setQuest�o(null);

	}

	public Set<Responde> getResponde() {
		return responde;
	}

	// metodo adiciona resposta do atributo de relacionamento n-�rio
	public void adicionaResposta(Responde responde) {
		if (this.responde.contains(responde))
			return;
		this.responde.add(responde);
		responde.setQuest�o(this);

	}

	// metodo remove resposta do atributo de relacionamento n-�rio
	public void removeResposta(Responde responde) {
		if (!this.responde.contains(responde))
			return;
		this.responde.remove(responde);
		responde.setQuest�o(null);

	}

	public Set<Opcao> getOpcao() {
		return opcao;
	}

	// metodo adiciona Op��o do atributo de relacionamento n-�rio
	public void adicionaOpcao(Opcao opcao) {
		if (this.opcao.contains(opcao))
			return;
		this.opcao.add(opcao);
		opcao.setQuest�o(this);

	}

	// metodo remove Op��o do atributo de relacionamento n-�rio
	public void removeOpcao(Opcao opcao) {
		if (!this.opcao.contains(opcao))
			return;
		this.opcao.remove(opcao);
		opcao.setQuest�o(null);

	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		if (this.professor == professor)
			return;
		if (this.professor == null) {
			Professor antigo = this.professor;
			this.professor = null;
			antigo.removeQuestao(this);

		} else {
			if (this.professor != null) {

				this.professor.removeQuestao(this);
				this.professor = professor;
				professor.adicionaQuestao(this);

			}

		}

	}

	@Override
	public String toString() {
		return "Quest�o [textoHtml=" + textoHtml + "]";
	}

	// valida��o dos atributos
	public void validarTexto(String t) throws DominioException {
		if (t == null || t.length() == 0)
			throw new DominioException(ErroDominio.TEXTO_INVALIDO);
	}

}
