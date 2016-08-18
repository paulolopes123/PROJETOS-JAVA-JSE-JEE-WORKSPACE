package dominio;

public class Pessoa {
	private Pessoa ant;
	private Pessoa prox;
	private String nome;

	public Pessoa(String nome) {
		super();
		this.nome = nome;
	}

	public Pessoa getAnt() {
		return ant;
	}

	public void setAnt(Pessoa ant) {
		this.ant = ant;
	}

	public Pessoa getProx() {
		return prox;
	}

	public void setProx(Pessoa prox) {
		this.prox = prox;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
