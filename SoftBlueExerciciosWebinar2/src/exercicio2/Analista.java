package exercicio2;

import exercicio1.Funcionario;

public class Analista extends Funcionario {

	private String setor;

	public Analista(String nome, int idade) {
		super(nome, idade);

	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		if (setor == null) {
			System.out.println("Favor será preciso informar um setor!!");
		} else {
			this.setor = setor;
		}
	}

	public void mostrarDados() {
		System.out.println(getSetor());
		super.mostrarDados();

	}

}
