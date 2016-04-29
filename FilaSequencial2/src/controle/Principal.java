package controle;

import java.util.Scanner;

import dominio.Fila;

public class Principal {

	public static void main(String[] args) {

		int tam, op, v, resp;
		boolean retorno;

		Scanner entrada = new Scanner(System.in);

		System.out.println("Entre com um inteiro maior do que ZERO para o tamanho da Fila: ");
		tam = entrada.nextInt();

		Fila f = new Fila(tam);

		System.out.println("\n======= MENU FILA =======");
		System.out.println("1 - Inserir na Fila: ");
		System.out.println("2 - Remover da Fila: ");
		System.out.println("3 - Imprimir Fila: ");
		System.out.println("4 - Limpar Fila: ");
		System.out.println("5 - Sair ");
		System.out.println("============================");

		System.out.print(" Escolha o tipo de acão que deseja executar: ");

		op = entrada.nextInt();

		switch (op) {
		case 1:
			do {
				System.out.println("Entre com o valor inteiro a ser inserido na Fila: ");
				v = entrada.nextInt();
				retorno = f.insereFila(v);

				if (retorno) {

					System.out.println("Deseja continuar inserindo (1-Sim / 2-Não)?");
					resp = entrada.nextInt();
				} else {
					System.out.println("O valor não foi inserido. Fila cheia!");
					resp = 2;
				}
			} while (resp == 1);

		case 2:

			do {

				retorno = f.removeFila();

				if (retorno) {
					System.out.println("Valor removido!!");
					System.out.println("Deseja continuar removendo (1-Sim / 2-Não)?");
					resp = entrada.nextInt();
				} else {
					System.out.println("O valor não foi removido. Fila vazia!");
					resp = 2;
				}
			} while (resp == 1);

		case 3:
			System.out.println("Impressão da Fila.");
			f.imprimeFila();

		case 4:
			System.out.println("Zerar a Fila");
			f.limpaFila();

		case 5:
			break;

		default:
			break;

		}

	}

}
