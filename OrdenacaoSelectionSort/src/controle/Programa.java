package controle;

import java.util.Scanner;

import dominio.SelectionSort;

public class Programa {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Informe o Tamanho que a Lista Ter�");
		int tam = entrada.nextInt();
		SelectionSort lista = new SelectionSort(tam);

		char resp;
		boolean confirma;
		int num;

		System.out.println("####################################################################");
		System.out.println("MENU DE OP��ES - ESCOLHA UMA DAS ALTERNATIVAS ABAIXO!!");
		System.out.println("\n 1 - Inserir em Elemento");
		System.out.println("\n 2 - Remover um Elemento");
		System.out.println("\n 3 - Listar os Elementos");
		System.out.println("\n 4 - Buscar um Elemento");
		System.out.println("\n 5 - Ordenar os Elementos");
		System.out.println("\n 6 - Busca Bin�ria de um Elemento");
		System.out.println("####################################################################");
		int valor = entrada.nextInt();

		switch (valor) {
		case 1:
			//////////// - Inser��o do Elemento - \\\\\\\\\\\\\\

			do {
				System.out.println("Entre com um inteiro e Positivo para inserir na Lista Linear: ");
				num = entrada.nextInt();
				confirma = lista.insereElemento(num);
				if (confirma) {
					System.out.println("Inser��o Realizada! Deseja continuar inserindo (S/N)?");
					resp = entrada.next().charAt(0);

				} else {
					resp = 'N';
					System.out.println("N�o houve inser��o! Lista Linear Cheia!");
				}
			} while (Character.toUpperCase(resp) == 'S');
			break;
		case 2:
			//////////// - Remo��o do Elemento - \\\\\\\\\\\\\\
			do {
				System.out.println("Qual elemento deseja remover da Lista?");
				num = entrada.nextInt();
				confirma = lista.removeElemento(num);
				if (confirma) {
					System.out.println("Elemento Removido com sucesso!!");
					System.out.println("Deseja remover outro elemento (S/N)?");
					resp = entrada.next().charAt(0);

				} else {
					resp = 'N';
					System.out.println("Remo��o n�o foi realizada! Elemento n�o foi encontrado na Lista Linear!");
				}
			} while (Character.toUpperCase(resp) == 'S');
			break;
		case 3:
			//////////// - Impress�o dos Elementos - \\\\\\\\\\\\\\
			confirma = lista.imprimiElementos();
			if (confirma) {
				System.out.println("Imprimindo Elementos!!");
				System.out.println(lista.imprimiElementos());
			} else {
				System.out.println("N�o H� Elementos para serem impressos - Lista Vazia!!");
			}
			break;
		case 4:
			//////////// - Busca do Elemento - \\\\\\\\\\\\\\
			do {
				System.out.println("Qual elemento deseja buscar na Lista?");
				num = entrada.nextInt();
				int b = lista.buscarElemento(num);
				if (b >= 0) {
					System.out.println("Elemento Encontrado!!");
					System.out.println(b);
					System.out.println("Deseja buscar outro elemento (S/N)?");
					resp = entrada.next().charAt(0);
				} else {

					resp = 'N';
					System.out.println("Elemento n�o Encontrado na Lista Ou Lista Vazia!!");
				}
			} while (Character.toUpperCase(resp) == 'S');
			break;
		case 5:
			//////////// - Ordena��o dos Elementos - \\\\\\\\\\\\\\
			System.out.println("Ordenando Os Elementos!!");
			lista.ordenarSelectionSort();
			System.out.println("Imprimindo Os Elementos ap�s a Ordena��o!!");
			break;
		case 6:
			//////////// - Busca Bin�ria do Elemento - \\\\\\\\\\\\\\
			break;
		default:
			System.out.println("Op��o Inv�lida!!");
		}
	}

}
