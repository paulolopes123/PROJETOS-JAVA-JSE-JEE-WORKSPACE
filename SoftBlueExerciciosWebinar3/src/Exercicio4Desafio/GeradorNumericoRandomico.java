package Exercicio4Desafio;

import java.util.Random;

public class GeradorNumericoRandomico implements GeradorNumerico {

	private int max;
	private Random random = new Random();

	public GeradorNumericoRandomico(int max) {
		this.max = max;

	}

	public int proximo() {
		return random.nextInt(max + 1);

	}
}
