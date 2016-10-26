package exercicio1;

public class Funcionario {

	private String nome;

	public Funcionario() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;

	}

	@Override
	public String toString() {

		return nome;
	}

}
