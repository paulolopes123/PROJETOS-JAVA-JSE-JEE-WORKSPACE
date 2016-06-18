package dominio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import controle.ITabelavel;
import dominio.DadosException;
import dominio.ErroDeDominio;

/**
 * Implementa a classe Professor que tem o "implements Serializable" para
 * realizar o processo de serialização e o "implements Tabelavel" para informar
 * que os objetos poderão ser exibidos em uma tabela de interface
 * 
 *
 */
@Entity
public class Professor extends Usuario implements IDados, ITabelavel, Comparable<Professor>, Serializable {

	private enum Status {
		aptoUsarSistema, inaptoUsarSistema;
		public static void validarTransicaoStatus(Status anterior, Status novo) throws DadosException {
			if (anterior == null && novo == inaptoUsarSistema
					|| anterior == aptoUsarSistema && novo == inaptoUsarSistema)

				return;
			throw new DadosException(new ErroDeDominio(1, "Um Professor não pode deixar de ser " + (anterior==null?"NULO":anterior) + " e passar a ser " + novo));
		}
	};

	// Atributos
	@Id
	@GeneratedValue
	private Long id;
	private Status status;
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

	// Métodos
	public Professor(Long id, Status status, String nome, String matr) throws DadosException {
		super();
		this.id = id;
		this.setStatus(status.inaptoUsarSistema);// Estado inicial
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

	public void setStatus(Status novo) throws DadosException {
		status.validarTransicaoStatus(this.status, novo);
		this.status = novo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws DadosException {
		validarNome(nome);
		this.nome = nome;
	}

	public String getMatr() {
		return matr;
	}

	public void setMatr(String matr) throws DadosException {
		validarMatr(matr);
		this.matr = matr;
	}

	public Set<Prova> getProva() {
		return new HashSet<Prova>(this.prova);
	}

	// metodo adiciona Prova do atributo de relacionamento n-ário
	public void adicionaProva(Prova prova) throws DadosException {
		if (this.prova.contains(prova))
			return;
		this.prova.add(prova);
		prova.setProfessor(this);

	}

	// metodo remove Prova do atributo de relacionamento n-ário
	public void removeProva(Prova prova) throws DadosException {
		if (!this.prova.contains(prova))
			return;
		this.prova.remove(prova);
		prova.setProfessor(null);

	}

	public Set<Questao> getQuestao() {
		return new HashSet<Questao>(this.questao);
	}

	// metodo adiciona Questao do atributo de relacionamento n-ário
	public void adicionaQuestao(Questao questao) throws DadosException {
		if (this.questao.contains(questao))
			return;
		this.questao.add(questao);
		questao.setProfessor(this);

	}

	// metodo remove Questao do atributo de relacionamento n-ário
	public void removeQuestao(Questao questao) throws DadosException {
		if (!this.questao.contains(questao))
			return;
		this.questao.remove(questao);
		questao.setProfessor(null);

	}

	public Set<Comentario> getComentario() {
		return new HashSet<Comentario>(this.comentario);
	}

	// metodo adiciona Comentario do atributo de relacionamento n-ário
	public void adicionaComentario(Comentario comentario) throws DadosException {
		if (this.comentario.contains(comentario))
			return;
		this.comentario.add(comentario);
		comentario.setProfessor(this);

	}

	// metodo remove Comentario do atributo de relacionamento n-ário
	public void removeComentario(Comentario comentario) throws DadosException {
		if (!this.comentario.contains(comentario))
			return;
		this.comentario.remove(comentario);
		comentario.setProfessor(null);

	}

	public Set<AplicacaoDeProva> getAplicacao() {
		return new HashSet<AplicacaoDeProva>(this.aplicacao);
	}

	// metodo adiciona aplicação de prova do atributo de relacionamento n-ário
	public void adicionaAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		if (this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.add(aplicacao);
		aplicacao.setProfessor(this);

	}

	// metodo remove aplicação de prova do atributo de relacionamento n-ário
	public void removeAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		if (!this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.remove(aplicacao);
		aplicacao.setProfessor(null);

	}

	/**
	 * Implementação do método toString que retorna uma String que descreve o
	 * objeto Professor
	 */
	public String toString() {
		return "Professor [nome=" + nome + "]";
	}

	// validação dos atributos
	@RegraDeDominio
	public void validarNome(String n) throws DadosException {
		if (n == null || n.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "O nome não pode ser nulo"));
	}

	@RegraDeDominio
	public void validarMatr(String m) throws DadosException {
		if (m == null || m.length() == 0)
			throw new DadosException(new ErroDeDominio(2, "A Matricula não pode ser nula"));
	}

	@RegraDeDominio
	public void validarProva(Prova prova) throws DadosException {
		// não há regras de validação
	}

	@RegraDeDominio
	public void validarQuestao(Questao questao) throws DadosException {
		// não há regras de validação
	}

	@RegraDeDominio
	public void validarComentario(Comentario comentario) throws DadosException {
		// não há regras de validação
	}

	@RegraDeDominio
	public void validarAplicacao(AplicacaoDeProva aplicacao) throws DadosException {
		// não há regras de validação
	}

	/**
	 * Método utilizado para colocar os Professores em ordem
	 */
	public int compareTo(Professor d) {
		return this.nome.compareTo(d.nome);
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.nome };
	}

	@Override
	public Object getChave() {
		// TODO Auto-generated method stub
		return id;
	}

}
