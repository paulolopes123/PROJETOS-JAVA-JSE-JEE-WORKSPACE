package dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Prova {
	@Column(unique = true)
	@Id
	@GeneratedValue
	private Long id;
	private Status status;
	private boolean disponivel;

	private enum Status {
		EmElaboracao, Pronta
	};

	@OneToMany(mappedBy = "prova")
	private Set<AplicacaoDeProva> aplicacao = new HashSet<AplicacaoDeProva>();
	@OneToMany(mappedBy = "prova")
	private Set<Peso> peso = new HashSet<Peso>();
	@ManyToOne
	private Professor professor;

	public Prova(Long id, Status status, boolean disponivel, Professor professor) throws DominioException {
		super();
		this.id = id;
		this.setStatus(status);
		this.setDisponivel(disponivel);
		this.setProfessor(professor);
	}

	public Prova() {
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

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) throws DominioException {
		validarDisponivel(disponivel);
		this.disponivel = disponivel;
	}

	public Set<AplicacaoDeProva> getAplicacao() {
		return aplicacao;
	}

	// metodo adiciona aplicação de prova do atributo de relacionamento n-ário
	public void adicionaAplicacaoDeProva(AplicacaoDeProva aplicacao) {
		if (this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.add(aplicacao);
		aplicacao.setProva(this);

	}

	// metodo remove aplicação de prova do atributo de relacionamento n-ário
	public void removeAplicacaoDeProva(AplicacaoDeProva aplicacao) {
		if (!this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.remove(aplicacao);
		aplicacao.setProva(null);

	}

	public Set<Peso> getPeso() {
		return peso;
	}

	// metodo adiciona Peso do atributo de relacionamento n-ário
	public void adicionaPeso(Peso peso) {
		if (this.peso.contains(peso))
			return;
		this.peso.add(peso);
		peso.setProva(this);

	}

	// metodo remove Peso do atributo de relacionamento n-ário
	public void removePeso(Peso peso) {
		if (!this.peso.contains(peso))
			return;
		this.peso.remove(peso);
		peso.setProva(null);

	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {

		if (this.professor == professor)
			return;
		if (professor == null) {
			Professor antigo = this.professor;
			this.professor = null;
			antigo.removeProva(this);
		} else {

			if (this.professor != null)
				this.professor.removeProva(this);
			this.professor = professor;
			professor.adicionaProva(this);
		}
	}

	@Override
	public String toString() {
		return "Prova [professor=" + professor + "]";
	}

	// validação dos atributos
	public void validarDisponivel(boolean e) throws DominioException {
		if (e != true || e != false)
			throw new DominioException(ErroDominio.ITEM_INVALIDO);
	}

}
