package Exercicio4Desafio;

public class testeExercicio4Desafio {

	public static void main(String[] args) {

		Numeros numeros1 = new Numeros(new GeradorNumericoSequencial());
		int[] numerosSequenciais = numeros1.gerar(5);
		imprimirSequencial(numerosSequenciais);

		Numeros numeros2 = new Numeros(new GeradorNumericoRandomico(20));
		int[] numerosRandomicos = numeros2.gerar(7);
		imprimirRandomico(numerosRandomicos);
	}

	private static void imprimirSequencial(int[] numeros) {
		System.out.print("Os Numeros Sequenciais Sao!!\n");
		for (int numero : numeros) {
			 System.out.println(numero);
		}
	}

	private static void imprimirRandomico(int[] numeros) {
		System.out.print("Os Numeros Randomicos Sao!!\n");
		for (int numero : numeros) {
			System.out.println(numero);
		}
	}
}
