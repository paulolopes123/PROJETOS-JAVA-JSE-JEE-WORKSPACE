package exercicio2;

import exercicio1.Funcionario;

public class Gerente extends Funcionario {

	private double valorBonus;

	public Gerente(String nome, int idade) {
		super(nome, idade);
	}

	public double getValorBonus() {
		return valorBonus;
	}

	public void setValorBonus(double valorBonus) {
		if (valorBonus < 0) {
			System.out.println("Bonûs inválido");
		} else {
			this.valorBonus = valorBonus;
		}
	}

	public double calculaSalario() {
		double sal = super.calcularSalario() + getValorBonus();
		return sal;

	}

	public void mostrarDados() {
		super.mostrarDados();
		System.out.println(getValorBonus());

	}

}
