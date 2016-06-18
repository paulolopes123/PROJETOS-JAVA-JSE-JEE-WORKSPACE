package dominio;

public class Pessoa {

	private String nome;
	private int idade;
	private String sexo;
	private Pessoa prox;
	private Pessoa ant;

	public Pessoa(String nome, int idade, String sexo) {
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Pessoa getProx() {
		return prox;
	}

	public void setProx(Pessoa prox) {
		this.prox = prox;
	}

	public Pessoa getAnt() {
		return ant;
	}

	public void setAnt(Pessoa ant) {
		this.ant = ant;
	}

}
