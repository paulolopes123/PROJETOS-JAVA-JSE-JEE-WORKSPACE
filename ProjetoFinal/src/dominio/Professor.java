package dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professor {
	@Id
	@GeneratedValue
	private Long id;
	private Status status;

	private enum Status {
		Contratado, Lincenca, AptoMinistrarAulas, Ferias, Demitido
	}

	private String nome;
	@Column(unique = true)
	private String matr;
	@OneToMany(mappedBy = "professor")
	private Set<Prova> prova = new HashSet<Prova>();
	@OneToMany(mappedBy = "professor")
	private Set<Questao> questao = new HashSet<Questao>();
	@OneToMany(mappedBy = "professor")
	private Set<Comentario> comentario = new HashSet<Comentario>();
	@OneToMany(mappedBy = "professor")
	private Set<AplicacaoDeProva> aplicacao = new HashSet<AplicacaoDeProva>();

	public Professor(Long id, Status status, String nome, String matr) throws DominioException {
		super();
		this.id = id;
		this.setStatus(status);
		this.setNome(nome);
		this.setMatr(matr);
	}

	public Professor() {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws DominioException {
		validarNome(nome);
		this.nome = nome;
	}

	public String getMatr() {
		return matr;
	}

	public void setMatr(String matr) throws DominioException {
		validarMatr(matr);
		this.matr = matr;
	}

	public Set<Prova> getProva() {
		return new HashSet<Prova>(this.prova);
	}

	// metodo adiciona Prova do atributo de relacionamento n-ário
	public void adicionaProva(Prova prova) {
		if (this.prova.contains(prova))
			return;
		this.prova.add(prova);
		prova.setProfessor(this);

	}

	// metodo remove Prova do atributo de relacionamento n-ário
	public void removeProva(Prova prova) {
		if (!this.prova.contains(prova))
			return;
		this.prova.remove(prova);
		prova.setProfessor(null);

	}

	public Set<Questao> getQuestao() {
		return new HashSet<Questao>(this.questao);
	}

	// metodo adiciona Questao do atributo de relacionamento n-ário
	public void adicionaQuestao(Questao questao) {
		if (this.questao.contains(questao))
			return;
		this.questao.add(questao);
		questao.setProfessor(this);

	}

	// metodo remove Questao do atributo de relacionamento n-ário
	public void removeQuestao(Questao questao) {
		if (!this.questao.contains(questao))
			return;
		this.questao.remove(questao);
		questao.setProfessor(null);

	}

	public Set<Comentario> getComentario() {
		return new HashSet<Comentario>(this.comentario);
	}

	// metodo adiciona Comentario do atributo de relacionamento n-ário
	public void adicionaComentario(Comentario comentario) {
		if (this.comentario.contains(comentario))
			return;
		this.comentario.add(comentario);
		comentario.setProfessor(this);

	}

	// metodo remove Comentario do atributo de relacionamento n-ário
	public void removeComentario(Comentario comentario) {
		if (!this.comentario.contains(comentario))
			return;
		this.comentario.remove(comentario);
		comentario.setProfessor(null);

	}

	public Set<AplicacaoDeProva> getAplicacao() {
		return new HashSet<AplicacaoDeProva>(this.aplicacao);
	}

	// metodo adiciona aplicação de prova do atributo de relacionamento n-ário
	public void adicionaAplicacaoDeProva(AplicacaoDeProva aplicacao) {
		if (this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.add(aplicacao);
		aplicacao.setProfessor(this);

	}

	// metodo remove aplicação de prova do atributo de relacionamento n-ário
	public void removeAplicacaoDeProva(AplicacaoDeProva aplicacao) {
		if (!this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.remove(aplicacao);
		aplicacao.setProfessor(null);

	}

	@Override
	public String toString() {
		return "Professor [nome=" + nome + "]";
	}

	// validação dos atributos
	public void validarNome(String n) throws DominioException {
		if (n == null || n.length() == 0)
			throw new DominioException(ErroDominio.NOME_INVALIDO);
	}

	public void validarMatr(String m) throws DominioException {
		if (m == null || m.length() == 0)
			throw new DominioException(ErroDominio.MATRICULA_NULA);
	}

}
