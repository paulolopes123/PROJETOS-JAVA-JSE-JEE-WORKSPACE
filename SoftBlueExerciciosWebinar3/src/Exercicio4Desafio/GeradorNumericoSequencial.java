package Exercicio4Desafio;

public class GeradorNumericoSequencial implements GeradorNumerico {
	private int atual = -1;

	public GeradorNumericoSequencial() {

	}

	public int proximo() {
		atual++;

		return atual;

	}

}
