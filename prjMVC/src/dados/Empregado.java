package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Empregado implements IDados, ITabelavel, Comparable<Empregado>, Serializable {
	//
	// CONSTANTES
	//
	public static final int TAMANHO_CPF = 11;
	public static final int TAMANHO_NOME = 40;
	
	
	public enum Status {
		ALOCADO, DESALOCADO, DESEMPREGADO;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws DadosException {
			if(anterior == null && novo == DESALOCADO || 
			   anterior == ALOCADO && novo == DESALOCADO || 
			   anterior == ALOCADO && novo == DESEMPREGADO ||
			   anterior == DESALOCADO && novo == ALOCADO ||
			   anterior == DESALOCADO && novo == DESEMPREGADO)
				return;
			throw new DadosException(new ErroDeDominio(1, "Um empregado não pode deixar de ser " + (anterior==null?"NULO":anterior) + " e passar a ser " + novo));
		}
	}
	
	//
	// ATRIBUTOS
	//
	private String cpf;
	private String nome;
	private Status status;
	private Departamento depto;
	private Set<Projeto> conjProjetos;
	

	//
	// MÉTODOS
	//
	public Empregado(String cpf, String nome, Departamento d) throws DadosException {
		super();
		this.setStatus(Status.DESALOCADO); // Estado Inicial
		this.setCpf(cpf);
		this.setNome(nome);
		this.setDepto(d);
		this.conjProjetos = new TreeSet<Projeto>();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws DadosException {
		validarCpf(cpf);
		this.cpf = cpf;
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
			this.setStatus(Status.DESALOCADO);
			Departamento antigo = this.depto; 
			this.depto = null;
			antigo.removerEmpregado(this);
		}
		else {
			this.setStatus(Status.ALOCADO);
			if(this.depto != null)
				this.depto.removerEmpregado(this);
			this.depto = depto;
			this.depto.adicionarEmpregado(this);							
		}
	}

	public void adicionarProjeto(Projeto novo) throws DadosException {
		Empregado.validarProjeto(novo);
		if(!this.conjProjetos.contains(novo)) {
			this.conjProjetos.add(novo);
			novo.adicionarEmpregado(this);
		}
	}

	public void removerProjeto(Projeto ex) throws DadosException {
		Empregado.validarProjeto(ex);
		if(this.conjProjetos.contains(ex)) {
			this.conjProjetos.remove(ex);
			ex.removerEmpregado(this);
		}
	}

	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status novo) throws DadosException {
		Status.validarTransicaoStatus(this.status,novo);
		this.status = novo;
	}

	public Object getChave() {
		return cpf;
	}

	@RegraDeDominio
	public static void validarCpf(String cpf) throws DadosException {
		if(cpf == null || cpf.length() == 0) 
			throw new DadosException(new ErroDeDominio(1, "O CPF não pode ser nulo!"));
		for(int i = 0; i < cpf.length(); i++)
			if(!Character.isDigit(cpf.charAt(i)))
				throw new DadosException(new ErroDeDominio(2, "O CPF só deve digitos!"));		
		if(cpf.length() != TAMANHO_CPF)
			throw new DadosException(new ErroDeDominio(3, "O CPF deve ter " + TAMANHO_CPF + " digitos!"));		
	}

	@RegraDeDominio
	public static void validarNome(String nome) throws DadosException {
		if(nome == null || nome.length() == 0) 
			throw new DadosException(new ErroDeDominio(4, "O Nome não pode ser nulo!"));
		if(nome.length() > 40)
			throw new DadosException(new ErroDeDominio(5, "O Nome não deve exceder a " + TAMANHO_NOME + " caracteres!"));		
	}

	@RegraDeDominio
	public static void validarDepto(Departamento depto) throws DadosException {
		// Não há regras de validação 
	}

	@RegraDeDominio
	public static void validarProjeto(Projeto proj) throws DadosException {
		if(proj == null) 
			throw new DadosException(new ErroDeDominio(6, "O projeto não pode ser nulo!"));
	}

	/**
	 * Implementação do método toString que retorna uma String que descreve o
	 * objeto Empregado
	 */
	public String toString() {
		return this.cpf + "-" + this.nome;
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		String nomeDepto = this.depto == null ? "Não Alocado" : this.depto.getNome(); 
		return new Object[] { this.cpf, this.nome, nomeDepto};
	}

	/**
	 * Método utilizado para colocar os empregados em ordem
	 */
	public int compareTo(Empregado d) {
		return this.nome.compareTo(d.nome);
	}
}