package Exercicio4Desafio;

public class Numeros {
	private GeradorNumerico num;

	public Numeros(GeradorNumerico num) {

		this.num = num;

	}

	public int[] gerar(int qtd) {

		int[] array = new int[qtd];

		for (int i = 0; i < qtd; i++) {
			array[i] = num.proximo();

		}
		return array;

	}
}
