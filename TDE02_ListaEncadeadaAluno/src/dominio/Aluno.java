package dominio;

public class Aluno {
	private String nome;
	private int cr;
	private int matr;
	private Aluno prox;

	public Aluno(String nome, int cr, int matr) {
		super();
		this.nome = nome;
		this.cr = cr;
		this.matr = matr;
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

	public int getMatr() {
		return matr;
	}

	public void setMatr(int matr) {
		this.matr = matr;
	}

	public Aluno getProx() {
		return prox;
	}

	public void setProx(Aluno prox) {
		this.prox = prox;
	}

}
