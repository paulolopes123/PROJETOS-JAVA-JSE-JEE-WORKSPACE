package dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Aluno {
	@Id
	@GeneratedValue
	private Long id;
	private Status status;

	private enum Status {
		Matriculado, Trancado, EmRenovacao, Cancelado
	};

	private String nome;
	@Column(unique = true)
	private String matr;
	@Column(unique = true)
	private String senha;
	@Column(unique = true)
	private String login;
	@OneToMany(mappedBy = "aluno")
	private Set<AplicacaoDeProva> aplicacao = new HashSet<AplicacaoDeProva>();
	@OneToMany(mappedBy = "aluno")
	private Set<RealizacaoDeProva> realizacao = new HashSet<RealizacaoDeProva>();

	public Aluno(Long id, Status status, String nome, String matr, String senha, String login) throws DominioException {
		super();
		this.id = id;
		this.setStatus(status);
		this.setNome(nome);
		this.setMatr(matr);
		this.setSenha(senha);
		this.setLogin(login);
	}

	public Aluno() {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws DominioException {
		validarSenha(senha);
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws DominioException {
		validarLogin(login);
		this.login = login;
	}

	public Set<AplicacaoDeProva> getAplicação() {
		return new HashSet<AplicacaoDeProva>(this.aplicacao);
	}

	// metodo adiciona aplicação de prova do atributo de relacionamento n-ário
	public void adicionaAplicacaoDeProva(AplicacaoDeProva aplicacao) {
		if (this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.add(aplicacao);
		aplicacao.setAluno(this);

	}

	// metodo remove aplicação de prova do atributo de relacionamento n-ário
	public void removeAplicacaoDeProva(AplicacaoDeProva aplicacao) {
		if (!this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.remove(aplicacao);
		aplicacao.setAluno(null);

	}

	public Set<RealizacaoDeProva> getRealizacao() {
		return new HashSet<RealizacaoDeProva>(this.realizacao);
	}

	// metodo adiciona realização de prova do atributo de relacionamento n-ário
	public void adicionaRealizacaoDeProva(RealizacaoDeProva realizacao) {
		if (this.realizacao.contains(realizacao))
			return;
		this.realizacao.add(realizacao);
		realizacao.setAluno(this);

	}

	// metodo remove realização de prova do atributo de relacionamento n-ário
	public void removeRealizacaoDeProva(RealizacaoDeProva realizacao) {
		if (!this.realizacao.contains(realizacao))
			return;
		this.realizacao.remove(realizacao);
		realizacao.setAluno(null);

	}

	@Override
	public String toString() {
		return "Aluno [nome=" + nome + "]";
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

	public void validarSenha(String s) throws DominioException {
		if (s == null || s.length() == 0)
			throw new DominioException(ErroDominio.SENHA_INVALIDA);

	}

	public void validarLogin(String l) throws DominioException {
		if (l == null || l.length() == 0)
			throw new DominioException(ErroDominio.LOGIN_INVALIDO);

	}

}
