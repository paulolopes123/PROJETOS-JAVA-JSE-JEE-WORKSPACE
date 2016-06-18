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
 * Implementa a classe Aluno que tem o "implements Serializable" para realizar o
 * processo de serialização e o "implements Tabelavel" para informar que os
 * objetos poderão ser exibidos em uma tabela de interface
 * 
 *
 */
@Entity
public class Aluno extends Usuario implements IDados, ITabelavel, Comparable<Aluno>, Serializable {

	private enum Status {
		aptoUsarSistema, inaptoUsarSistema;
		public static void validarTransicaoStatus(Status anterior, Status novo) throws DadosException {
			if (anterior == null && novo == inaptoUsarSistema
					|| anterior == aptoUsarSistema && novo == inaptoUsarSistema)

				return;
			throw new DadosException(new ErroDeDominio(1, "Um Aluno não pode deixar de ser " + (anterior==null?"NULO":anterior) + " e passar a ser " + novo));
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

	@OneToMany(mappedBy = "aluno")
	private Set<AplicacaoDeProva> aplicacao = new HashSet<AplicacaoDeProva>();
	@OneToMany(mappedBy = "aluno")
	private Set<RealizacaoDeProva> realizacao = new HashSet<RealizacaoDeProva>();

	// Métodos
	public Aluno(Long id, Status status, String nome, String matr) throws DadosException {
		super();
		this.id = id;
		this.setStatus(status.inaptoUsarSistema);// Estado inicial
		this.setNome(nome);
		this.setMatr(matr);

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

	public Set<AplicacaoDeProva> getAplicação() {
		return new HashSet<AplicacaoDeProva>(this.aplicacao);
	}

	// metodo adiciona aplicação de prova do atributo de relacionamento n-ário
	public void adicionaAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		if (this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.add(aplicacao);
		aplicacao.setAluno(this);

	}

	// metodo remove aplicação de prova do atributo de relacionamento n-ário
	public void removeAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		if (!this.aplicacao.contains(aplicacao))
			return;
		this.aplicacao.remove(aplicacao);
		aplicacao.setAluno(null);

	}

	public Set<RealizacaoDeProva> getRealizacao() {
		return new HashSet<RealizacaoDeProva>(this.realizacao);
	}

	// metodo adiciona realização de prova do atributo de relacionamento n-ário
	public void adicionaRealizacaoDeProva(RealizacaoDeProva realizacao) throws DadosException {
		if (this.realizacao.contains(realizacao))
			return;
		this.realizacao.add(realizacao);
		realizacao.setAluno(this);

	}

	// metodo remove realização de prova do atributo de relacionamento n-ário
	public void removeRealizacaoDeProva(RealizacaoDeProva realizacao) throws DadosException {
		if (!this.realizacao.contains(realizacao))
			return;
		this.realizacao.remove(realizacao);
		realizacao.setAluno(null);

	}

	/**
	 * Implementação do método toString que retorna uma String que descreve o
	 * objeto Aluno
	 */
	public String toString() {
		return "Aluno [nome=" + nome + "]";
	}

	// validação dos atributos
	@RegraDeDominio
	public void validarNome(String n) throws DadosException {
		if (n == null || n.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "O Nome não pode ser nulo!"));
	}

	@RegraDeDominio
	public void validarMatr(String m) throws DadosException {
		if (m == null || m.length() == 0)
			throw new DadosException(new ErroDeDominio(2, "A Matrícula não pode ser nula"));
	}

	@RegraDeDominio
	public void validarAplicacaoDeProva(AplicacaoDeProva aplicacao) throws DadosException {
		// não há regras de validação
	}

	@RegraDeDominio
	public void validarRealizacaoDeProva(RealizacaoDeProva realizacao) throws DadosException {
		if (realizacao == null)
			throw new DadosException(new ErroDeDominio(3, "A Realização não pode ser nula"));

	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.nome };
	}

	public Object getChave() {
		return id;
	}

	/**
	 * Método utilizado para colocar os Alunos em ordem
	 */
	public int compareTo(Aluno d) {
		return this.nome.compareTo(d.nome);
	}

}
