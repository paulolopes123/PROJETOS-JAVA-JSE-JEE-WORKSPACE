//Desenvolvido por Luciene Motta
package controle;

import java.util.Scanner;

import dominio.ListaLinearSequencial;

public class Programa {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Entre com um inteiro referente ao tamanho da lista linear: ");
		int tam = entrada.nextInt();

		ListaLinearSequencial listaLinearSeq = new ListaLinearSequencial(tam);

		int valor;
		char resp;
		boolean confirma;

		// ----------------- Inser��o ------------------------
		do {
			System.out.println("Entre com um inteiro para inserir na Lista Linear: ");
			valor = entrada.nextInt();
			confirma = listaLinearSeq.insereNaLista(valor);
			if (confirma) {
				System.out.println("Inser��o Realizada! Deseja continuar inserindo (S/N)?");
				resp = entrada.next().charAt(0);
			} else {
				resp = 'N';
				System.out.println("N�o houve inser��o! Lista Linear Cheia!");
			}
		} while (Character.toUpperCase(resp) == 'S');

		// -------------- Remo��o -----------------------------
		int itemRemovido = 0;
		do {
			System.out.println("Qual elemento deseja remover da Lista?");
			valor = entrada.nextInt();
			confirma = listaLinearSeq.removeDaLista(valor);
			if (!confirma) {
				System.out.println("Remo��o n�o foi realizada! Elemento n�o foi encontrado na Lista Linear!");
			} else {
				System.out.println("Remo��o realizada com sucesso!");
			}
			System.out.println("Deseja remover outro elemento (S/N)?");
			resp = entrada.next().charAt(0);
		} while (Character.toUpperCase(resp) == 'S');

		// imprimindo a lista linear sequencial resultante
		listaLinearSeq.imprimeListaLinearSeq();
	}

}
