package controle;

import java.util.Scanner;

import dominio.Lista;
import dominio.Nodo;

public class Programa {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Lista listaSimpEnc = new Lista();

		char r = 'S';
		do {
			System.out.println("Digite um valor inteiro:");
			int valor = input.nextInt();
			listaSimpEnc.adicionaOrdenado(valor);
			System.out.println("Deseja inserir novamente um valor? (S/N)");
			r = input.next().charAt(0);
		} while (Character.toUpperCase(r) == 'S');
		Nodo ptAux = listaSimpEnc.getPtLista();
		while (ptAux != null) {
			System.out.println(ptAux.getInfo());
			ptAux = ptAux.getProx();
		}

		System.out.println("Qual valor desejas buscar? ");
		int valor = input.nextInt();

		Nodo noAux = listaSimpEnc.buscaNo(valor);

		if (noAux != null) {
			System.out.println("Nó encontrado!");
		} else {
			System.out.println("Nó não encontrado!");
		}

		boolean resp = listaSimpEnc.removeNo(1);
		if (resp == true) {
			System.out.println("Nó removido com sucesso!");
		} else {
			System.out.println("Não foi possível remover o nó!");
		}
		resp = listaSimpEnc.removeNo(5);
		resp = listaSimpEnc.removeNo(3);

		ptAux = listaSimpEnc.getPtLista();

		while (ptAux != null) {
			System.out.println(ptAux.getInfo());
			ptAux = ptAux.getProx();
		}

	}

}
