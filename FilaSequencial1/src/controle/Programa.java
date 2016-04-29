/*
 * By Pedro Chaves
 * Corrigido em sala por Luciene Motta em 15/mar/16
*/

package controle;

import java.util.Scanner;

import dominio.Fila;

public class Programa {

	public static void main(String[] args) {

		int valor;
		char resp;
		boolean confirma;

		Scanner input = new Scanner(System.in);

		System.out.println("Entre com um inteiro maior do que ZERO para o tamanho da fila: ");
		int tam = input.nextInt();

		Fila fila = new Fila(tam);

		do {
			System.out.println("Entre com um inteiro para inserir na fila: ");
			valor = input.nextInt();
			confirma = fila.insereNaFila(valor);
			if (confirma) {
				System.out.println("Inserção Realizada! Deseja continuar inserindo (S/N)?");
				resp = input.next().charAt(0);
				// ou
				// resp = Character.toUpperCase(input.next().charAt(0));
				// ou
				// resp = Character.toUpperCase(resp);
				// ou testa direto no while
			} else {
				resp = 'N';
				System.out.println("Não houve inserção! Fila Cheia!");
			}
		} while (Character.toUpperCase(resp) == 'S');
		// ou while(resp == 'S' || resp == 's');

		resp = 'S';
		int itemRemovido = 0;
		while (Character.toUpperCase(resp) == 'S') {
			System.out.println("Deseja remover um elemento da Fila (S/N)?");
			resp = input.next().charAt(0);
			if (Character.toUpperCase(resp) == 'S') {
				if (fila.getInicio() != -1) {
					itemRemovido = fila.getConteudoInicio();
				}
				confirma = fila.removeDaFila();
				if (!confirma) {
					resp = 'N';
					System.out.println("Remoção não foi realizada! Fila Vazia!");
				} else {
					System.out.println("Remoção realizada! Item Removido: " + itemRemovido);
				}
			}

		}
	}

}
