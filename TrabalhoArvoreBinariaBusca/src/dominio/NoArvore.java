package dominio;

/*
 * Implementar com a estrutura do nó:
 * ALUNO: Nome, matricula, CR + end. sub. esq. +  end. sub. dir.
 * OBS: O campo chave é matrícula
 */

public class NoArvore {
	private String nome;
	private int cr;
	private int matricula; // valor armazenado no nó
	private NoArvore esquerdo; // filho esquerdo
	private NoArvore direito; // filho direito

	// Construtor Vazio
	public NoArvore() {
		super();
	}

	// construtor do nó
	public NoArvore(String nome, int cr, int matricula) {
		this.nome = nome;
		this.cr = cr;
		this.matricula = matricula;
		this.direito = null;
		this.esquerdo = null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCr() {
		return cr;
	}

	public void setCr(int cr) {
		this.cr = cr;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public NoArvore getEsquerdo() {
		return esquerdo;
	}

	public void setEsquerdo(NoArvore esquerdo) {
		this.esquerdo = esquerdo;
	}

	public NoArvore getDireito() {
		return direito;
	}

	public void setDireito(NoArvore direito) {
		this.direito = direito;
	}

}
