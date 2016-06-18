package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

/**
 * Implementa a classe Departamento que tem o "implements Serializable"
 * para realizar o processo de serialização e o "implements Tabelavel"
 * para informar que os objetos poderão ser exibidos em uma 
 * tabela de interface
 * @author Alessandro Cerqueira
 *
 */
public class Departamento implements IDados, ITabelavel, Comparable<Departamento>, Serializable {	
	//
	// CONSTANTES
	//
	public static final int TAMANHO_SIGLA = 2;
	public static final int TAMANHO_NOME = 40;
	
	//
	// ATRIBUTOS
	//
	private String sigla;
	private String nome;
	private Set<Empregado> conjEmpregados;
	private Set<Projeto> conjProjetos;
	
	//
	// MÉTODOS
	//
	public Departamento(String sigla, String nome) throws DadosException {
		super();
		this.setSigla(sigla);
		this.setNome(nome);
		this.conjEmpregados = new TreeSet<Empregado>();
		this.conjProjetos = new TreeSet<Projeto>();
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) throws DadosException {
		validarSigla(sigla);
		this.sigla = sigla;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) throws DadosException {
		validarNome(nome);
		this.nome = nome;
	}
	
	public void adicionarEmpregado(Empregado novo) throws DadosException {
		if(this.conjEmpregados.contains(novo))
			return;
		this.conjEmpregados.add(novo);
		novo.setDepto(this);
	}

	public void removerEmpregado(Empregado ex) throws DadosException {
		if(!this.conjEmpregados.contains(ex))
			return;
		this.conjEmpregados.remove(ex);
		ex.setDepto(null);
	}

	public void adicionarProjeto(Projeto novo) throws DadosException {
		Departamento.validarProjeto(novo);
		if(this.conjProjetos.contains(novo))
			return;
		this.conjProjetos.add(novo);
		novo.setDepto(this);
	}

	public boolean removerProjeto(Projeto ex) throws DadosException {
		Departamento.validarProjeto(ex);
		if(!this.conjProjetos.contains(ex))
			return false;
		this.conjProjetos.remove(ex);
		ex.setDepto(null);
		return true;	
	}

	public Object getChave() {
		return sigla;
	}

	@RegraDeDominio
	public static void validarSigla(String sigla) throws DadosException {
		if(sigla == null || sigla.length() == 0) 
			throw new DadosException(new ErroDeDominio(1, "A Sigla não pode ser nula!"));
		if(sigla.length() != TAMANHO_SIGLA)
			throw new DadosException(new ErroDeDominio(2, "A Sigla deve apresentar dois caracteres!"));		
	}

	@RegraDeDominio
	public static void validarNome(String nome) throws DadosException {
		if(nome == null || nome.length() == 0) 
			throw new DadosException(new ErroDeDominio(3, "O Nome não pode ser nulo!"));
		if(nome.length() > 40)
			throw new DadosException(new ErroDeDominio(4, "O Nome não deve exceder a " + TAMANHO_NOME + " caracteres!"));		
	}

	@RegraDeDominio
	public static void validarProjeto(Projeto proj) throws DadosException {
		if(proj == null) 
			throw new DadosException(new ErroDeDominio(5, "O projeto não pode ser nulo!"));
	}

	@RegraDeDominio
	public static void validarEmpregado(Empregado emp) throws DadosException {
		if(emp == null) 
			throw new DadosException(new ErroDeDominio(6, "O empregado não pode ser nulo!"));
	}

	/** 
	 * Implementação do método toString que retorna uma String
	 * que descreve o objeto Departamento
	 */
	public String toString() {
		return this.sigla + "-" + this.nome;
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos 
	 * dos objetos
	 * @return
	 */
	public Object[] getData() {
		return new Object[]{this.sigla, this.nome, this.conjEmpregados.size()};
	}
	
	/**
	 * Método utilizado para colocar os departamentos em ordem 
	 */
	public int compareTo(Departamento d) {
		return this.nome.compareTo(d.nome);
	}
}
