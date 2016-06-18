package dados;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Projeto implements IDados, ITabelavel, Comparable<Projeto>, Serializable {
	//
	// CONSTANTES
	//
	public static final int TAMANHO_NOME = 40;
	
	
	public enum Status {
		EM_FUNCIONAMENTO, PARADO, DESCONTINUADO;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws DadosException {
			if(anterior == null && novo == EM_FUNCIONAMENTO || 
			   anterior == EM_FUNCIONAMENTO && novo == PARADO || 
			   anterior == EM_FUNCIONAMENTO && novo == DESCONTINUADO ||
			   anterior == PARADO && novo == EM_FUNCIONAMENTO ||
			   anterior == PARADO && novo == DESCONTINUADO)
				return;
			throw new DadosException(new ErroDeDominio(1, "Um empregado n�o pode deixar de ser " + (anterior==null?"NULO":anterior) + " e passar a ser " + novo));
		}
	}
	
	//
	// ATRIBUTOS
	//
	private String nome;
	private Departamento depto;
	private Set<Empregado> conjEmpregados;
	private Status status;
	

	//
	// M�TODOS
	//
	public Projeto(String nome, Departamento d, List<Empregado> conjEmpregados) throws DadosException {
		super();
		this.setNome(nome);
		this.setDepto(d);
		this.conjEmpregados = new TreeSet<Empregado>();
		for(Empregado e : conjEmpregados)
			this.adicionarEmpregado(e);
		this.setStatus(Status.EM_FUNCIONAMENTO);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws DadosException {
		validarNome(nome);
		this.nome = nome;
	}

	public Departamento getDepto() {
		return depto;
	}

	public void setDepto(Departamento depto) throws DadosException {		
		if(this.depto == depto)
			return;
		if(depto == null) {
			Departamento antigo = this.depto; 
			this.depto = null;
			antigo.removerProjeto(this);
			this.setStatus(Status.DESCONTINUADO);
		}
		else {
			if(this.depto != null)
				this.depto.removerProjeto(this);
			this.depto = depto;
			this.depto.adicionarProjeto(this);							
		}
	}
	
	public Set<Empregado> getConjEmpregados() {
		return new HashSet<Empregado>(this.conjEmpregados);
	}
	
	public void adicionarEmpregado(Empregado novo) throws DadosException {
		Projeto.validarEmpregado(novo);
		if(!this.conjEmpregados.contains(novo)) {
			this.conjEmpregados.add(novo);
			novo.adicionarProjeto(this);
		}
	}

	public void adicionarTodosEmpregados(Collection<Empregado> colecao) throws DadosException {
		for(Empregado e : colecao)
			this.adicionarEmpregado(e);
	}

	public void removerEmpregado(Empregado ex) throws DadosException {
		Projeto.validarEmpregado(ex);
		if(this.conjEmpregados.contains(ex)) {
			this.conjEmpregados.remove(ex);
			ex.removerProjeto(this);
		}
	}

	public void removerTodosEmpregados() throws DadosException {
		Collection<Empregado> antigos = new HashSet<Empregado>(this.conjEmpregados);
		for(Empregado e : antigos)
			this.removerEmpregado(e);
	}

	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status novo) throws DadosException {
		Status.validarTransicaoStatus(this.status,novo);
		this.status = novo;
	}

	public Object getChave() {
		return nome;
	}

	@RegraDeDominio
	public static void validarNome(String nome) throws DadosException {
		if(nome == null || nome.length() == 0) 
			throw new DadosException(new ErroDeDominio(4, "O Nome n�o pode ser nulo!"));
		if(nome.length() > 40)
			throw new DadosException(new ErroDeDominio(5, "O Nome n�o deve exceder a " + TAMANHO_NOME + " caracteres!"));		
	}

	@RegraDeDominio
	public static void validarDepto(Departamento depto) throws DadosException {
		if(depto == null)
			throw new DadosException(new ErroDeDominio(6, "Departamento n�o informado"));		
	}

	@RegraDeDominio
	public static void validarEmpregado(Empregado emp) throws DadosException {
		if(emp == null)
			throw new DadosException(new ErroDeDominio(7, "Empregado n�o informado"));		
	}

	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Empregado
	 */
	public String toString() {
		return this.nome + "-" + this.status + "-" + this.depto.getNome() + "-" + this.conjEmpregados;
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.nome, this.depto.getNome(), this.status, this.conjEmpregados.size() };
	}

	/**
	 * M�todo utilizado para colocar os empregados em ordem
	 */
	public int compareTo(Projeto d) {
		return this.nome.compareTo(d.nome);
	}
}