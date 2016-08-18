package model;

public class Noh {

	private Noh prox;
	private Noh ant;
	private String nome;

	public Noh() {
		super();
	}

	

	public Noh(String nome) {
		super();
		this.nome = nome;
	}



	public Noh getAnt() {
		return ant;
	}

	public void setAnt(Noh ant) {
		this.ant = ant;
	}

	public Noh getProx() {
		return prox;
	}

	public void setProx(Noh prox) {
		this.prox = prox;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
